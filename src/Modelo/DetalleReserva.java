package Modelo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DetalleReserva {
     int idReserva;
     int idServicio;
     int idHabitaculo;

    public DetalleReserva() {
    }

    public DetalleReserva(int idReserva, int idServicio, int idHabitaculo) {
        this.idReserva = idReserva;
        this.idServicio = idServicio;
        this.idHabitaculo = idHabitaculo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdHabitaculo() {
        return idHabitaculo;
    }

    public void setIdHabitaculo(int idHabitaculo) {
        this.idHabitaculo = idHabitaculo;
    }

    
    public void crearDetalleReserva() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO DetalleReserva (idReserva, idservicio, idHabitaculo) VALUES (?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setInt(1, getIdReserva());
            conexion.sentencia.setInt(2, getIdServicio());
            conexion.sentencia.setInt(3, getIdHabitaculo());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Detalle de reserva registrado.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Buscar todos los servicios y habitáculos de una reserva
    public static void consultarDetalles(int idReserva) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM DetalleReserva WHERE idReserva=" + idReserva);
            System.out.println("Servicios y habitáculos para la reserva #" + idReserva + ":");
            while (resultado.next()) {
                System.out.println("- Servicio ID: " + resultado.getInt("idservicio") + ", Habitáculo ID: " + resultado.getInt("idHabitaculo"));
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar detalles: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
