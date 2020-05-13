package com.jdbc.day1;

import java.sql.*;

public class InsertAndDeleteTest {

   static String URL = "jdbc:oracle:thin:@54.162.14.46:1521:xe";
   static String username = "hr";
   static String password = "hr";


    public static void main(String[] args) throws SQLException {

            Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);





         ResultSet resultSet = statement.executeQuery("select * from countries");
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }







            resultSet.close();
            statement.close();
            connection.close();

        }
    }




