package model;

import java.sql.*;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MemberDataBase {
	private Constants constants;
	public Connection connection; 
	public Statement statement; 
	public ResultSet resultset;
	
	public MemberDataBase() {
		this.constants = new Constants();
		this.connection = null; 
		this.statement = null; 
		this.resultset = null;
		
		try {
			Class.forName(constants.JDBC_DRIVER);
			connection = DriverManager.getConnection(constants.DB_URL,constants.USERNAME,constants.PASSWORD);
		} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		} catch (SQLException e) { 
			System.out.println("SQL Exception : " + e.getMessage()); 
			e.printStackTrace(); 
		}
	}
	
	// 데베에 정보 넣기 
	public void insertData(String id,String password,String name,String birth,String email,String phoneNumber,String address) throws SQLException
	{
		String insertQuery = constants.INSERTQUERY;
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(insertQuery);
		statement.setString(1, id);
		statement.setString(2, password);
		statement.setString(3, name);
		statement.setString(4, birth);
		statement.setString(5, email);
		statement.setString(6, phoneNumber);
		statement.setString(7, address);
		statement.executeUpdate();
	}
	
	public boolean checkIsHaving(String data,String check) throws SQLException
	{
		boolean isHaving = false;
		String selecQuery = constants.SELECTQUERY;
		Statement statement = (Statement)connection.createStatement();
		ResultSet resultset = statement.executeQuery(selecQuery);
	
		while(resultset.next())
		{
			if(check == resultset.getString(data))
			{
				isHaving = true;
			}
		}
		
		return isHaving; 
	}
}
