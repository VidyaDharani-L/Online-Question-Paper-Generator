package project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ChangePassword extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
			response.setContentType("text/html");
			PrintWriter out =response.getWriter();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String password2=request.getParameter("password2");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			PreparedStatement pstmt=con.prepareStatement("update sample1 set password=?,cpassword=? where username=?");
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,password2);

			int i=pstmt.executeUpdate();
			if(i>0) 
			{

				out.println("Updated Successfully");
				
			}
			else {
				out.println("Details are invalid...try again");

				request.getRequestDispatcher("mer.html").include(request,response);
				
			}
			
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		}
	}
			  
