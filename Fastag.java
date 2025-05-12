/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fastagcardcreationproject;

/**
 *
 * @author Yuvraj
 */
public class Fastag {
    private String vehicleNumber;
        private  String ownerName;
        private String contactInfo;
        
        public Fastag(String vehicleNumber,String ownerName,String contactInfo)
        {
    this.vehicleNumber=vehicleNumber;
    this.ownerName=ownerName;
    this.contactInfo=contactInfo;
}
        public String getVehiclenumber()
        {
            return vehicleNumber;
        }
         public String getOwnername()
        {
            return ownerName;
        }
          public String getContactInfo()
        {
            return contactInfo;
        }
}
