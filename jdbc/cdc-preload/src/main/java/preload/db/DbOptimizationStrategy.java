package preload.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DbOptimizationStrategy {
    void applySourceOptimization(PreparedStatement ps) throws SQLException;
    void applyTargetOptimization(Connection conn) throws SQLException;
}
