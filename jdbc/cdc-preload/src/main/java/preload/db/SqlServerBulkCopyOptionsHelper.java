package preload.db;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class SqlServerBulkCopyOptionsHelper {
    public static SQLServerBulkCopyOptions preloadDefault() throws SQLServerException {
        SQLServerBulkCopyOptions copyOptions = new SQLServerBulkCopyOptions();
        copyOptions.setBatchSize(50_000);
        copyOptions.setTableLock(true);
        copyOptions.setCheckConstraints(false);
        copyOptions.setFireTriggers(false);
        copyOptions.setBulkCopyTimeout(0); // unit seconds
        return copyOptions;
    }
}