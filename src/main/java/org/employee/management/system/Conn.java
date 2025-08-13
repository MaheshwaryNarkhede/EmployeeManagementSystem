package org.employee.management.system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

    private Connection connection;
    public Statement statement;

    private static final String URL = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "  ";

    public Conn() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create statement object
            statement = connection.createStatement();

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
