package com.jdbc.day1;

import java.sql.*;

public class UpdateData {

    static String URL = "jdbc:oracle:thin:@54.162.14.46:1521:xe";
    static String username = "hr";
    static String password = "hr";


    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, username, password);
        System.out.println("connect successful");
        Statement statement = connection.createStatement();
        System.out.println("statement created");
       String query="UPDATE departments SET department_name='Admin' WHERE department_name='Administration'";
       statement.executeUpdate(query);
       String query2="Drop View full_name";
       statement.executeUpdate(query2);






    }


}