package preload.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlServerOptimizationStrategy implements DbOptimizationStrategy {
    @Override
    public void applySourceOptimization(PreparedStatement ps) throws SQLException {
        ps.setFetchSize(2000);
    }

    @Override
    public void applyTargetOptimization(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }
}
