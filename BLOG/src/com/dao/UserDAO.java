package com.dao;

import java.util.List;

import com.bean.User;

public interface UserDAO {

	public List<User> findUsers();

	public int addUser(User user);

	public User findById(int id);

	public int delById(int id);

	public int updateUser(User user);

}