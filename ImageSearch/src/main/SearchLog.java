package main;

import java.sql.*;

public class SearchLog {

	public void SearchLogDBConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/root";
			conn = DriverManager.getConnection(url,"root","0000");
			System.out.println("���� ����");
		}
		 catch(ClassNotFoundException e){
	            System.out.println("����̹� �ε� ����");
	        }
	        catch(SQLException e){
	            System.out.println("����: " + e);
	        }
	        finally{
	            try{
	                if( conn != null && !conn.isClosed()){
	                    conn.close();
	                }
	            }
	            catch( SQLException e){
	                e.printStackTrace();
	            }
	        }
	}
}
