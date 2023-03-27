/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.college_space.timetable;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class insert_operation_for_timetable_module extends HttpServlet {

    protected void insert_into_day_schedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deptName = request.getParameter("Key_dept_name");
        int sem =Integer.parseInt(request.getParameter("key_sem")) ;
        
        

    }

}
