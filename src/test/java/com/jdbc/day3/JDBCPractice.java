package com.jdbc.day3;

import com.jdbc.utilities.DBUtils;
import org.junit.Test;

import java.sql.*;

public class JDBCPractice {

    final String DB_URL = "jdbc:oracle:thin:@54.152.21.73:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";




    @Test
    public void connectToDB() throws SQLException {
        /**
         * To connect with a database we call DriverManager
         * Provide DB connection URL. username and password
         * By default, JDBC does auto commit
         */
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        connection.setAutoCommit(false); to disable auto commit
//        if don't commit, we can call rollback function to revert changes
//        if auto commit is disabled, at the end we have to call connection.commit() method to make changes permanent
//        only DML commands can ge reverted: INSERT, UPDATE, DELETE
        /**
         * statement is used to execute a query
         */
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        /**
         * A table of data representing a database result set,
         * which is usually generated by executing a statement that queries the database
         * It pointer will be before first row, we need to call next() method
         * To switch pointer and move it to the first row
         */

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        resultSet.next(); //returns boolean, and moves pointer to the next row in result set if it present
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        /**
         * either (1) the row count for SQL Data Manipulation Language (DML) statements
         *     or (2) 0 for SQL statements that return nothing
         */
     //   statement.executeUpdate("UPDATE employees SET first_name = 'Steven', last_name='King' WHERE employee_id = 101");
        System.out.println(firstName);
        System.out.println(lastName);



        while (resultSet.next()){
            String firstName2=resultSet.getString("first_name");
            String lastName2=resultSet.getString("last_name");
            System.out.println(firstName2+" "+ lastName2);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }




    @Test
    public void preparedStatementTest() throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        String query = "SELECT * FROM employees WHERE last_name = ? AND first_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, "King");// replace ? with a value "King"
        preparedStatement.setString(2, "Steven");//replace second ? with value "Steven"

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }


        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


    @Test
    public void metaDataTest() throws Exception {
        //try with resources
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();//database properties, not data itself
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();//result set properties, not a data itself
            while (resultSet.next()) {
                for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                    System.out.print(resultSet.getObject(columnIndex) + " ");
                }
                System.out.println();
            }
        }
    }
    @Test
    public void dbUtilitiesTest(String query) {
        DBUtils.createConnection(DB_URL,DB_USER,DB_PASSWORD);
        System.out.println(DBUtils.getQueryResultMap(query));

        DBUtils.destroy();




    }
}













