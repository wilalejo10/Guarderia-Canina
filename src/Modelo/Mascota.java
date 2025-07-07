package Modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Mascota {
    int idMascota;
    String nombre;
    String raza;
 String edad;
    String peso;
    String propietarioCedula;

    public Mascota() {
    }

    public Mascota(int idMascota, String nombre, String raza, String edad, String peso, String propietarioCedula) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.propietarioCedula = propietarioCedula;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPropietarioCedula() {
        return propietarioCedula;
    }

    public void setPropietarioCedula(String propietarioCedula) {
        this.propietarioCedula = propietarioCedula;
    }

    
public static int obtenerIdMascota(String cedulaProp, String nombreMascota) {
    int id = -1;
    try {
        ConectarBD conexion = new ConectarBD();
        Statement stmt = conexion.getConexion().createStatement();
        String sql = "SELECT idMascota FROM mascota " +
                     "WHERE Propietario_cedula = '" + cedulaProp + "' " +
                     "AND nombre = '" + nombreMascota + "'";

        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            id = rs.getInt("idMascota");
        }

        rs.close();
        stmt.close();
        conexion.getConexion().close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error obteniendo ID de mascota: " + e.getMessage());
    }
    return id;
}


    
    
    
    
public void crearMascota() {
    try {
        ConectarBD conexion = new ConectarBD();
        String instruccion = "INSERT INTO mascota (idMascota, nombre, raza, edad, peso, Propietario_cedula) VALUES (?, ?, ?, ?, ?, ?)";
        conexion.setSentencia(conexion.getConexion().prepareStatement(instruccion));
        conexion.getSentencia().setInt(1, idMascota);
        conexion.getSentencia().setString(2, nombre);
        conexion.getSentencia().setString(3, raza);
        conexion.getSentencia().setString(4, edad);
        conexion.getSentencia().setString(5, peso);
        conexion.getSentencia().setString(6, propietarioCedula);
        conexion.getSentencia().execute();
        conexion.getConexion().close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL al registrar mascota: " + e.getMessage());
    }
}


    public String[] buscarMascota(int id, String[] datos) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM mascota WHERE idMascota=" + id);
            if (resultado.next()) {
                datos[0] = resultado.getString("nombre");
                datos[1] = resultado.getString("raza");
                datos[2] = resultado.getString("edad");
                datos[3] = resultado.getString("peso");
                datos[4] = resultado.getString("Propietario_cedula");
            } else {
                JOptionPane.showMessageDialog(null, "Mascota no encontrada.");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

   
    public void actualizarMascota() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE mascota SET nombre=?, raza=?, edad=?, peso=?, Propietario_cedula=? WHERE idMascota=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setString(2, getRaza());
            conexion.sentencia.setString(3, getEdad());
            conexion.sentencia.setString(4, getPeso());
            conexion.sentencia.setString(5, getPropietarioCedula());
            conexion.sentencia.setInt(6, getIdMascota());
            conexion.sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mascota actualizada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Mascota buscarPorCedulaPropietario(String cedulaPropietario) {
    Mascota mascota = null;
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM mascota WHERE Propietario_cedula = '" + cedulaPropietario + "' LIMIT 1";
        Statement stmt = conexion.getConexion().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            mascota = new Mascota(
                rs.getInt("idMascota"),
                rs.getString("nombre"),
                rs.getString("raza"),
                rs.getString("edad"),
                rs.getString("peso"),
                cedulaPropietario
            );
        }

        rs.close();
        stmt.close();
        conexion.getConexion().close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar mascota: " + e.getMessage());
    }

    return mascota;
}

    
    

    public void eliminarMascota(int id) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta mascota?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ConectarBD conexion = new ConectarBD();
                String instruccion = "DELETE FROM mascota WHERE idMascota=?";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.setInt(1, id);
                conexion.sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Mascota eliminada correctamente.");
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
