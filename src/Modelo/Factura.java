package Modelo;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.awt.Desktop;
import java.sql.*;


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
    public void reporteFactura(int idFactura) {
    Document documento = new Document();
    ConectarBD conexion = new ConectarBD();
    try {
        String nombreArchivo = "Factura_" + idFactura + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
        documento.open();

        Paragraph titulo = new Paragraph("REPORTE DE FACTURA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(new Paragraph(" "));

        PreparedStatement sentencia = conexion.getConexion().prepareStatement("SELECT * FROM Factura WHERE idFactura = ?");
        sentencia.setInt(1, idFactura);
        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(70);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            tabla.addCell("ID Factura:");
            tabla.addCell(String.valueOf(resultado.getInt("idFactura")));

            tabla.addCell("Fecha de Pago:");
            tabla.addCell(resultado.getString("fechaPago"));

            tabla.addCell("Monto Pagado:");
            tabla.addCell("$" + resultado.getDouble("montoPagado"));

            tabla.addCell("Método de Pago:");
            tabla.addCell(resultado.getString("metodoPago"));

            tabla.addCell("ID Reserva:");
            tabla.addCell(String.valueOf(resultado.getInt("idReserva")));

            documento.add(tabla);
        } else {
            documento.add(new Paragraph("No se encontró la factura con ID: " + idFactura));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e);
        e.printStackTrace();
    } finally {
        documento.close();
        try {
            conexion.getConexion().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    try {
        File archivo = new File("Factura_" + idFactura + ".pdf");
        if (archivo.exists()) {
            Desktop.getDesktop().open(archivo);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
}
