package com.example.aasthaapp.Models;

public class User {
    String profilepic, username, mail, password, userId, lastMesaage, status, spinner;
    public User(String spinner,String status,String profilepic, String username, String mail, String password, String userId, String lastMesaage) {
        this.profilepic = profilepic;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMesaage = lastMesaage;
        this.status= status;
        this.spinner= spinner;

    }



    // empty constructor
    public  User(){

    }


    //SignUp Constructor
    public User( String username, String mail, String password,String spinner) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.spinner= spinner;

    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilepic() {
        return profilepic;
    }
    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastMesaage() {
        return lastMesaage;
    }

    public void setLastMesaage(String lastMesaage) {
        this.lastMesaage = lastMesaage;
    }
}


