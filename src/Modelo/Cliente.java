package Modelo;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

// Heredar el los datos de la mascota para actualizar?

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
    
    public void crearCliente()
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
    
    public String[] buscarCliente(String cedula, String[] datos)
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cliente WHERE cedula='" + cedula + "'");
            
            if (resultado.next())
            {
                datos[0] = resultado.getString("nombre");
                datos[1] = resultado.getString("correo");
                datos[2] = resultado.getString("direccion");
                datos[3] = resultado.getString("telefono");
                datos[4] = resultado.getString("nombre_mascota");
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
            String instruccion= "UPDATE cliente SET nombre=?, correo=?, direccion=?, telefono=?, nombre_mascota=? WHERE cedula=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setString(2, getCorreo());
            conexion.sentencia.setString(3, getDireccion());
            conexion.sentencia.setString(4, getTelefono());
            //conexion.sentencia.setString(5, get());
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
                String instruccion= "Delete from cliente where cedula=" + cedula;
                conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
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