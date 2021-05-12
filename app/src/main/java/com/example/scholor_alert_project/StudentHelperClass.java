package com.example.scholor_alert_project;

public class StudentHelperClass
{
    String email,pass,cpass;


    public StudentHelperClass()
    {

    }

    public StudentHelperClass(String email, String pass, String cpass)
    {
        this.email = email;
        this.pass = pass;
        this.cpass = cpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }
}
