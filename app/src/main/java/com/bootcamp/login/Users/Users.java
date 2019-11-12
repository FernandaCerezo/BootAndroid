package com.bootcamp.login.Users;

import java.util.ArrayList;

public class Users extends ArrayList<Users> {
    private String name, technology/*, image*/;

    public Users() {
        //constructor necesario
    }

    public Users(int initialCapacity, String name, String technology, String image) {
        super(initialCapacity);
        this.name = name;
        this.technology = technology;
        /*this.image=image;*/
    }

/*    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/

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
}
