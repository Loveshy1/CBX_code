package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Comment;
import com.dao.CommentDAO;
import com.db.DBCon;


public class CommentDAOImpl implements CommentDAO{

	private DBCon dbc=null;
	
	@Override
	public int addComment(Comment comment){		
		String sql="insert into comment(bolgid,content,blogname,time) values(?,?,?,?)";
		int res=0;
		try {
			res = dbc.doUpdate(sql,new Object[]{comment.getBlogid(),comment.getContent(),comment.getBlogname(),comment.getTime()});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return res;
		
	}
	
	

	@Override
	public List<Comment> findComments() {
		List<Comment> commentList=new ArrayList<Comment>();
		String sql="select * from comment order by id";
			try {
				dbc=new DBCon();
				ResultSet rs=dbc.doQuery(sql,new Object[]{});
				while(rs.next())
				{
					Comment c=new Comment();
					c.setId(rs.getInt("id"));
					c.setBlogid(rs.getInt("blogid"));
					c.setContent(rs.getString("content"));
					c.setBlogname(rs.getString("blogname"));
					c.setTime(rs.getString("time"));
					commentList.add(c);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				dbc.close();
			}
			return commentList;
	}
	@Override
	public Comment findById(int id) {		
		Comment comment=null;
		try {
			dbc=new DBCon();
			String sql="select * from comment where id=?";
			ResultSet rs = dbc.doQuery(sql,new Object[]{id});
			if(rs.next())
			{
				comment=new Comment();
				comment.setId(rs.getInt("id"));
				comment.setBlogid(rs.getInt("blogid"));
				comment.setContent(rs.getString("content"));
				comment.setBlogname(rs.getString("blogname"));
				comment.setTime(rs.getString("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return comment;
	}
	@Override
	public int delById(int id) {		
		int res=0;
		try {
			String sql="delete from comment where id=?";
			res = dbc.doUpdate(sql,new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return res;
	}
}
