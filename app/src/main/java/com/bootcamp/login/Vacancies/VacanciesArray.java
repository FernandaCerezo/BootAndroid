package com.bootcamp.login.Vacancies;

public class VacanciesArray {
    public String name, technology, technologyp;
    int image;
    public VacanciesArray() {
    }

    public VacanciesArray(String name, String technology, int image, String technologyp) {
        this.name = name;
        this.technology = technology;
        this.image = image;
        this.technologyp=technologyp;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTechnologyp() {
        return technologyp;
    }

    public void setTechnologyp(String technologyp) {
        this.technologyp = technologyp;
    }
}
