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

            DatabaseMetaData databaseMetaData=connection.getMetaData();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();


            System.out.println("JDBC driver: " + databaseMetaData.getDriverName());
            System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
            System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
            System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

            System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
            System.out.println("Label of 1st column: " + resultSetMetaData.getColumnName(1));
            System.out.println("Data type of first column: " + resultSetMetaData.getColumnTypeName(1));

            System.out.println("################");
            //this loop will loop though columns
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-15s", resultSetMetaData.getColumnName(columnIndex));
            }

            System.out.println("");

            //iterate rows
            while (resultSet.next()) {
                //iterate columns
                for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                    System.out.printf("%-15s", resultSet.getString(columnIndex));
                }
                System.out.println("");
            }




            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Exception" + e);
        }
    }
}