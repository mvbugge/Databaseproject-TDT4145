package treningsbok;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBManager {
	
    private static Connection conn;
    
    public DBConn () {
    }
    
    public void connect() {
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user", "myuser");
            p.put("password", "mypassword");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/avtalebok?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
    

    public List<String> requestDB(String sqlQuery) {
    	try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            
            List<String> array = new ArrayList<String>();
            
            while (rs.next()) {
            	String row = "";
            	for(int i = 1; i<=comlumns; i++) {
            		row = row + rs.getString(i);
            	}
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
    		connection.close();
    	} catch (Exception e) {
    	    e.printStackTrace();
        }
    }
}
