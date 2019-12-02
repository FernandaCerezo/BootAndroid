package com.bootcamp.login.Users;

import java.util.ArrayList;

public class Users extends ArrayList<Users> {
    public String name, technology, image;
    private String rol, technologyp, grade, account, startDate, endDate;

    public Users() {
        super();
    }

    public Users(int initialCapacity, String name, String technology, String image, String rol, String technologyEsp, String grade, String account, String startDate, String endDate) {
        super(initialCapacity);
        this.name = name;
        this.technology = technology;
        this.image = image;
        this.rol = rol;
        this.technologyp = technologyp;
        this.grade = grade;
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTechnologyp() {
        return technologyp;
    }

    public void setTechnologyp(String technologyEsp) {
        this.technologyp = technologyp;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
