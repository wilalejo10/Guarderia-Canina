package modelo;

import Modelo.ConectarBD;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.JOptionPane;

public class FacturaPDF {

    public static void generarFactura(String cedula, String metodoPago) {
        Document documento = new Document();
        try {
            String ruta = "factura_" + cedula + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

           
            Paragraph titulo = new Paragraph("FACTURA - Guardería Canina", FontFactory.getFont("Arial", 20, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); 

            
            ConectarBD conexion = new ConectarBD();
            String sqlProp = "SELECT nombre, direccion, telefono, correo FROM propietario WHERE cedula=?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sqlProp);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                documento.add(new Paragraph("Cliente: " + rs.getString("nombre")));
                documento.add(new Paragraph("Cédula: " + cedula));
                documento.add(new Paragraph("Dirección: " + rs.getString("direccion")));
                documento.add(new Paragraph("Teléfono: " + rs.getString("telefono")));
                documento.add(new Paragraph("Correo: " + rs.getString("correo")));
                documento.add(new Paragraph(" "));
            } else {
                JOptionPane.showMessageDialog(null, "Cédula no encontrada.");
                documento.close();
                return;
            }
            rs.close();
            stmt.close();

            
            documento.add(new Paragraph("Servicios Prestados:", FontFactory.getFont("Arial", 14, Font.BOLD)));

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidths(new float[]{3, 3, 2});
            tabla.addCell("Mascota");
            tabla.addCell("Servicio");
            tabla.addCell("Precio");

            double total = 0;
            String sqlServicios = "SELECT m.nombre AS mascota, t.tipoServicio, t.costoUnidad " +
                                  "FROM mascota m " +
                                  "JOIN servicios_solicitados ss ON ss.id_mascota = m.idMascota " +
                                  "JOIN servicio s ON s.idservicio = ss.id_servicio " +
                                  "JOIN tipodeservicio t ON s.idTipoServicio = t.idTipoServicio " +
                                  "WHERE m.Propietario_cedula = ?";
            stmt = conexion.getConexion().prepareStatement(sqlServicios);
            stmt.setString(1, cedula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String mascota = rs.getString("mascota");
                String tipo = rs.getString("tipoServicio");
                String costo = rs.getString("costoUnidad");

                tabla.addCell(mascota);
                tabla.addCell(tipo);
                tabla.addCell(costo);

                total += Double.parseDouble(costo.replaceAll("[^\\d.]", ""));
            }

            documento.add(tabla);
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph("Método de pago: " + metodoPago));
            documento.add(new Paragraph("Total a pagar: $" + total + " COP", FontFactory.getFont("Arial", 12, Font.BOLD)));

            documento.close();
            rs.close();
            conexion.getConexion().close();

            JOptionPane.showMessageDialog(null, "Factura generada correctamente: " + ruta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar factura: " + e.getMessage());
        }
    }
}
