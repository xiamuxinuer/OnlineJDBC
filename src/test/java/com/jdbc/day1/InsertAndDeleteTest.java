package com.jdbc.day1;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InsertAndDeleteTest {

   static String URL = "jdbc:oracle:thin:@54.162.14.46:1521:xe";
   static String username = "hr";
   static String password = "hr";


    public static void main(String[] args) throws SQLException {

            Connection connection = DriverManager.getConnection(URL, username, password);
            System.out.println("connect successful");
            Statement statement = connection.createStatement();
            System.out.println("statement created");
            ResultSet resultSet = statement.executeQuery("select * from countries");
            System.out.println("result set is completed");

        List<String> countryInfo=new ArrayList<>();
        Map<String,Integer> countryFullInfo=new LinkedHashMap<>();


            while (resultSet.next()){
                String countriesId=resultSet.getString(1);
                String countriesName=resultSet.getString(2);
                int regionId=resultSet.getInt(3);

                System.out.println(countriesId+" "+ countriesName+" " +regionId);
                countryInfo.add(countriesName);
                countryFullInfo.put(countriesName,regionId);

            }

        System.out.println(countryInfo);
        System.out.println(countryInfo.contains("Canada"));
        System.out.println(countryFullInfo);
        System.out.println(countryFullInfo.get("China"));
        System.out.println(countryFullInfo.get("China")==3);


            resultSet.close();
            statement.close();
            connection.close();

        }

    }




