package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Blog;
import com.dao.BlogDAO;
import com.db.DBCon;


public class BlogDAOImpl implements BlogDAO{

	private DBCon dbc=null;
	
	@Override
	public int addBlog(Blog blog){		
		String sql="insert into blog(name,title,content,picture,time) values(?,?,?,?,?)";
		int res=0;
		try {
			res = dbc.doUpdate(sql,new Object[]{blog.getName(),blog.getTitle(),blog.getContent(),blog.getPicture(),blog.getTime()});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return res;
		
	}
	
	
	@Override
	public int updateBlog(Blog blog){		
		int res=0;
		try {
			String sql="update blog set title=?,content=?,time=? where id=?";
			res=dbc.doUpdate(sql,new Object[]{blog.getTitle(),blog.getContent(),blog.getTime(),blog.getId()});
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return res;
	}
	@Override
	public List<Blog> findBlogs() {
		List<Blog> blogList=new ArrayList<Blog>();
		String sql="select * from blog order by id";
			try {
				dbc=new DBCon();
				ResultSet rs=dbc.doQuery(sql,new Object[]{});
				while(rs.next())
				{
					Blog b=new Blog();
					b.setId(rs.getInt("id"));
					b.setName(rs.getString("name"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setPicture(rs.getString("picture"));
					b.setTime(rs.getString("time"));
					b.setLooknumber(rs.getInt("looknumber"));
					blogList.add(b);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				dbc.close();
			}
			return blogList;
	}
	@Override
	public Blog findById(int id) {		
		Blog blog=null;
		try {
			dbc=new DBCon();
			String sql="select * from blog where id=?";
			ResultSet rs = dbc.doQuery(sql,new Object[]{id});
			if(rs.next())
			{
				blog=new Blog();
				blog.setId(rs.getInt("id"));
				blog.setName(rs.getString("name"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getString("content"));
				blog.setPicture(rs.getString("picture"));
				blog.setTime(rs.getString("time"));
				blog.setLooknumber(rs.getInt("looknumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return blog;
	}
	@Override
	public int delById(int id) {		
		int res=0;
		try {
			String sql="delete from blog where id=?";
			res = dbc.doUpdate(sql,new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return res;
	}
}
