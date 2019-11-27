package com.bootcamp.login.Accounts;

public class Accounts
{
    private String Name;
    private String Description;
    private String Technology;

    //Constructor
    public Accounts(){}
    public Accounts(String Name, String Description, String Technology)
    {
        this.Name = Name;
        this.Description = Description;
        this.Technology = Technology;
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
}
