/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stanislav
 */
public class DBConnector {
    private static final String URL = "jdbc:mysql://207.154.222.88:3306/LegoHouse";
    private static final String USER = "test";
    private static final String PASSWORD = "testpassword";
    private static Connection conn = null;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);

        }
        return conn;
    }
}
