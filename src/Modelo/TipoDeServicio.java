package Modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TipoDeServicio {
    int idTipoServicio;
    String tipoServicio;
    String costoUnidad;

    public TipoDeServicio() {
    }

    public TipoDeServicio(int idTipoServicio, String tipoServicio, String costoUnidad) {
        this.idTipoServicio = idTipoServicio;
        this.tipoServicio = tipoServicio;
        this.costoUnidad = costoUnidad;
    }

    // Getters y Setters
    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(String costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    @Override
    public String toString() {
        return tipoServicio;
    }

    // Crear nuevo tipo de servicio
    public boolean registrarTipoServicio() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO tipodeservicio (tipoServicio, costoUnidad) VALUES (?, ?)";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setString(1, tipoServicio);
            pst.setString(2, costoUnidad);
            pst.executeUpdate();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar tipo de servicio: " + e);
            return false;
        }
    }

    // Buscar por ID
    public boolean buscarTipoServicio(int id) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM tipodeservicio WHERE idTipoServicio=?";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                this.idTipoServicio = rs.getInt("idTipoServicio");
                this.tipoServicio = rs.getString("tipoServicio");
                this.costoUnidad = rs.getString("costoUnidad");
                rs.close();
                conexion.getConexion().close();
                return true;
            } else {
                rs.close();
                conexion.getConexion().close();
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar tipo de servicio: " + e);
            return false;
        }
    }

    // Listar todos los tipos de servicio
    public static ArrayList<TipoDeServicio> listarTiposServicio() {
        ArrayList<TipoDeServicio> lista = new ArrayList<>();
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM tipodeservicio";
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TipoDeServicio tipo = new TipoDeServicio(
                    rs.getInt("idTipoServicio"),
                    rs.getString("tipoServicio"),
                    rs.getString("costoUnidad")
                );
                lista.add(tipo);
            }
            rs.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar tipos de servicio: " + e);
        }
        return lista;
    }

    // Actualizar tipo de servicio
    public boolean actualizarTipoServicio() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE tipodeservicio SET tipoServicio=?, costoUnidad=? WHERE idTipoServicio=?";
            PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
            pst.setString(1, tipoServicio);
            pst.setString(2, costoUnidad);
            pst.setInt(3, idTipoServicio);
            pst.executeUpdate();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar tipo de servicio: " + e);
            return false;
        }
    }
}
