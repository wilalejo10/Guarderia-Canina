package Modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Servicio {
    int idServicio;
    int aforoMaximo;
    int idTipoServicio;
    int disponibles;
    String tipo;

    public Servicio() {
    }

    public Servicio(int idServicio, int aforoMaximo, int idTipoServicio, int disponibles) {
        this.idServicio = idServicio;
        this.aforoMaximo = aforoMaximo;
        this.idTipoServicio = idTipoServicio;
        this.disponibles = disponibles;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    

    public boolean actualizarDisponibilidad(int id, int nuevaDisponibilidad) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE servicio SET disponibles = ? WHERE idServicio = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setInt(1, nuevaDisponibilidad);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar disponibilidad: " + e.getMessage());
            return false;
        }
    }

    public static Servicio obtenerServicioPorNombre(String nombreServicio) {
        Servicio s = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT s.idServicio, s.aforoMaximo, s.disponibles, t.tipoServicio " +
                         "FROM servicio s JOIN tipo_de_servicio t ON s.Tipo_de_Servicio_idTipo_de_Servicio = t.idTipo_de_Servicio " +
                         "WHERE t.tipoServicio = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, nombreServicio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = new Servicio();
                s.idServicio = rs.getInt("idServicio");
                s.aforoMaximo = rs.getInt("aforoMaximo");
                s.disponibles = rs.getInt("disponibles");
                s.tipo = rs.getString("tipoServicio");
            }

            rs.close();
            stmt.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener servicio: " + e.getMessage());
        }
        return s;
    }
    
    public boolean registrarServicioSolicitado(String cedulaPropietario, int idServicio) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "INSERT INTO servicios_solicitados (cedula_propietario, id_servicio) VALUES (?, ?)";
        PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
        stmt.setString(1, cedulaPropietario);
        stmt.setInt(2, idServicio);
        stmt.executeUpdate();
        stmt.close();
        conexion.getConexion().close();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al registrar servicio solicitado: " + e.getMessage());
        return false;
    }
}
    
    public boolean eliminarServicioSolicitado(String cedulaPropietario, int idServicio) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "DELETE FROM servicios_solicitados WHERE cedula_propietario = ? AND id_servicio = ?";
        PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
        stmt.setString(1, cedulaPropietario);
        stmt.setInt(2, idServicio);
        stmt.executeUpdate();
        stmt.close();
        conexion.getConexion().close();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar servicio solicitado: " + e.getMessage());
        return false;
    }
}

}



