package com.jdbc.day2;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.*;

public class DatabaseTest {
    final String URL = "jdbc:oracle:thin:@54.162.14.46:1521:xe";
    final String username = "hr";
    final String password = "hr";

    @Test
    public void getEmployeesData() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        List<Integer> employeesId=new ArrayList<>();
        List<String>  employeesName=new ArrayList<>();
        Map<Integer,String> idAndNamePair = new HashMap<>();


        while (resultSet.next()){
            employeesId.add(resultSet.getInt("employee_id"));
            employeesName.add(resultSet.getString("first_name"));
            idAndNamePair.put(resultSet.getInt("employee_id"),resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
        }

        System.out.println(employeesId);
        System.out.println(employeesName);
        System.out.println(idAndNamePair);

        //Assert.assertTrue(resultSet.getMetaData().getColumnCount()==11);

        //get 5th employee name
        System.out.println(employeesName.get(4));
        //get name of employee that id==100
        System.out.println(idAndNamePair.get(100));


        resultSet.close();
        statement.close();
        connection.close();

    }

}






