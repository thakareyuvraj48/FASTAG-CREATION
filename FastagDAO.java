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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yuvraj
 */
public class FastagDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/fastag";

    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    public static Connection getConnection()
    {
        Connection con=null;
    
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
    public static boolean insertFastag(Fastag fastag) {
        boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastag", "root", "root");
            String query = "insert into fastagcreate (vehicleNumber,ownerName,contactInfo) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
           pst.setString(1, fastag.getVehiclenumber());
            pst.setString(2, fastag.getOwnername());
            pst.setString(3,fastag.getContactInfo());
            
            
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
    
    public static List<Fastag> getAllFastag()
    {
        
        List<Fastag> fastagList=new ArrayList<>();
        String sql="SELECT * FROM fastagcreate";
        try{
            Connection con=getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                fastagList.add(new Fastag(
                rs.getString("vehicleNumber"),
                rs.getString("ownerName"),
                rs.getString("contactInfo")
                ));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return fastagList;
    }
    
   public static boolean deleteFastag(String vehicleNumber)
   {
       Connection con=null;
       PreparedStatement ps=null;
       boolean status=false;
       try
       {
           con=getConnection();
           String sql="DELETE FROM fastagcreate WHERE vehicleNumber=?";
           ps=con.prepareStatement(sql);
           ps.setString(1, vehicleNumber);
           status =ps.executeUpdate()>0;
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return status;
   }
  
   
  public static Fastag getFastagByVehicleNumber(String vehicleNumber)
  {
      Connection con=null;
      PreparedStatement ps=null;
      ResultSet rs=null;
      Fastag fastag=null;
      try
      {
          con=getConnection();
          String query="SELECT * FROM fastagcreate WHERE vehicleNumber=?";
          ps=con.prepareStatement(query);
          ps.setString(1, vehicleNumber);
          rs=ps.executeQuery();
          if(rs.next())
          {
              fastag =new Fastag(
              rs.getString("vehicleNumber"),
              rs.getString("ownerName"),
              rs.getString("contactInfo")
              );
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return fastag;
  }
   
  
   // Method to renew a Fastag (update its details)
    public static boolean updateFastag(String vehicleNumber, String ownerName, String contactInfo) {
        boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String query = "UPDATE fastagcreate SET ownerName=?, contactInfo=? WHERE vehicleNumber=?";
            ps = con.prepareStatement(query);
            ps.setString(1, ownerName); // new owner name
            ps.setString(2, contactInfo); // new contact information
            ps.setString(3, vehicleNumber); // the vehicle number to identify the Fastag
            int row = ps.executeUpdate();
            if (row > 0) {
                status = true; // Successfully updated the Fastag
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
