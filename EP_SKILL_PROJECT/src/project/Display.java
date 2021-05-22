package project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/display")
public class Display extends HttpServlet{
 public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
  String cssTag="<link rel='stylesheet' type='text/css' href='css/style.css'>";
  res.setContentType("text/html");
  PrintWriter out=res.getWriter();
  // <!-- CSS only -->
  String boot="<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6' crossorigin='anonymous'>";
  try {
   Connection con = null;
   Class.forName("oracle.jdbc.driver.OracleDriver");
   System.out.println("Driver Class Loaded");
   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ep", "ep");
   System.out.println("Connection Established");
   PreparedStatement pstmt = con.prepareStatement("select * from addquestion");
   ResultSet rs=pstmt.executeQuery();
   out.println("<center><b>Questions and Answers</b></center><br>");
   /*/
    * 
    * 
.question{
    background: #75ba48;
    padding: 20px;
    color: #fff;
    border-bottom-right-radius: 55px;
    border-top-left-radius: 55px;
}
#qid{
    margin-right: 22px;
    background-color: #ffffff;
    color: #aaaaaa;
}
  <div class="question">
         <h3><span class="label label-warning" id="qid">1</span>
         <span id="question"> How can you add a single line comment in a JavaScript?</span>
         </h3>
     </div>
    */
   while(rs.next()) {
    out.println(
      "<html><head><style>.question{\r\n"
        + "    background: #75ba48;\r\n"
        + "    padding: 20px;\r\n"
        + "    color: #fff;\r\n"
        + "    border-bottom-right-radius: 55px;\r\n"
        + "    border-top-left-radius: 55px;\r\n"
        + "}\r\n"
        + "\r\n"
        + "#qid{\r\n"
        + "    margin-right: 22px;\r\n"
        + "    background-color: #ffffff;\r\n"
        + "    color: #aaaaaa;\r\n"
        + "    \r\n"
        + "} </style> </head>"
        +"<div class='question'>"
        + "<b>"+rs.getInt(1)+")"+"</b>"+" "
        + "\r\n"
        + "<b>"+rs.getString(2)+"</b><br>"+" </div>" 
        +"  <b>a) </b> &nbsp;"+ rs.getString(3)+ "<br> " 
        +" <b> b) </b> &nbsp;"+ rs.getString(4)+ "<br> " 
        +" <b> c) </b> &nbsp;"+ rs.getString(5)+ "<br> " 
           +"  <b>d) </b> &nbsp;"+ rs.getString(3)+ "<br> "
           +"<b>The correct option is :</b>"+rs.getString(7)+"<br>"
           +"<b>Difficulty level: </b>"+rs.getString(8)+"<br>"
           +"<b>Tags: </b>"+rs.getString(9)+"<br>"
        );
   }
  }
  catch(Exception e){
   out.println(e);
  }
 }
}
