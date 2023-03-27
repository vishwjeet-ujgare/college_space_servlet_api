package com.example.college_space.timetable;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Timetable", urlPatterns = {"/Timetable"})
public class Timetable extends HttpServlet {

    Connection con;
          PrintWriter out ;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
  
        try {
            out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_space", "root", "root");

            String action = request.getParameter("key_action");
            String user_role=request.getParameter("user_role");
            
            if(action==null)
            {
                 out.print("NUll values in responcse");
                return;
            }
            if ((action.equals("daily_timetable;") && user_role.equals("admin")) || (action.equals("daily_timetable;") && user_role.equals("teacher"))) {
                insert_operation_for_timetable_module insert_timetable=new insert_operation_for_timetable_module(); 
               insert_timetable.insert_into_day_schedule(request, response);
            } else {
                out.print("Key_action is wrong");
            }
            out.print("success");
        } catch (Exception e) {
            out.print("Error Occure : " + e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
}
