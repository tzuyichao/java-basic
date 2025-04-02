package preload.config;

import preload.db.DbOptimizationStrategy;
import preload.db.OracleOptimizationStrategy;
import preload.db.SqlServerOptimizationStrategy;

public enum DbType {
    ORACLE, SQLSERVER;

    public DbOptimizationStrategy getOptimizationStrategy() {
        return switch (this) {
            case ORACLE -> new OracleOptimizationStrategy();
            case SQLSERVER -> new SqlServerOptimizationStrategy();
        };
    }
}
