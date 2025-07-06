package Modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

public class Propietario extends Persona {
     String correo, direccion;
     int idMascota;
     String nombreMascota;

    public Propietario() {
    }

    public Propietario(String nombre, String cedula, String telefono, String direccion, String correo) {
        super(nombre, cedula, telefono);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }

    public String getNombreMascota() { return nombreMascota; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }

    public void crearPropietario() {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "INSERT INTO Propietario (cedula, nombre, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getCedula());
            conexion.sentencia.setString(2, getNombre());
            conexion.sentencia.setString(3, getDireccion());
            conexion.sentencia.setString(4, getTelefono());
            conexion.sentencia.setString(5, getCorreo());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Propietario registrado correctamente.");
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

public String[] buscarPropietario(String cedula, String[] datos) {
    try {
        ConectarBD conexion = new ConectarBD();
        Statement sentencia = conexion.getConexion().createStatement();
        String sql = "SELECT * FROM Propietario WHERE cedula = '" + cedula + "'";
        ResultSet resultado = sentencia.executeQuery(sql);

        if (resultado.next()) {
            datos[0] = resultado.getString("nombre");
            datos[1] = resultado.getString("direccion");
            datos[2] = resultado.getString("telefono");
            datos[3] = resultado.getString("correo");

            // Buscar datos de la mascota (si tienes este método implementado)
            buscarMascotaDePropietario(cedula); // método que tú tienes en la clase
            datos[4] = getNombreMascota();      // nombre de la mascota encontrada
            datos[5] = String.valueOf(getIdMascota()); // ID convertido a texto
        } else {
            JOptionPane.showMessageDialog(null, "Propietario no encontrado");
        }

        resultado.close();
        sentencia.close();
        conexion.getConexion().close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
    }

    return datos;
}


    public void actualizarCliente(String cedula) {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE Propietario SET nombre=?, direccion=?, telefono=?, correo=? WHERE cedula=?";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, getNombre());
            conexion.sentencia.setString(2, getDireccion());
            conexion.sentencia.setString(3, getTelefono());
            conexion.sentencia.setString(4, getCorreo());
            conexion.sentencia.setString(5, getCedula());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void eliminarCliente(String cedula) {
        int seleccion = JOptionPane.showOptionDialog(null, "¿Desea ELIMINAR EL REGISTRO (Si/No)", "Seleccione una opción",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
        if ((seleccion + 1) == 1) {
            try {
                ConectarBD conexion = new ConectarBD();
                String instruccion = "DELETE FROM Propietario WHERE cedula=?";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.setString(1, cedula);
                conexion.sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL: " + e, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registro NO ELIMINADO", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public boolean buscarPorCedula(String cedulaBuscada) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM Propietario WHERE cedula = '" + cedulaBuscada + "'";
        Statement stmt = conexion.getConexion().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            this.setCedula(rs.getString("cedula"));
            this.setNombre(rs.getString("nombre"));
            this.setDireccion(rs.getString("direccion"));
            this.setTelefono(rs.getString("telefono"));
            this.setCorreo(rs.getString("correo"));
            rs.close();
            stmt.close();
            conexion.getConexion().close();
            return true;
        } else {
            rs.close();
            stmt.close();
            conexion.getConexion().close();
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar propietario: " + e.getMessage());
        return false;
    }
}

    
    
    private void buscarMascotaDePropietario(String cedulaPropietario) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery(
                "SELECT idMascota, nombre FROM mascota WHERE Propietario_cedula='" + cedulaPropietario + "' LIMIT 1"
            );

            if (resultado.next()) {
                this.idMascota = resultado.getInt("idMascota");
                this.nombreMascota = resultado.getString("nombre");
            }

            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mascota del propietario: " + e.getMessage());
        }
    }
}
