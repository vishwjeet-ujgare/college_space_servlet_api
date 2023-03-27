/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.college_space.administration;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetSendRequestValues {

    SimpleDateFormat dateFormat;
    InsertOperationsForAdministration insertAdministration;

    public String getCreateCourceValues(HttpServletRequest req) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String course_name = req.getParameter("key_courseDept_name");
        String abbr = req.getParameter("Key_abbr");
        int totalSem;
        Date startDate;
        Date endDate;

        try {
            totalSem = Integer.parseInt(req.getParameter("key_totalSem"));
            java.util.Date utilStartDate = dateFormat.parse(req.getParameter("key_course_startDate"));
            startDate = new Date(utilStartDate.getTime());

            java.util.Date utilEndDate = dateFormat.parse(req.getParameter("key_course_endDate"));
            endDate = new Date(utilEndDate.getTime());

            insertAdministration = new InsertOperationsForAdministration();

        } catch (NumberFormatException | ParseException e) {
            return "Error Occured while parsing date or string in course :\n" + e;
        }
        insertAdministration = new InsertOperationsForAdministration();

        return insertAdministration.createCourse(course_name, abbr, totalSem, startDate, endDate);
    }

    public String getCreateSemesterValues(HttpServletRequest req) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String temp_dept_id = req.getParameter("key_dept_id");
        String temp_sem_no = req.getParameter("key_sem_num");
        String temp_startDate = req.getParameter("key_start_date");
        String temp_endDate = req.getParameter("key_end_date");
        String returnString;

        if (temp_dept_id.equals("") || temp_sem_no.equals("") || temp_startDate.equals("") || temp_endDate.equals("")) {
            return "All data is compulsaory \n enter data";
        } else {
            try {

                int dept_id = Integer.parseInt(temp_dept_id);
                int sem_no = Integer.parseInt(temp_sem_no);

                java.util.Date utilStartDate = dateFormat.parse(temp_startDate);
                Date startDate = new Date(utilStartDate.getTime());

                java.util.Date utilEndDate = dateFormat.parse(temp_endDate);
                Date endDate = new Date(utilEndDate.getTime());

                returnString = new InsertOperationsForAdministration().createSemester(sem_no, startDate, endDate, dept_id);

            } catch (NumberFormatException | ParseException e) {
                return "Error Occured while parsing date or string in course :\n" + e;
            }
        }

        return returnString;

    }

    public String getSubjectDetailsValues(HttpServletRequest req) {
        String response;
//        System.out.println("hii : " + req.getParameter("key_response").toString());
        int course_id = Integer.parseInt(req.getParameter("key_dept_id"));
        int sem = Integer.parseInt(req.getParameter("key_sem"));
        String response_sem_id = new SelectOperationsAdministration().isDepSemValid(course_id, sem);
        if (response_sem_id.equals("0")) {
            return "Select semester not found for selected department";
        } else {
//            try {
//                int semester_id = Integer.parseInt(response_sem_id);
//                    JSONObject jsonobject = new JSONObject(req.getParameter("key_response"));
//                      JSONArray jsonArray = jsonobject.optJSONArray("sem_subject_details");
//                   
//                       if (jsonArray != null) {
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
//
//                            // Fetch the department id and name from JSONObject
//                            int dept_id = jsonObject1.optInt("id");
//                            String dept_name = jsonObject1.optString("name").toString();
//
//                            // Add department name into the spinnerValuesList
//                            spinnerValuesList.add(dept_name);
//
//                            // Create Department_Values object and add it to department_valuesList
//                            Department_Values department = new Department_Values(dept_id, dept_name);
//                            department_valuesList.add(department);
//                        }
//
//                    } else {
//                        // Show error dialog if no departments found
//                        commonAlertDialog.showMsgDialog(getContext(), "No department Found \n try after some time  or contact to administration", "No department Found");
//                        return;
//                    }
//
//            } catch (Exception e) {
//                return response_sem_id;
//            }
        }

        System.out.println("course_id : " + course_id);
        System.out.println("sem : " + sem);
        return "Hello";
    }
}
