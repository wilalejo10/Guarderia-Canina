package Modelo;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

// Heredar el los datos de la mascota para actualizar?

public class Propietario extends Persona
{
    String correo, direccion;
    public Propietario()
    {
    }
    
    public Propietario(String nombre, String cedula, String telefono, String direccion, String correo)
    {
        super(nombre, cedula, telefono);
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
    
    public void crearPropietario()
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO Propietario (cedula, nombre, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getCedula());
            conexion.sentencia.setString(2, getNombre());
            conexion.sentencia.setString(3, getDireccion());
            conexion.sentencia.setString(4, getTelefono());
            conexion.sentencia.setString(5, getCorreo());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Propietario registrado correctamente.");
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
    
    public String[] buscarPropietario(String cedula, String[] datos)
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Propietario WHERE cedula='" + cedula + "'");
            
            if (resultado.next())
            {
                datos[0] = resultado.getString("nombre");
                datos[1] = resultado.getString("direccion");
                datos[2] = resultado.getString("telefono");
                datos[3] = resultado.getString("correo");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }
            resultado.close();
            conexion.getConexion().close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Información", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }
    
    public void actualizarCliente(String cedula)
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            String instruccion= "UPDATE Propietario SET nombre=?, direccion=?, telefono=?, correo=? WHERE cedula=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setString(2, getDireccion());
            conexion.sentencia.setString(3, getTelefono());
            conexion.sentencia.setString(4, getCorreo());
            conexion.sentencia.setString(5, getCedula());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            conexion.getConexion().close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void eliminarCliente(String cedula)
    {
        int seleccion = JOptionPane.showOptionDialog(null,"¿Desea ELIMINAR EL REGISTRO (Si/No)","Seleccione una opción",
	JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Si", "No"},"Si");
        if((seleccion + 1) == 1)
        {
            try
            {
                ConectarBD conexion=new ConectarBD();
                String instruccion= "DELETE FROM Propietario WHERE cedula=?";
                conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registro Eliminado","Información",JOptionPane.INFORMATION_MESSAGE);
                conexion.getConexion().close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Error SQL: " +e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Registro NO ELIMINADO", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}