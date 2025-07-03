package Modelo;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Cliente extends Persona
{
    String correo, direccion;
    public Cliente()
    {
    }
    
    public Cliente(String correo, String direccion)
    {
        this.correo = correo;
        this.direccion = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void crear()
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "insert into cliente values(?,?,?,?,?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setString(2, getCedula());
            conexion.sentencia.setString(3, getCorreo());
            conexion.sentencia.setString(4, getDireccion());
            conexion.sentencia.setString(5, getTelefono());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Registro Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            conexion.getConexion().close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error SQL"+e,"Información",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error del sistema"+e,"Información",JOptionPane.ERROR_MESSAGE);  
        }
    }
}