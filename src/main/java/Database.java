import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    static Connection koneksi;

    public Database(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/villa";
            String user = "root";
            String pass = "";

            Class.forName(driver);
            koneksi = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
