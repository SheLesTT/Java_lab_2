import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DatabaseConnector {

    public Connection conncetion(String db_name, String user, String password) {
        String url = "jdbc:postgresql://localhost:5432/" + db_name;
        Connection conn = null;


        try {
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connection");
            } else {
                System.out.println("NO connection");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public void createTabel(Connection conn, SQLTable table) throws SQLException {


        Statement stmt = conn.createStatement();
        String querry = "CREATE TABLE " + table.getTableName() + " (";
        for (String column : table.getCollumnNames()) {

            if (table.getData().get(column).get(0) instanceof String) {
                querry += column + " VARCHAR(255), ";
            } else if (table.getData().get(column).get(0) instanceof Double) {
                querry += column + " float, ";
            } else if (table.getData().get(column).get(0) instanceof Boolean) {
                querry += column + " boolean, ";
            } else if (table.getData().get(column).get(0) instanceof Date) {
                querry += column + " date, ";
            }
        }
        querry = querry.substring(0, querry.length() - 2);
        querry += ")";
        System.out.println(querry);
        stmt.executeUpdate(querry);

        String sql = "ALTER TABLE "+ table.getTableName()+ " ADD CONSTRAINT " +table.getTableName()+"_id_constr PRIMARY KEY (id)";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }


    public String createInsertStatement(SQLTable table){
        String sql = "INSERT INTO " + table.getTableName() + " ( ";
        for(String column: table.getCollumnNames()){
            sql += column + ", ";
        }
        sql = sql.substring(0,sql.length()-2);
        sql +=  ") VALUES(";
        for(int i = 0; i < table.getCollumnNames().length; i++){
            sql += "?,";
        }
        sql = sql.substring(0,sql.length()-1);
        sql+=")";
        return sql;
    }
    public void fillTable(Connection conn, SQLTable table) throws SQLException {
        int count = table.getListByColumnNumber(0).size();
//        System.out.println(count);
        int numColumns = table.getCollumnNames().length;
        String sql = createInsertStatement(table);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < count; i++) {
            for(int column =0; column<(numColumns); column++) {
//                System.out.println(numColumns);
                if(table.getListByColumnNumber(column).get(0) instanceof Double){
                    System.out.println(table.getListByColumnNumber(column).get(i));
                    Double value = (Double) table.getListByColumnNumber(column).get(i);
                    if (value != null && value !=0) {
                        pstmt.setDouble(column + 1, value.doubleValue());
                    } else {
                        pstmt.setNull(column + 1, java.sql.Types.DOUBLE);
                    }
                }
                else if (table.getListByColumnNumber(column).get(0) instanceof String) {
                    pstmt.setString(column+1, (String) table.getListByColumnNumber(column).get(i));
                }
                else if (table.getListByColumnNumber(column).get(0) instanceof Boolean) {
                    pstmt.setBoolean(column+1, (Boolean) table.getListByColumnNumber(column).get(i));
                }
                else if(table.getListByColumnNumber(column).get(0) instanceof Date){
                    java.util.Date utilDate = (java.util.Date) table.getListByColumnNumber(column).get(i);

                    if (utilDate != null){
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        pstmt.setDate(column+1, sqlDate);
                    }
                    else {
                        pstmt.setDate(column+1,null);
                    }


                }


            }

            pstmt.executeUpdate();
        }

        pstmt.close();
    }

    public void addForeingKey(String parent, String child, String parent_col, String child_col, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "ALTER TABLE " +child +" ADD CONSTRAINT " +parent +child + " FOREIGN KEY ("+child_col+") REFERENCES "+parent+ " ("+parent_col+");";
        System.out.println(sql);
        // Execute the SQL statement
        stmt.executeUpdate(sql);

        // Close the statement and connection
        stmt.close();
    }
}
