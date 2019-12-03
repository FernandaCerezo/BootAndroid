package com.bootcamp.login.Accounts;

public class Accounts
{
    private String Name;
    private String Description;
    private String Technology;
    private int Image;

    //Constructor
    public Accounts(){}
    public Accounts(String Name, String Description, String Technology, int Image)
    {
        this.Name = Name;
        this.Description = Description;
        this.Technology = Technology;
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

    //Tecnologia
    public String getTechnology() { return Technology; }
    public void setTechnology(String Technology)
    {
        this.Technology = Technology;
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
