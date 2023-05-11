import java.sql.*;

public class DatabaseConnector {

    public Connection connection(String dbName, String user, String password) throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/" + dbName;
//        String url = "jdbc:postgresql://192.168.1.9:5432/" + dbName;

        String url = "jdbc:postgresql://dpg-che6pve7avja5mbkb7p0-a.frankfurt-postgres.render.com:5432/reactorsdb";
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database: " + dbName);
        return conn;
    }
}


