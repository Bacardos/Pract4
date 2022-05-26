package com.example.praktika4.DATA;

public class User {
    private String email,pass,name,phone;

    User(String email,String pass,String name,String phone){
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.phone = phone;

    }
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
