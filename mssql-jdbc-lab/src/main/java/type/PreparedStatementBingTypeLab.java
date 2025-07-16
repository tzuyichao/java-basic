package type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementBingTypeLab {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustServerCertificate=true;user=sa;password=YourStrongPassword123;");
    }

    public void insertWithSetNString() {
        final String sql = "insert into PSTest (DeptCode) values (?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setNString(1, "DEP001");
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PreparedStatementBingTypeLab preparedStatementBingTypeLab = new PreparedStatementBingTypeLab();
        preparedStatementBingTypeLab.insertWithSetNString();
    }
}
