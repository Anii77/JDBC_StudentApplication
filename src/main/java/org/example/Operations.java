package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations {


    public   static void fetchData(ResultSet resultSet) throws SQLException
    {
        System.out.println("******STUDENT DETAILS******");
        while (resultSet.next()) {
            System.out.println("Student ID: " + resultSet.getString("st_id"));
            System.out.println("Student Name: " + resultSet.getString("stname"));
            System.out.println("Student Email: " + resultSet.getString("email"));
            System.out.println("Student Address: " + resultSet.getString("phoneNo"));
            System.out.println("-----------------------------------------------------------------");
        }
    }
}
