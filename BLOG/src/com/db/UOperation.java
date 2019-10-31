package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;

public class UOperation {

    public static Connection getConn() {
	String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
	String DB_USERNAME = "root";
	String DB_PASSWORD = "981220";
	Connection conn = null;
	try {
	    Class.forName(DB_DRIVER);
	    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return conn;
    }

    public String getOne(User user) throws SQLException {

	Connection conn = getConn();
	String sql = "select * from user where name=? and password=?";
	PreparedStatement pstmt;

	pstmt = (PreparedStatement) conn.prepareStatement(sql);
	pstmt.setString(1, user.getName());
	pstmt.setString(2, user.getPassword());
	ResultSet rs = pstmt.executeQuery();

	if (rs.next())
	    return rs.getString("status").toString();
	else
	    return "null";

    }

    public static int insert(User u) {
	Connection conn = getConn();
	int i = 0;
	String sql = "insert into user (name,password,status) values(?,?,?)";
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, u.getName());
	    pstmt.setString(2, u.getPassword());
	    pstmt.setString(3, u.getStatus());

	    i = pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return i;
    }

    public boolean updatepwd(String name, String pwd) {
	String sql = "update user set password = ?   where name = ?";
	Connection conn = getConn();
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, pwd);
	    pstmt.setString(2, name);
	    int count = pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	    return count > 0 ? true : false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean update(String name, String pwd, String status) {
	String sql = "update user set password = ? , status = ?  where name = ?";
	Connection conn = getConn();
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, pwd);
	    pstmt.setString(2, status);
	    pstmt.setString(3, name);
	    int count = pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	    return count > 0 ? true : false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean deletename(String name) {
	String sql = "delete from user where name = ?";
	Connection conn = getConn();
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, name);
	    int count = pstmt.executeUpdate();
	    return count > 0 ? true : false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public List<User> getUser(String sql) {
	List<User> lst = new ArrayList<User>();
	Connection conn = getConn();
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    int col = rs.getMetaData().getColumnCount();
	    while (rs.next()) {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setPassword(rs.getString("password"));
		u.setStatus(rs.getString("status"));
		lst.add(u);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return lst;
    }

    public User finduser(String name) {
	Connection conn = getConn();
	String sql = "select * from user where name=? ";
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, name);
	    ResultSet rs = pstmt.executeQuery();
	    rs.next();
	    User u = new User();
	    u.setName(rs.getString("name"));
	    u.setPassword(rs.getString("password"));
	    u.setStatus(rs.getString("status"));

	    return u;
	} catch (SQLException e) {
	    return null;
	}
    }

}
