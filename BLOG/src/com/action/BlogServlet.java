package com.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.List;

import com.bean.Blog;
import com.dao.BlogDAO;
import com.dao.impl.BlogDAOImpl;


/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private BlogDAO blogdao=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action.equals("query"))
		{
			List<Blog> results=blogdao.findBlogs();
			HttpSession session=request.getSession();
			session.setAttribute("blogList", results);
			
			request.getRequestDispatcher("uarticle.jsp").forward(request, response);
			
		}else if(action.equals("delete"))
		{
			int id=Integer.parseInt(request.getParameter("id")); 
			int res=blogdao.delById(id);
			if(res>0)
			{
				request.getRequestDispatcher("BlogServlet?action=query").forward(request, response);
			}else
			{
				PrintWriter out=response.getWriter();
				out.close();
			}
			
		}else if(action.equals("edit"))
		{
			int id=Integer.parseInt(request.getParameter("id")); 
			Blog blog=blogdao.findById(id);
			HttpSession session=request.getSession();
			session.setAttribute("blog", blog);
			request.getRequestDispatcher("blogmanage.jsp").forward(request, response);
			
		}else if(action.equals("update"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String title=new String(request.getParameter("title"));
			String content=new String(request.getParameter("content"));
			String time=new String(request.getParameter("time"));
			Blog blog=new Blog(id,title,content,time);
			int res=blogdao.updateBlog(blog);
			if(res>0)
			{
				request.getRequestDispatcher("BlogServlet?action=query").forward(request, response);
			}else
			{
				PrintWriter out=response.getWriter();
				out.close();
			}
			
		}else if(action.equals("add"))
		{
			String name=request.getParameter("name");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String picture=request.getParameter("picture");
			String time=request.getParameter("time");
			Blog blog=new Blog(name,title,content,picture,time);
			int res=blogdao.addBlog(blog);
			if(res>0)
			{
				request.getRequestDispatcher("BlogServlet?action=query").forward(request, response);
			}else
			{
				PrintWriter out=response.getWriter();
				out.close();
			}
		}
	}

}
