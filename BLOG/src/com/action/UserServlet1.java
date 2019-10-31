package com.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import com.bean.User;


import com.db.UOperation;
import java.sql.SQLException;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		User user=new User();
		user.setName(name);
		user.setPassword(password);
		UOperation db=new UOperation();
		try {
			if(db.getOne(user). equalsIgnoreCase("g")){
				request.setAttribute("name",name); 
				request.getRequestDispatcher( "admin.jsp" ).forward(request,response);
										
			}else if(db.getOne(user). equalsIgnoreCase("p")){
				request.setAttribute("name",name);
				request.getRequestDispatcher( "user.jsp" ).forward(request,response);
			}else{
				response.sendRedirect("login.jsp");
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out=response.getWriter();
				e.printStackTrace();
			}		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
