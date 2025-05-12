/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fastagcardcreationproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Yuvraj
 */
public class AdminDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/fastag";

    private static final String AName="root";
    private static final String APass="root";
    public static Connection getConnection()
    {
        Connection con=null;
    
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection(URL,AName,APass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
    public static boolean insertAdmin(Admin admin) {
        boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastag", "root", "root");
            String query = "insert into fastagadmin (name,email, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
           pst.setString(1, admin.getName());
            pst.setString(2, admin.getEmail());
            pst.setString(3, admin.getUsername());
            pst.setString(4, admin.getPassword());
            
            int row = pst.executeUpdate();
            if (row > 0) {
                status = true;
            }
            pst.close();
            con.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean validateAdmin(String username, String password) {
        boolean status = false;
        String query = "SELECT * FROM fastagadmin WHERE username = ? AND password = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
         try {
            
            con=getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password); 
            rs = ps.executeQuery();

            status = rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
