/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abc
 */
public class Database_Connection {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String URL = "jdbc:mysql://localhost:3306/college_space";
                String USERNAME = "root";
                String PASS = "root";

                connection = DriverManager.getConnection(URL, USERNAME, PASS);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return connection;
    }
    
public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
