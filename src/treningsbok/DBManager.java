package treningsbok;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

public class DBManager {
	
    private static Connection conn;
    private static Properties p;
    public DBManager(Properties p){
        this.p = p;
    }
    public void DBConn () {
    	connect();
    }
    
    public void connect() {
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/t1?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
        	e.printStackTrace();
            throw new RuntimeException("Unable to connect", e);
    	}
    }
    
    public static List<String> requestDB(String sqlQuery) {
    	try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            
            List<String> array = new ArrayList<String>();
            
            while (rs.next()) {
            	String row = "";
            	for(int i = 1; i<=columns; i++) {
            		row = row + rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ";
            	}
            	row = row + "\n";
            	array.add(row);
            }
            return array;
        } catch (Exception e) {
            System.out.println("Error sending request to DB");
            return null;
        }
        
    }
    
    public void sendDB(String sqlQuery) {
    	try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (Exception e) {
            System.out.println("Error sending to DB");
            return;
        }
    }
    
    public static void close() {
    	try {
    		conn.close();
    	} catch (Exception e) {
    	    e.printStackTrace();
        }
    }
}