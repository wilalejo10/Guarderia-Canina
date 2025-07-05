/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Habitaculo {
     int idHabitaculo;
     int idServicio;

    public Habitaculo() {
    }

    public Habitaculo(int idHabitaculo, int idServicio) {
        this.idHabitaculo = idHabitaculo;
        this.idServicio = idServicio;
    }

    public int getIdHabitaculo() {
        return idHabitaculo;
    }

    public void setIdHabitaculo(int idHabitaculo) {
        this.idHabitaculo = idHabitaculo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String[] buscarHabitaculo(int id, String[] datos) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Habitaculo WHERE idHabitaculo=" + id);
            if (resultado.next()) {
                datos[0] = String.valueOf(resultado.getInt("idHabitaculo"));
                datos[1] = String.valueOf(resultado.getInt("idservicio"));
            } else {
                JOptionPane.showMessageDialog(null, "Habitáculo no encontrado.");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

    public static void listarPorServicio(int idServicio) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Habitaculo WHERE idservicio=" + idServicio);
            while (resultado.next()) {
                System.out.println("Habitáculo ID: " + resultado.getInt("idHabitaculo"));
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar habitáculos: " + e);
        }
    }
}
