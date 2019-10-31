package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Blog;





public class BOperation {

	public static Connection getConn() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=CTT";
		String username = "root";
		String password = "981220";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int insert(Blog b) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into blog (name,title,content,picture,time,looknumber) values(?,?,?,?,?,0)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getPicture());
			pstmt.setString(5, b.getTime());
			pstmt.setInt(6, b.getLooknumber());
		
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}


    public boolean update(String sql) throws SQLException {
    	 
        Connection conn = getConn();
        try {
        
            Statement st = conn.createStatement();
     
            int cnt = st.executeUpdate(sql);
            return cnt>0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (conn!=null)
                conn.close();
        }
    }
	
	public List<Blog> getBlog(String sql) {
		List<Blog> lst = new ArrayList<Blog>();
		Connection conn = getConn();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Blog b = new Blog();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setPicture(rs.getString("picture"));
				b.setTime(rs.getString("time"));
				b.setLooknumber(rs.getInt("looknumber"));
				
				lst.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}


	public static int delete(int no) {
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from blog where id='" + no + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
