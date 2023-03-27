/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.college_space.administration;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abc
 */
@WebServlet(name = "TransferRequest", urlPatterns = {"/TransferRequest"})
public class TransferRequest extends HttpServlet {

    GetSendRequestValues getRequestValues;
    SelectOperationsAdministration selectOperationAdministration;
    PrintWriter out;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getRequestValues = new GetSendRequestValues();
        selectOperationAdministration = new SelectOperationsAdministration();

        out = response.getWriter();

        String action = request.getParameter("key_action");
        switch (action) {
            case "createCourse":
                out.print(getRequestValues.getCreateCourceValues(request));
                break;
            case "selectDepartments":
                out.print(selectOperationAdministration.getAllDepartments());
                break;
            case "createSemester":
                out.print(getRequestValues.getCreateSemesterValues(request));
                break;
            case "maxSemForDept":
                String course_id = request.getParameter("key_course_id");
                out.print(new SelectOperationsAdministration().selectMaxSemCountForDept(Integer.parseInt(course_id)));
                break;
            case "selectTeachers":
                out.print(selectOperationAdministration.getAllTeachers());
                break;
                
              case "insertSubData":
                out.print(getRequestValues.getSubjectDetailsValues(request));
                break;
            default:
                // Handle invalid or missing action parameter here
                break;
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
