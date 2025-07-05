package Modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Servicio {
     int idServicio;
     int aforoMaximo;
     int idTipoServicio;
     int disponibles;

    public Servicio() {
    }

    public Servicio(int idServicio, int aforoMaximo, int idTipoServicio, int disponibles) {
        this.idServicio = idServicio;
        this.aforoMaximo = aforoMaximo;
        this.idTipoServicio = idTipoServicio;
        this.disponibles = disponibles;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String[] buscarServicio(int id, String[] datos) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM servicio WHERE idservicio=" + id);
            if (resultado.next()) {
                datos[0] = String.valueOf(resultado.getInt("aforoMaximo"));
                datos[1] = String.valueOf(resultado.getInt("idTipoServicio"));
                datos[2] = String.valueOf(resultado.getInt("disponibles"));
            } else {
                JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

    public void actualizarDisponibilidad() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE servicio SET disponibles=? WHERE idservicio=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setInt(1, getDisponibles());
            conexion.sentencia.setInt(2, getIdServicio());
            conexion.sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disponibilidad actualizada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
