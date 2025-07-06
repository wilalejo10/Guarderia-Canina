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
    

}

    
 

