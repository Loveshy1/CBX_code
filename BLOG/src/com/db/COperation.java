package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Comment;





public class COperation {

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

	public static int insert(Comment c) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into comment (blogid,content,blogname,time) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setInt(1, c.getBlogid());
			pstmt.setString(2, c.getContent());
			pstmt.setString(3, c.getBlogname());
			pstmt.setString(4, c.getTime());

		
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
	
	public List<Comment> getNews(String sql) {
		List<Comment> lst = new ArrayList<Comment>();
		Connection conn = getConn();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Comment c = new Comment();
				c.setId(rs.getInt("id"));
				c.setBlogid(rs.getInt("blogid"));
				c.setContent(rs.getString("content"));
				c.setBlogname(rs.getString("blogname"));
				c.setTime(rs.getString("time"));
				
				lst.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}


	public static int delete(int no) {
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from comment where id='" + no + "'";
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
