package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class retrieve_data {

    public int getDepartmentID(String deptName) {

        int id = 0;
        Connection con = Database_Connection.getConnection();
        String query = "select course_id from registration where course_name=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, deptName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("course_id");
            }
            else
            {
                return id;
            }

        } catch (SQLException ex) {
            System.out.println("Error Occure in retieve / getDepartmentID : "+ex);
        }
        return id;
    }

}
