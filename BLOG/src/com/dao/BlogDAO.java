package com.dao;


import java.util.List;

import com.bean.Blog;


public interface BlogDAO {
	
	public List<Blog> findBlogs();

	public int addBlog(Blog blog);

	public Blog findById(int id);

	public int delById(int id);

	public int updateBlog(Blog blog);


}
