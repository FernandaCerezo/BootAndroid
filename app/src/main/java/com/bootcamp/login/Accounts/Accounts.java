package com.bootcamp.login.Accounts;

public class Accounts
{
    private String Name;
    private String Description;
    private int Image;

    //Constructor
    public Accounts(){}
    public Accounts(String Name, String Description, int Image)
    {
        this.Name = Name;
        this.Description = Description;
        this.Image = Image;
    }

    //Nombre
    public String getName()
    {
        return Name;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }

    //Descripcion
    public String getDescription()
    {
        return Description;
    }
    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    //Imagenes
    public int getImage()
    {
        return Image;
    }
    public void setDescription(int Image)
    {
        this.Image = Image;
    }
}
