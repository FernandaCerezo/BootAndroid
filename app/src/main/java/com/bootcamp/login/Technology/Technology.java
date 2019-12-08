package com.bootcamp.login.Technology;

public class Technology {
    private String name, technologyp;
    private int image;

    public Technology() {
    }

    public Technology(String name, int image, String technologyp) {
        this.name = name;
        this.image = image;
        this.technologyp=technologyp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
