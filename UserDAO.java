/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fastagcardcreationproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yuvraj
 */
public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/fastag";
    private static final String UName = "root";
    private static final String UPass = "root";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, UName, UPass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static boolean insertUser(User user) {
        boolean status = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = getConnection();
            String query = "INSERT INTO fastaguser (name, email, username, password) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getUsername());
            pst.setString(4, user.getPassword());

            int row = pst.executeUpdate(); // Insert the user data
            if (row > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static boolean validateUser(String username, String password) {
        boolean status = false;
        String query = "SELECT * FROM fastaguser WHERE username = ? AND password = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            status = rs.next(); // Check if there is at least one row
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}