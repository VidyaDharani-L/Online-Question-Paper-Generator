package project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
			response.setContentType("text/html");
			PrintWriter out =response.getWriter();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			PreparedStatement pstmt=con.prepareStatement("select * from sample1 where username=? and password=?");
			pstmt.setString(1,username);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if(username.equals("admin") & password.equals("Admin@123")) {
				HttpSession session=request.getSession();
				session.setAttribute("username",username);
				session.setAttribute("password",password);
				
				response.sendRedirect("admin.html");
				
			}
			else if(rs.next()) {
			//else if(!username.equals("admin") & !password.equals("Admin@123")) {

				response.sendRedirect("user.html");
				
			}
			else {
				out.println("Invalid details...login again");
				request.getRequestDispatcher("login.html").include(request,response);
			}
			
				}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		}

}
