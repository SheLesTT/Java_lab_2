import java.sql.*;
import java.util.Date;

public class DatabaseConnector {

    public Connection conncetion(String dbName, String user, String password) throws SQLException {
        String url = "jdbc:postgresql://192.168.1.9:5432/" + dbName;
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database: " + dbName);
        return conn;
    }
}


