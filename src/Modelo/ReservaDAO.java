/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ReservaDAO {

    public static boolean finalizarServicio(String cedula, String nombreMascota) {
        try {
            ConectarBD conexion = new ConectarBD();

            // 1. Obtener ID de la mascota
            String sqlMascota = "SELECT idMascota FROM mascota WHERE Propietario_cedula = ? AND nombre = ?";
            PreparedStatement stmtMascota = conexion.getConexion().prepareStatement(sqlMascota);
            stmtMascota.setString(1, cedula);
            stmtMascota.setString(2, nombreMascota);
            ResultSet rsMascota = stmtMascota.executeQuery();

            if (!rsMascota.next()) {
                JOptionPane.showMessageDialog(null, "Mascota no encontrada.");
                return false;
            }

            int idMascota = rsMascota.getInt("idMascota");
            rsMascota.close();
            stmtMascota.close();

            // 2. Obtener reserva activa (sin fecha de fin)
            String sqlReserva = "SELECT idReserva FROM reserva WHERE mascota_idMascota = ? AND fecharfin IS NULL";
            PreparedStatement stmtReserva = conexion.getConexion().prepareStatement(sqlReserva);
            stmtReserva.setInt(1, idMascota);
            ResultSet rsReserva = stmtReserva.executeQuery();

            if (!rsReserva.next()) {
                JOptionPane.showMessageDialog(null, "No hay reservas activas para esta mascota.");
                return false;
            }

            int idReserva = rsReserva.getInt("idReserva");
            rsReserva.close();
            stmtReserva.close();

            // 3. Actualizar la fecha de finalización (a la fecha y hora actual)
            String sqlFinalizar = "UPDATE reserva SET fecharfin = NOW() WHERE idReserva = ?";
            PreparedStatement stmtFin = conexion.getConexion().prepareStatement(sqlFinalizar);
            stmtFin.setInt(1, idReserva);
            stmtFin.executeUpdate();
            stmtFin.close();

            // 4. Obtener el ID del servicio relacionado
            String sqlDetalle = "SELECT servicio_idservicio FROM detalle_de_reserva WHERE Reserva_idReserva = ?";
            PreparedStatement stmtDetalle = conexion.getConexion().prepareStatement(sqlDetalle);
            stmtDetalle.setInt(1, idReserva);
            ResultSet rsDetalle = stmtDetalle.executeQuery();

            if (rsDetalle.next()) {
                int idServicio = rsDetalle.getInt("servicio_idservicio");
                rsDetalle.close();
                stmtDetalle.close();

                // 5. Aumentar la disponibilidad del servicio
                String sqlActualizar = "UPDATE servicio SET disponibles = disponibles + 1 WHERE idservicio = ?";
                PreparedStatement stmtActualizar = conexion.getConexion().prepareStatement(sqlActualizar);
                stmtActualizar.setInt(1, idServicio);
                stmtActualizar.executeUpdate();
                stmtActualizar.close();
            }

            conexion.getConexion().close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al finalizar servicio: " + e.getMessage());
            return false;
        }
    }
}
