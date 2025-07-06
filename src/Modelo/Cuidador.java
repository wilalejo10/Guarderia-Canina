package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Cuidador extends Persona
{
    String turno, especialidad, cargo;
    int edad;

    public Cuidador()
    {
    }

    public Cuidador(String nombre, String cedula, String telefono, int edad, String turno, String especialidad, String cargo) {
        super(nombre, cedula, telefono);
        this.edad = edad;
        this.turno = turno;
        this.especialidad = especialidad;
        this.cargo = cargo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    
    public void crearCuidador()
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO cuidador (cedulaCuidador, nombre, edad, telefono, turno, especialidad, cargo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getCedula());
            conexion.sentencia.setString(2, getNombre());
            conexion.sentencia.setInt(3, getEdad());
            conexion.sentencia.setString(4, getTelefono());
            conexion.sentencia.setString(5, getTurno());
            conexion.sentencia.setString(6, getEspecialidad());
            conexion.sentencia.setString(7, getCargo());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Cuidador registrado correctamente.");
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

    public String[] buscarCuidador(String cedulaCuidador, String[] datos)
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Cuidador WHERE cedulaCuidador = '" + cedulaCuidador + "'");
            
            if (resultado.next())
            {
                datos[0] = resultado.getString("nombre");
                datos[1] = String.valueOf(resultado.getInt("edad"));
                datos[2] = resultado.getString("telefono");
                datos[3] = resultado.getString("turno");
                datos[4] = resultado.getString("especialidad");
                datos[5] = resultado.getString("cargo");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cuidador no encontrado");
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

    public void actualizarCuidador(String cedulaCuidador)
    {
        try
        {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE cuidador SET nombre=?, edad=?, telefono=?, turno=?, especialidad=?, cargo=? WHERE cedulaCuidador=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setInt(2, getEdad());
            conexion.sentencia.setString(3, getTelefono());
            conexion.sentencia.setString(4, getTurno());
            conexion.sentencia.setString(5, getEspecialidad());
            conexion.sentencia.setString(6, getCargo());
            conexion.sentencia.setString(7, getCedula());
            conexion.sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            conexion.getConexion().close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

       public boolean buscarPorCedulaCI(String cedulaBuscada) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM Cuidador WHERE cedulaCuidador = '" + cedulaBuscada + "'";
        Statement stmt = conexion.getConexion().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            setCedula(rs.getString("cedulaCuidador"));
            setNombre(rs.getString("nombre"));
            setTelefono(rs.getString("telefono"));
            setEdad(rs.getInt("edad"));
            setTurno(rs.getString("turno"));
            setEspecialidad(rs.getString("especialidad"));
            setCargo(rs.getString("cargo"));
            rs.close();
            stmt.close();
            conexion.getConexion().close();
            return true;
        } else {
            return false;
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar cuidador: " + e.getMessage());
        return false;
    }
}

    
 

       
    
    public void eliminarCuidador(String cedulaCuidador)
    {
        int seleccion = JOptionPane.showOptionDialog(null,"¿Desea ELIMINAR EL REGISTRO (Si/No)","Seleccione una opción",
            JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Si", "No"},"Si");
        if((seleccion + 1) == 1)
        {
            try
            {
                ConectarBD conexion = new ConectarBD();
                String instruccion = "DELETE FROM cuidador WHERE cedulaCuidador=?";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                JOptionPane.showMessageDialog(null, "Cuidador eliminado correctamente.");
                conexion.getConexion().close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Registro NO ELIMINADO", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
