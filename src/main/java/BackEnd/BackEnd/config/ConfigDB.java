package BackEnd.BackEnd.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection db() throws SQLException {
        // add your url db last / add your name db
        String url = "jdbc:mysql://localhost:3306/backend";

        //add your DB username and password
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
}
