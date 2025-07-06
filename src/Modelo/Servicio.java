package Modelo;

import java.sql.*;
import java.util.ArrayList;
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
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return datos;
    }

    // registrar un nuevo servicio
    public boolean registrarServicio() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO servicio (aforoMaximo, idTipoServicio, disponibles) VALUES (?, ?, ?)";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, aforoMaximo);
            pst.setInt(2, idTipoServicio);
            pst.setInt(3, disponibles);
            pst.executeUpdate();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar servicio: " + e);
            return false;
        }
    }

    // listar servicios
    public static ArrayList<Servicio> listarServicios() {
        ArrayList<Servicio> lista = new ArrayList<>();
        try {
            ConectarBD conexion = new ConectarBD();
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM servicio");
            while (rs.next()) {
                Servicio s = new Servicio(
                    rs.getInt("idservicio"),
                    rs.getInt("aforoMaximo"),
                    rs.getInt("idTipoServicio"),
                    rs.getInt("disponibles")
                );
                lista.add(s);
            }
            rs.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar servicios: " + e);
        }
        return lista;
    }

    // actualizar todos los campos del servicio
    public boolean actualizarServicio() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE servicio SET aforoMaximo=?, idTipoServicio=?, disponibles=? WHERE idservicio=?";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, aforoMaximo);
            pst.setInt(2, idTipoServicio);
            pst.setInt(3, disponibles);
            pst.setInt(4, idServicio);
            pst.executeUpdate();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar servicio: " + e);
            return false;
        }
    }

    // eliminar un servicio
    public boolean eliminarServicio() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "DELETE FROM servicio WHERE idservicio=?";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, idServicio);
            pst.executeUpdate();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar servicio: " + e);
            return false;
        }
    }
}
