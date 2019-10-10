package com.improving.tagcli.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OldSchoolDatabaseClient {

    private static final Logger logger = LoggerFactory.getLogger(OldSchoolDatabaseClient.class);

    public void readRecordIntoDatabase() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement())  {
            logger.info("Connection + Statement made!");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WEAPON LIMIT 10");
            ResultSetMetaData metaData = resultSet.getMetaData();
            String columns = "";
            for (int i = 1; i <= metaData.getColumnCount(); i++){
                columns = columns + metaData.getColumnName(i) + ",";
            }
            logger.info("Table columns: " + columns);
            resultSet.beforeFirst();

            while (resultSet.next()) {
               int id = resultSet.getInt(1);
               String name = resultSet.getString(3);
               String area =  resultSet.getString(17);
               logger.info("Id: {}. Name: {}, Area: {}", id, name, area);

            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tag?serverTimezone=UTC",
                "taylorlocal", "supertopsecretpassw0rd");
        connection.setAutoCommit(false);
        return connection;
    }

    public void insertRecordIntoDatabase() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int rowsAffected = statement.executeUpdate("INSERT INTO weapon (Name, Area, ItemType)" + "VALUES ('Test Dagger', 'Test Area', 'Weapon')");
        if(rowsAffected > 0) {
            logger.info("Committing...");
            connection.commit();
        }
        logger.info("Items Affected: " + rowsAffected);
    }
}

