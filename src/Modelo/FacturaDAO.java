package Modelo;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class FacturaDAO {

    public static Object[][] obtenerServiciosDePropietario(String cedula) {
        List<Object[]> lista = new ArrayList<>();
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT m.nombre AS mascota, t.tipoServicio, t.costoUnidad " +
             "FROM mascota m " +
             "JOIN reserva r ON r.mascota_idMascota = m.idMascota " +
             "JOIN detallereserva dr ON dr.idReserva = r.idReserva " +
             "JOIN servicio s ON s.idservicio = dr.idservicio " +
             "JOIN tipodeservicio t ON s.idTipoServicio = t.idTipoServicio " +
             "WHERE m.Propietario_cedula = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("mascota"),
                    rs.getString("tipoServicio"),
                    rs.getDouble("costoUnidad")
                };
                lista.add(fila);
            }
            rs.close();
            stmt.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obteniendo servicios: " + e.getMessage());
        }
        return lista.toArray(new Object[0][]);
    }

    public static int obtenerFacturaPorCedula(String cedula) {
        int idFactura = -1;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT f.idFactura FROM factura f " +
                     "JOIN reserva r ON r.idReserva = f.idReserva " +
                     "JOIN mascota m ON m.idMascota = r.mascota_idMascota " +
                     "WHERE m.Propietario_cedula = ? LIMIT 1";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idFactura = rs.getInt("idFactura");
            }
            rs.close();
            stmt.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obteniendo factura: " + e.getMessage());
        }
        return idFactura;
    }

    public static boolean registrarRecaudo(int idFactura, double monto) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO recaudo (fechaPago, montoPagado, factura_idFactura) VALUES (NOW(), ?, ?)";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setDouble(1, monto);
            stmt.setInt(2, idFactura);
            stmt.executeUpdate();
            stmt.close();
            conexion.getConexion().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error registrando recaudo: " + e.getMessage());
            return false;
        }
    }

    public static double obtenerTotalRecaudado(int idFactura) {
        double total = 0;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT SUM(montoPagado) as total FROM recaudo WHERE factura_idFactura = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
            rs.close();
            stmt.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obteniendo total recaudado: " + e.getMessage());
        }
        return total;
    }

    public static Object[][] obtenerPagosHistorial(int idFactura) {
        List<Object[]> lista = new ArrayList<>();
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT fechaPago, montoPagado FROM recaudo WHERE factura_idFactura = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getDate("fechaPago"),
                    rs.getDouble("montoPagado")
                };
                lista.add(fila);
            }
            rs.close();
            stmt.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obteniendo pagos: " + e.getMessage());
        }
        return lista.toArray(new Object[0][]);
    }

    public static void generarPDF(String cedula, Object[][] servicios, double total, double abonado, String metodo) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Factura_" + cedula + ".pdf"));
            document.open();

            document.add(new Paragraph("Factura Guarderia Canina"));
            document.add(new Paragraph("Cedula: " + cedula));
            document.add(new Paragraph("Metodo de pago: " + metodo));
            document.add(new Paragraph("----------------------------"));

            PdfPTable table = new PdfPTable(3);
            table.addCell("Mascota");
            table.addCell("Servicio");
            table.addCell("Precio");

            for (Object[] fila : servicios) {
                table.addCell(fila[0].toString());
                table.addCell(fila[1].toString());
                table.addCell(fila[2].toString());
            }
            document.add(table);

            document.add(new Paragraph("Total a pagar: $" + total));
            document.add(new Paragraph("Monto abonado: $" + abonado));
            document.add(new Paragraph("Saldo pendiente: $" + (total - abonado)));

            document.close();
            JOptionPane.showMessageDialog(null, "Factura PDF generada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }
}
