/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.college_space.administration;

import common.Database_Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author abc
 */
public class InsertOperationsForAdministration {

    private final String Success = "Success";
    private final String Failed = "Failed";

    private Connection con = null;
    private PreparedStatement ps = null;
    private String query = null;
    private int rowsAffected = 0;

    public  InsertOperationsForAdministration() {
        con = Database_Connection.getConnection();
    }

    public String createCourse(String course_name, String abbr, int total_sem, Date startDate, Date endDate) {

        query = "insert into courses(course_name,abbr,total_semester,starting_date,ending_date) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, course_name);
            ps.setString(2, abbr);
            ps.setInt(3, total_sem);
            ps.setDate(4, startDate);
            ps.setDate(5, endDate);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return Success;
            } else {
                return Failed;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating Semester: " + e.getMessage();
        } finally {
            ps = null;
            query = null;
            rowsAffected = 0;
        }
    }

    public String createSemester(int semester_number, Date start_date, Date end_date, int course_id) {
        query = "INSERT INTO course_semesters (semester_number,  start_date,  end_date,  course_id) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, semester_number);
            ps.setDate(2, start_date);
            ps.setDate(3, end_date);
            ps.setInt(4, course_id);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return Success;
            } else {
                return Failed;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating course: " + e.getMessage();
        } finally {
            ps = null;
            query = null;
            rowsAffected = 0;
        }
    }
    
    public void insertSubjectDetails()
    {
    }
}
