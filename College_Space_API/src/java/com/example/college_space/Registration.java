package com.example.college_space;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abc
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

    Connection con;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_space", "root", "root");

        String action = request.getParameter("key_action");

        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("registration")) {
            registration(request, response);
        }

    }

    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // logic for inserting new data
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("key_first_name");
        String lastName = request.getParameter("key_last_name");
        String gender = request.getParameter("key_gender");
        String mblNo = request.getParameter("key_mbl_no");
        String userName = request.getParameter("key_username");
        String emailAddr = request.getParameter("key_email");
//      String secretToken=request.getParameter("key_secretToken");
        String password = request.getParameter("key_pass");
        String userRole = request.getParameter("key_user_role");

        try {

            String query = "INSERT INTO registration (first_name, last_name, gender, mobile_number,username,email, password_hash,role) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, gender);
            ps.setString(4, mblNo);
            ps.setString(5, userName);
            ps.setString(6, emailAddr);
            ps.setString(7, password);
            ps.setString(8, userRole);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.print("Sucess...!");
            } else {
                out.print("Failed...!");
            }
        } catch (Exception e) {
            out.print(e);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // logic for inserting new data
        PrintWriter out = response.getWriter();
        boolean authenticated = false;

        String user_role = request.getParameter("key_user_role");
        String username_or_email = request.getParameter("key_username_or_email");
        String pass = request.getParameter("key_pass");

        String query = "select  * from registration where (username =? or email=?) and role=? and  password_hash=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username_or_email);
        ps.setString(2, username_or_email);
        ps.setString(3, user_role);
        ps.setString(4, pass);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            authenticated = true;
        } else {
            authenticated = false;
        }

        out.print(authenticated);
        out.flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
