package minhvu.codelean.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/phpmyadmin/web_student_tracker";
        String username = "root";
        String password = "";

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {

              Class.forName("com.mysql.jdbc.Driver");
              myConn = DriverManager.getConnection(url,username,password);

              String sql = "select * from student";

              myStmt = myConn.createStatement();

              myRs = myStmt.executeQuery(sql);

              while (myRs.next()){
                  String email = myRs.getString("email");
                  out.println(email);
              }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
