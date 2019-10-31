package com.db;

import java.sql.*;
public class DBCon {
	private static String dbDriver="com.mysql.cj.jdbc.Driver";
	private String dbUrl="jdbc:mysql://localhost:3306/test?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8";
	private String dbUser="root";
	private String dbPass="981220";
	private Connection conn=null;
	
	public Connection getConnection()
	{
		try {
			Class.forName(dbDriver);
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPass);
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public ResultSet doQuery(String sql,Object[] params) 
	{
		ResultSet rs=null;
		conn=this.getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rs;
	}
	public int doUpdate(String sql,Object[] params) 
	{
		int res=0;
		conn=this.getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstmt.setObject(i+1, params[i]);
			}
			res=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return res;
	}
	
	public void close() 
	{
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
