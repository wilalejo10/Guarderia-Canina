package Modelo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class Factura {
     int idFactura;
     Timestamp fechaPago;
     double montoPagado;
     String metodoPago;
     int idReserva;

    public Factura() {
    }

    public Factura(int idFactura, Timestamp fechaPago, double montoPagado, String metodoPago, int idReserva) {
        this.idFactura = idFactura;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
        this.idReserva = idReserva;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

  
    public void crearFactura() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO Factura (idFactura, fechaPago, montoPagado, metodoPago, idReserva) VALUES (?, ?, ?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setInt(1, getIdFactura());
            conexion.sentencia.setTimestamp(2, getFechaPago());
            conexion.sentencia.setDouble(3, getMontoPagado());
            conexion.sentencia.setString(4, getMetodoPago());
            conexion.sentencia.setInt(5, getIdReserva());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Factura registrada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
        }
    }

  
    public String[] buscarFactura(int id, String[] datos) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Factura WHERE idFactura=" + id);
            if (resultado.next()) {
                datos[0] = resultado.getString("fechaPago");
                datos[1] = String.valueOf(resultado.getDouble("montoPagado"));
                datos[2] = resultado.getString("metodoPago");
                datos[3] = String.valueOf(resultado.getInt("idReserva"));
            } else {
                JOptionPane.showMessageDialog(null, "Factura no encontrada.");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return datos;
    }


    public void actualizarFactura() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE Factura SET fechaPago=?, montoPagado=?, metodoPago=?, idReserva=? WHERE idFactura=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setTimestamp(1, getFechaPago());
            conexion.sentencia.setDouble(2, getMontoPagado());
            conexion.sentencia.setString(3, getMetodoPago());
            conexion.sentencia.setInt(4, getIdReserva());
            conexion.sentencia.setInt(5, getIdFactura());
            conexion.sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Factura actualizada correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
        }
    }
}
