package com.dao;

import java.util.List;


import com.bean.Comment;

public interface CommentDAO {
	public List<Comment> findComments();

	public int addComment(Comment comment);

	public Comment findById(int id);

	public int delById(int id);

	//public int updateComment(Comment comment);

}
