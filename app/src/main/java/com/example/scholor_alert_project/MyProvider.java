package com.example.scholor_alert_project;

public class MyProvider {
    String Userid;
    String Password;
    String  OrgName;
    String Email;

    public MyProvider() {
    }

    public MyProvider(String userid, String password, String orgName, String email) {
        Userid = userid;
        Password = password;
        OrgName = orgName;
        Email = email;
    }

    @Override
    public String toString() {
        return "MyProvider{" +
                "Userid='" + Userid + '\'' +
                ", Password='" + Password + '\'' +
                ", OrgName='" + OrgName + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
