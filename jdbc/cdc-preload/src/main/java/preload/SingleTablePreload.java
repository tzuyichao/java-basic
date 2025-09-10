package preload;

import com.fasterxml.jackson.databind.ObjectMapper;
import preload.config.ColumnConfig;
import preload.config.DbConfig;
import preload.config.PreloadConfig;
import preload.db.DbOptimizationStrategy;

import java.io.File;
import java.sql.*;
import java.util.List;

public class SingleTablePreload {

    public static final int BATCH_SIZE = 1000;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SingleTablePreload <config.json>");
            System.exit(1);
        }

        try {
            String configPath = args[0];
            ObjectMapper mapper = new ObjectMapper();
            PreloadConfig config = mapper.readValue(new File(configPath), PreloadConfig.class);
            System.out.println(mapper.writeValueAsString(config));

            DbConfig source = config.getSource();
            DbConfig target = config.getTarget();

            Connection sourceConn = DriverManager.getConnection(source.getJdbcUrl(), source.getUsername(), source.getPassword());
            Connection targetConn = DriverManager.getConnection(target.getJdbcUrl(), target.getUsername(), target.getPassword());

            DbOptimizationStrategy sourceStrategy = source.getDbType().getOptimizationStrategy();
            DbOptimizationStrategy targetStrategy = target.getDbType().getOptimizationStrategy();

            try (
                    Statement stmt = targetConn.createStatement();
                    PreparedStatement psSrc = sourceConn.prepareStatement(
                            source.getQuery(),
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY
                    );
                    PreparedStatement psDst = targetConn.prepareStatement(target.getInsertSql())
            ) {
                if(target.getPreStatement() != null && !target.getPreStatement().isEmpty()) {
                    stmt.execute(target.getPreStatement());
                }
                sourceStrategy.applySourceOptimization(psSrc);
                targetStrategy.applyTargetOptimization(targetConn);

                ResultSet rs = psSrc.executeQuery();
                List<ColumnConfig> columns = target.getColumns();

                int count = 0;
                while (rs.next()) {
                    for (int i = 0; i < columns.size(); i++) {
                        ColumnConfig col = columns.get(i);
                        int idx = i + 1;
                        switch (col.getType().toUpperCase()) {
                            case "INTEGER" -> psDst.setInt(idx, rs.getInt(col.getName()));
                            case "DECIMAL" -> psDst.setBigDecimal(idx, rs.getBigDecimal(col.getName()));
                            case "BIGINT" -> psDst.setLong(idx, rs.getLong(col.getName()));
                            case "VARCHAR", "NVARCHAR", "CHAR" -> psDst.setNString(idx, rs.getNString(col.getName()));
                            case "TIMESTAMP" -> psDst.setTimestamp(idx, rs.getTimestamp(col.getName()));
                            case "DATE" -> psDst.setDate(idx, rs.getDate(col.getName()));
                            default -> psDst.setObject(idx, rs.getObject(col.getName()));
                        }
                    }
                    psDst.addBatch();

                    if (++count % BATCH_SIZE == 0) {
                        psDst.executeBatch();
                        System.out.println("Inserted rows: " + count);
                        targetConn.commit();
                    }
                }
                psDst.executeBatch(); // flush remaining
                targetConn.commit();
                System.out.println("Preload completed successfully. Total rows: " + count);
            } catch (SQLException ex) {
                targetConn.rollback();
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
