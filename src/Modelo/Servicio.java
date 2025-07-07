package Modelo;

import java.sql.*;
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
    
   public static Object[][] obtenerDatosAforo() {
    Object[][] datos = null;
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT t.tipoServicio, s.disponibles " +
                     "FROM servicio s " +
                     "JOIN tipodeservicio t ON s.idTipoServicio = t.idTipoServicio";
        PreparedStatement stmt = conexion.getConexion().prepareStatement(sql,
                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery();

        // Obtener cantidad de filas
        rs.last();
        int filas = rs.getRow();
        rs.beforeFirst();

        datos = new Object[filas][2];
        int i = 0;
        while (rs.next()) {
            datos[i][0] = rs.getString("tipoServicio");
            datos[i][1] = rs.getInt("disponibles");
            i++;
        }

        rs.close();
        stmt.close();
        conexion.getConexion().close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar aforo: " + e.getMessage());
    }

    return datos;
}
    
    
    public static Servicio obtenerServicioPorNombre(String nombreServicio) {
        Servicio s = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT s.idservicio, s.aforoMaximo, s.disponibles, t.tipoServicio " +
                         "FROM servicio s " +
                         "JOIN tipodeservicio t ON s.idTipoServicio = t.idTipoServicio " +
                         "WHERE t.tipoServicio = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, nombreServicio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = new Servicio();
                s.idServicio = rs.getInt("idservicio");
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

    
    public boolean reducirDisponibilidad() {
        if (disponibles <= 0) return false;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE servicio SET disponibles = disponibles - 1 WHERE idservicio = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setInt(1, idServicio);
            int filas = stmt.executeUpdate();
            stmt.close();
            conexion.getConexion().close();
            if (filas > 0) {
                disponibles--;
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar disponibilidad: " + e.getMessage());
        }
        return false;
    }


    public boolean actualizarDisponibilidad(int nuevaDisponibilidad) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE servicio SET disponibles = ? WHERE idservicio = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setInt(1, nuevaDisponibilidad);
            stmt.setInt(2, idServicio);
            stmt.executeUpdate();
            stmt.close();
            conexion.getConexion().close();
            this.disponibles = nuevaDisponibilidad;
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al establecer nueva disponibilidad: " + e.getMessage());
            return false;
        }
    }
}





