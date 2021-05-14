package main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SearchLog { // mysql ���� Ȱ�������� ������ Ŭ���� 
	
	private Constants constant;
	private Connection conn;
	private PreparedStatement pstmt;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	
	public SearchLog() {
		
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
	public void ShowSearchLog() throws SQLException {
		String selectQuery = constant.SELECTQUERY;
		String searchLogStr ="";
		pstmt = conn.prepareStatement(selectQuery);
		ResultSet rs = pstmt.executeQuery(selectQuery);
		
		while(rs.next()) {
			searchLogStr += "�˻� �ð� : "+rs.getString("date") + "\t�˻��� : "+rs.getString("text") +"\n";
		}
		
	}
}
