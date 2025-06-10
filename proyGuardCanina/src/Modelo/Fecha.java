package Modelo;

import java.util.Calendar;

/**
 *
 * @author User
 */
public class Fecha {
    private int dia, mes, anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    public Fecha() {
        Calendar fecha= Calendar.getInstance();
        this.dia = fecha.get(Calendar.DAY_OF_MONTH);
        this.mes = fecha.get(Calendar.MONTH)+1;  
        this.anio = fecha.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "\nDia: " + dia + "\tMes: "
                + mes + "\tAnio: " + anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}