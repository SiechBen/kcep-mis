/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.population.kcep.mis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author siech
 */
public class MysqlConnection {

    public static Connection getConnection() {

        // create MysqlConnection object
        Connection connection;

        try {
            // connection
            String url="jdbc:mysql://localhost:3306/kcep_mis?zeroDateTimeBehavior=convertToNull&useSSL=false";
            String user = "root";
            String password = "complex";

            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the kcep_mis database");
                return connection;
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }

        return null;
    }

}
