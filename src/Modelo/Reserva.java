/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Timestamp;

public class Reserva {
  int idReserva;
  Timestamp fechaInicio;
  Timestamp fechaFin;
  int idMascota;
  String cedulaCuidador;

    public Reserva() {
    }

    public Reserva(int idReserva, Timestamp fechaInicio, Timestamp fechaFin, int idMascota, String cedulaCuidador) {
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idMascota = idMascota;
        this.cedulaCuidador = cedulaCuidador;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getCedulaCuidador() {
        return cedulaCuidador;
    }

    public void setCedulaCuidador(String cedulaCuidador) {
        this.cedulaCuidador = cedulaCuidador;
    }

   
    public void crearReserva() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO Reserva (idReserva, fechainicio, fecharfin, mascota_idMascota, cuidador_cedulaCuidador) VALUES (?, ?, ?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setInt(1, getIdReserva());
            conexion.sentencia.setTimestamp(2, getFechaInicio());
            conexion.sentencia.setTimestamp(3, getFechaFin());
            conexion.sentencia.setInt(4, getIdMascota());
            conexion.sentencia.setString(5, getCedulaCuidador());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Reserva registrada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public String[] buscarReserva(int id, String[] datos) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Reserva WHERE idReserva=" + id);
            if (resultado.next()) {
                datos[0] = resultado.getString("fechainicio");
                datos[1] = resultado.getString("fecharfin");
                datos[2] = String.valueOf(resultado.getInt("mascota_idMascota"));
                datos[3] = resultado.getString("cuidador_cedulaCuidador");
            } else {
                JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

 
    public void actualizarReserva() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE Reserva SET fechainicio=?, fecharfin=?, mascota_idMascota=?, cuidador_cedulaCuidador=? WHERE idReserva=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setTimestamp(1, getFechaInicio());
            conexion.sentencia.setTimestamp(2, getFechaFin());
            conexion.sentencia.setInt(3, getIdMascota());
            conexion.sentencia.setString(4, getCedulaCuidador());
            conexion.sentencia.setInt(5, getIdReserva());
            conexion.sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

  
    public void eliminarReserva(int id) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta reserva?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ConectarBD conexion = new ConectarBD();
                String instruccion = "DELETE FROM Reserva WHERE idReserva=?";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.setInt(1, id);
                conexion.sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
