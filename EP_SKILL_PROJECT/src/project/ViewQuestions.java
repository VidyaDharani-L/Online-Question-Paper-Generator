package project;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ViewQuestions extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
		String id=request.getParameter("id");
		String ques=request.getParameter("ques");
		String opt1=request.getParameter("opt1");
		String opt2=request.getParameter("opt2");
		String opt3=request.getParameter("opt3");
		String opt4=request.getParameter("opt4");
		
		
		//String ans=request.getParameter("ans");
		//String dlevel=request.getParameter("dlevel");
		//String type=request.getParameter("type");

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		PreparedStatement pstmt=con.prepareStatement("select * from addque");
		pstmt.setString(1,id);
		pstmt.setString(2, ques);
		pstmt.setString(3, opt1);
		pstmt.setString(4, opt2);
		pstmt.setString(5, opt3);
		pstmt.setString(6, opt4);


		ResultSet rs=pstmt.executeQuery();

		out.println(id);
		out.println(ques);
		}
		
		
				catch(Exception e) {
			e.printStackTrace();
			
		}
			

}

}	  
