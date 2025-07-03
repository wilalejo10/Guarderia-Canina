package Modelo;

public class Persona 
{
    String nombre, cedula, telefono;
    int edad;
    
    public Persona()
    {
    }
    
    public Persona(String nombre, String cedula, String telefono, int edad)
    {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getCedula() 
    {
        return cedula;
    }

    public void setCedula(String cedula) 
    {
        this.cedula = cedula;
    }

    public String getTelefono() 
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    public int getEdad() 
    {
        return edad;
    }

    public void setEdad(int edad) 
    {
        this.edad = edad;
    }  
}
