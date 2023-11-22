
package com.fstore.repository;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;

public class DBConnect {
    
private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "FPOLYSTORE";
    private static final boolean USING_SSL = false;
   
    private static String CONNECT_STRING;
   
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";")
                    ;
            connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            
            CONNECT_STRING = connectStringBuilder.toString();
            System.out.println("Connect String có dạng: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static Connection getConnection()  {
         try {
             return DriverManager.getConnection(CONNECT_STRING);
         } catch (SQLException ex) {
             ex.printStackTrace();
             Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }
   
    public static void main(String[] args) throws Exception {
        DatabaseMetaData dbmt = null;
        try {
            Connection conn = getConnection();
            dbmt = conn.getMetaData();
           
            Statement stmt = conn.createStatement();
          
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
      
        
        System.out.println(dbmt.getDriverName());
        System.out.println(dbmt.getDatabaseProductName());
        System.out.println(dbmt.getDatabaseProductVersion());
    }
}
