package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.db.UOperation;

/**
 * Servlet implementation class UserServlet2
 */
@WebServlet("/UserServlet2")
public class UserServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String status=request.getParameter("status"); 
		String action=request.getParameter("action"); 
		
		UOperation db=new UOperation();
		User u=new User();
		
		String str = ""; 
		
		if(action.equalsIgnoreCase("add"))
		{
			if(status.equalsIgnoreCase("g")||status.equalsIgnoreCase("p"))
			{
			try{
					u.setName(name);
					u.setPassword(password);
					u.setStatus(status);
					db.insert(u);
                    str = "添加成功";
					}catch(Exception e){str="添加失败";}
			}else str="管理员选择错误";
		}
		else if(action.equalsIgnoreCase("delete"))
		{
			if(db.deletename(name))
			{
				str="删除成功";				
			}
			else str="删除失败";
		}
		else if(action.equalsIgnoreCase("update"))
		{
			if(status.equalsIgnoreCase("g")||status.equalsIgnoreCase("p"))
			{
				if(db.update(name, password, status)) 
				{
				str="修改成功";				
				}
				else str="修改失败";

			}
			else str="管理员选择错误";
		}
		else if(action.equalsIgnoreCase("find"))
		{
			try{
				u=db.finduser(name);
				request.setAttribute("name",u.getName()); 
				request.setAttribute("password",u.getPassword()); 
				request.setAttribute("status",u.getStatus()); 
				request.getRequestDispatcher( "/usermanage.jsp" ).forward(request,response);

				}catch(Exception e){
					str="查询失败";
				}
		}
		else str = "增删改查选择错误";
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(str); 
		out.println("<a href=\"usermanage.jsp\">返回</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
