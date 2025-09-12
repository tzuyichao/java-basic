package preload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;
import preload.config.DbConfig;
import preload.config.PreloadConfig;

import java.io.File;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;

/**
 * Target: MUST MS SQL SERVER
 * Source: Any JDBC source
 * Constraints:
 * - Single table only
 * - Column mapping: query order must match target table column order
 * Notes:
 * - If Source is Oracle should take care about number precision/scale.
 */
public class SingleTablePreloadToMSSQL {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.printf("Usage: java %s <config.json>%n", SingleTablePreloadToMSSQL.class.getSimpleName());
            System.exit(1);
        }

        String configPath = args[0];
        execute(configPath);
    }

    private static void execute(String configPath) {
        try {
            Instant start = Instant.now();
            ObjectMapper mapper = new ObjectMapper();
            PreloadConfig config = mapper.readValue(new File(configPath), PreloadConfig.class);
            System.out.println(mapper.writeValueAsString(config));

            DbConfig source = config.getSource();
            DbConfig target = config.getTarget();

            try (
                Connection sourceConn = DriverManager.getConnection(source.getJdbcUrl(), source.getUsername(), source.getPassword());
                Connection targetConn = DriverManager.getConnection(target.getJdbcUrl(), target.getUsername(), target.getPassword());
                Statement targetStmt = targetConn.createStatement();
                PreparedStatement psSrc = sourceConn.prepareStatement(
                    source.getQuery(),
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
                );
                SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(targetConn)
            ) {
                targetStmt.execute("TRUNCATE TABLE " + target.getTableName());
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
                bulkCopy.setDestinationTableName(target.getTableName());
                bulkCopy.writeToServer(rs);

                long count = 0;
                try(ResultSet totalRs = targetStmt.executeQuery("SELECT COUNT(*) AS COUNT FROM " + target.getTableName());) {
                    totalRs.next();
                    count = totalRs.getLong("COUNT");
                }
                

                Instant end = Instant.now();
                Duration runningDuration = Duration.between(start, end);
                System.out.printf("Total %d Preload completed successfully in %d seconds.%n", count, runningDuration.getSeconds());
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
