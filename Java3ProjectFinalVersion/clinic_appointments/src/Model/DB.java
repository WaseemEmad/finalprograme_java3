
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
    private static DB db;
    private DB(){
        
    }
    
    public static DB getInstance(){
        if (db == null) {
            db = new DB();
            return db;
        }else{
           return db; 
        }
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
          Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/clinic_appointments";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
        
    }
    
}
