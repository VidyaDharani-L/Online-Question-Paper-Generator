package project;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String mobile=request.getParameter("mobilenumber");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		PreparedStatement pstmt=con.prepareStatement("insert into sample1 values(?,?,?,?,?)");
		pstmt.setString(1,name);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, password2);
		pstmt.setString(5, mobile);
		
		int i=pstmt.executeUpdate();
		if(i>0) 
		{
			//account.emsend(email);

			response.sendRedirect("login.html");
			
		}
		else {
			out.println("Details are invalid..login again");

			request.getRequestDispatcher("register.html").include(request,response);
			
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
		  
