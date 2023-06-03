package org.example.app;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataTransfer {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());

        String username = "username";
        String password = "password";
        String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String inputJdbcUrl = "jdbc:sqlserver://serverA:port;databaseName=your_database";
        String outputJdbcUrl = "jdbc:sqlserver://serverB:port;databaseName=your_database";

        String query = "SELECT column1, column2, ..., column200 FROM input_table";

        String insertStatement = "INSERT INTO output_table (column1, column2, ..., column200) VALUES (?, ?, ..., ?)"; // replace with your actual insert statement

        p.apply("ReadFromDB", JdbcIO.<String>read()
                        .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(driverClassName, inputJdbcUrl)
                                .withUsername(username)
                                .withPassword(password))
                        .withQuery(query)
                        .withRowMapper(new JdbcIO.RowMapper<String>() {
                            public String mapRow(ResultSet resultSet) throws Exception {
                                // Here you need to convert each row of data into a single string,
                                // or a more complex type depending on your use case.
                                // For example:
                                return resultSet.getString("column1") + ", " + resultSet.getString("column2") + ", ..., " + resultSet.getString("column200");
                            }
                        }))
                .apply("WriteToDB", JdbcIO.<String>write()
                        .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(driverClassName, outputJdbcUrl)
                                .withUsername(username)
                                .withPassword(password))
                        .withStatement(insertStatement)
                        .withPreparedStatementSetter(new JdbcIO.PreparedStatementSetter<String>() {
                            public void setParameters(String element, PreparedStatement query) throws Exception {
                                // Here you need to set values into each prepared statement.
                                // This depends on how you've formatted your string in the RowMapper.
                                // For example:
                                String[] parts = element.split(", ");
                                for (int i = 0; i < parts.length; i++) {
                                    query.setString(i + 1, parts[i]);
                                }
                            }
                        }));

        p.run().waitUntilFinish();
    }
}
