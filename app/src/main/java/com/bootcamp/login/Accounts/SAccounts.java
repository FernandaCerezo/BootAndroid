package com.bootcamp.login.Accounts;

public class SAccounts
{
    private String Name;
    private String Description;
    private String Technology;

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
