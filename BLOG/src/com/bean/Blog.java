package com.bean;


public class Blog {
	
	private int id;
	private String name;
	private String title;
	private String content;
	private String picture;
	private String time;
	private int looknumber;
	
	public Blog() {}

	public Blog(int id,String title, String content,String time) {

		this.id=id;
		this.title = title;
		this.content = content;
		this.time = time;
	}
	public Blog(String name, String title, String content,String picture,String time) {
        

		this.name = name;
		this.title = title;
		this.content = content;
		this.picture=picture;
		this.time = time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public int getLooknumber() {
		return looknumber;
	}
	public void setLooknumber(int looknumber) {
		this.looknumber = looknumber;
	}

}
