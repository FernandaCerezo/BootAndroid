package com.bootcamp.login.firebase.models;

/**
 * Created by alvaromunoz
 * Date 2019-11-22.
 */
public class User {

    private String Name;
    private String Email;
    private String Account;
    private String Technologyp;

    public User(){
        // Default constructor required
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getTechnologyp() {
        return Technologyp;
    }

    public void setTechnologyp(String technologyp) {
        Technologyp = technologyp;
    }
}
