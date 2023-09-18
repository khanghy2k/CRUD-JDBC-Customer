package com.ra.web.Session6.util;

import java.sql.*;

public class MySqlConnection {
    public static Connection open(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/test_db";
            String user = "root";
            String password="khanghy123";
            Connection cn = DriverManager.getConnection(url,user,password);
            return cn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
