package data;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SearchLogWithMySql { // mysql ���� Ȱ�������� ������ Ŭ���� 
	
	private Constants constant;
	private Connection conn;
	private PreparedStatement pstmt;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	
	public SearchLogWithMySql() {
		
		this.constant = new Constants();
		
		// mysql �� �����ϱ� 
		try {
			Class.forName(constant.JDBC_DRIVER);
			conn = DriverManager.getConnection(constant.DB_URL,constant.USERNAME,constant.PASSWORD);
			
		} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	// Ȱ�� ���� DB �� �����ϱ� 
	public void InsertSearchLog(String text) throws SQLException {
		String InsertQuery = constant.INSERTQUERY; 
		pstmt = conn.prepareStatement(InsertQuery);
		pstmt.setString(1,text);
		pstmt.setString(2,dateFormat.format(System.currentTimeMillis()).toString());
		pstmt.executeUpdate();
	}
	
	// Ȱ�� ���� �ʱ�ȭ 
	public void DeleteAll() throws SQLException {
		String deleteQuery = constant.DELETEQUERY;
		pstmt = conn.prepareStatement(deleteQuery);
		pstmt.executeUpdate();
	}
	
	// Ȱ�� ���� ����ϱ� 
	public String GetSearchLogString() throws SQLException {
		String selectQuery = constant.SELECTQUERY;
		String searchLogStr ="";
		pstmt = conn.prepareStatement(selectQuery);
		ResultSet rs = pstmt.executeQuery(selectQuery);
		
		int count = 0;
		
		while(rs.next()) {
			searchLogStr += "�˻� �ð� : "+rs.getString("date") + "   �˻��� : "+rs.getString("text") +"\n";
		}
		
		return searchLogStr;
	}
}
