
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    private Connection connect;
    public Connection getConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
           this.connect = DriverManager.getConnection("jdbc:mysql://localhost/muzik", "root", "123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.connect;
    }
}
