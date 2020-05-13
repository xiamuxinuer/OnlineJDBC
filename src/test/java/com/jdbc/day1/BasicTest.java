package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) {
        String URL = "jdbc:oracle:thin:@54.146.199.178:1521:xe";
        String username = "hr";
        String password = "hr";
        try {
            Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(2));

                System.out.println(resultSet.getString(1) +
                        " " + resultSet.getString(2) +
                        " " + resultSet.getString(3)+
                        " " + resultSet.getString("salary"));
            }

            resultSet.beforeFirst();//to come back to the beginning of the row

//            while (resultSet.next()){
//                System.out.println(resultSet.getString("salary"));
//            }

            DatabaseMetaData databaseMetaDat=connection.getMetaData();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Exception" + e);
        }
    }
}