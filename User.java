/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fastagcardcreationproject;

/**
 *
 * @author Yuvraj
 */
public class User {
    private String name;
    private String email;
    private String username;
    private String password;
   
    
    public User()
    {
        
    }
    public User( String name,String email,String username,String password)
    {
        this.name=name;
        this.email=email;
        this.username=username;
        this.password=password;
    }
     public String getName()
    {
        return(name);
    }
   
    public String getEmail()
    {
        return(email);
    }
        public String getUsername()
    {
        return(username);
    }
   
    public String getPassword()
    {
        return(password);
    }
}
