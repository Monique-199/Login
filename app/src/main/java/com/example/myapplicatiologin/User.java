package com.example.myapplicatiologin;

public class User {
    public String email;
    public String passwd;
    public String gender;
    public String address;

    public User(){

    }
    public User(String email,String gender,String address,String passwd){
        this.address=address;
        this.email=email;
        this.gender=gender;
        this.passwd=passwd;
    }
}
