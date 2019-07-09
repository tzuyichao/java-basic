package jdbc;

import java.sql.*;

public class UserMain {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:mariadb://localhost:3306/mybatis";
        try(Connection connection = DriverManager.getConnection(connectionUrl, "test", "test6yhn")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, sex, age, status, rank from tb_user");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("id: " + id + ", name: " + name);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}
