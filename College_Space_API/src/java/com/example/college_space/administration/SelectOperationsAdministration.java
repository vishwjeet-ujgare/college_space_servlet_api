/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.college_space.administration;

import com.google.gson.Gson;
import common.Database_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.college_space.administration.Department_Values;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author abc
 */
public class SelectOperationsAdministration {

    public String getAllDepartments() {
        ArrayList<Department_Values> department_arrayList = new ArrayList<Department_Values>();
        Connection con = Database_Connection.getConnection();
        String query = "select course_id,course_name from courses";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Gson gson = null;
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");

                Department_Values department_values = new Department_Values(course_id, course_name);
                department_arrayList.add(department_values);
                gson = new Gson();
            }

        } catch (SQLException e) {
            return "Error Occured in selectiono operation : \n" + e;
        }

        String jsonResponse = gson.toJson(department_arrayList);
        // Create a JSON object to store the JSON array as a value of a key
        JSONObject responseObject = new JSONObject();
        try {
            responseObject.put("departments", new JSONArray(jsonResponse));
        } catch (JSONException e) {
            return "Error Occured in selectiono operation : \n" + e;
        }

        return responseObject.toString();
    }

    public String selectMaxSemCountForDept(int course_id) {
        Connection con = Database_Connection.getConnection();
        String query = "SELECT MAX(semester_number) FROM course_semesters WHERE course_id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, course_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int maxSemester = rs.getInt(1);
                return String.valueOf(maxSemester);
            } else {
                return "0"; // No semesters found for given course ID
            }
        } catch (SQLException e) {

            return "An Error ocuuredd :\n" + e; // An error occurred
        }
    }

    public String getAllTeachers() {
        ArrayList<Teacher_Values> teacher_Valueses_list = new ArrayList<Teacher_Values>();
        Connection con = Database_Connection.getConnection();
        String query = " select * from registration where role=?";

        Gson gson = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "teacher");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int teacher_id = rs.getInt("reg_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");

                Teacher_Values teacher_Values = new Teacher_Values(teacher_id, first_name, last_name, email);
                teacher_Valueses_list.add(teacher_Values);
                gson = new Gson();

            }

        } catch (SQLException e) {
            return "Error Occured in selectiono operation : \n" + e;
        }

        String jsonResponse = gson.toJson(teacher_Valueses_list);
        // Create a JSON object to store the JSON array as a value of a key
        JSONObject responseObject = new JSONObject();
        try {
            responseObject.put("teachers", new JSONArray(jsonResponse));
        } catch (JSONException e) {
            return "Error Occured while creatig techer lis: \n" + e;
        }

        return responseObject.toString();

    }

    public String isDepSemValid(int course_id, int semester_number) {
        Connection con = Database_Connection.getConnection();
        int id = 0;
        String query = "select course_sem_id from course_semesters where course_id=? and  semester_number=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            ps.setInt(2, semester_number);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                id = 0;
            }
        } catch (Exception e) {
            return "Error Occure : " + e;
        }

        if (id > 0) {
            return String.valueOf(id);
        }
        return String.valueOf(id);
    }
}
