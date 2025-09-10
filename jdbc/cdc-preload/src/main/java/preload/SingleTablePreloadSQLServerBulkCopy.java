package preload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;
import preload.config.ColumnConfig;
import preload.config.DbConfig;
import preload.config.PreloadConfig;
import preload.db.DbOptimizationStrategy;

import java.io.File;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class SingleTablePreloadSQLServerBulkCopy {

    public static final int BATCH_SIZE = 1000;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SingleTablePreloadSQLServerBulkCopy <config.json>");
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

            try (
                    PreparedStatement psSrc = sourceConn.prepareStatement(
                            source.getQuery(),
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY
                    );
                    SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(targetConn)
            ) {
                if(target.getPreStatement() != null && !target.getPreStatement().isEmpty()) {
                    targetConn.prepareStatement(target.getPreStatement()).execute();
                }
                psSrc.setQueryTimeout(0);
                psSrc.setFetchSize(10_000);

                ResultSet rs = psSrc.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                for(int i=1; i<=md.getColumnCount(); i++) {
                    System.out.printf(
                            "#%d %s | type=%s (%d) | precision=%d | scale=%d | displaySize=%d%n",
                            i, md.getColumnLabel(i), md.getColumnTypeName(i), md.getColumnType(i),
                            md.getPrecision(i), md.getScale(i), md.getColumnDisplaySize(i)
                    );
                }

                SQLServerBulkCopyOptions copyOptions = new SQLServerBulkCopyOptions();
                copyOptions.setBatchSize(50_000);
                copyOptions.setBulkCopyTimeout(0); // unit seconds
                bulkCopy.setBulkCopyOptions(copyOptions);
                bulkCopy.setDestinationTableName("dev.Z_PART_GENERAL");
                bulkCopy.writeToServer(rs);
                System.out.println("Preload completed successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
