package warehouse.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url="jdbc:sqlserver://localhost:1433;";
    private static String database ="databaseName=Warehouse;";
    private static String username="user=sa;";
    private static String password = "password=123456;";
    private static final String ENCRYPT = "encrypt=true;";
    private static final String CERTIFICATION = "trustServerCertificate=true;";
    
     public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        String completeUrl = url+database+username+password+ENCRYPT+CERTIFICATION;
        Connection connection = DriverManager.getConnection(completeUrl);
        PreparedStatement pstmt;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }
        else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static void update(String sql, Object...args) {
        try {
            PreparedStatement stmt = JDBC.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } 
            finally{
                stmt.getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = JDBC.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = JDBC.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

