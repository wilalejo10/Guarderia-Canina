
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Factura {
    Fecha fechaF;
    Mascota masc;
    private ArrayList<Persona> objP;
    private ArrayList<Servicios> objS;

    public Factura(Fecha fechaF, Mascota masc, ArrayList<Persona> objP, ArrayList<Servicios> objS) {
        this.fechaF = fechaF;
        this.masc = masc;
        this.objP = objP;
        this.objS = objS;
    }
    public Factura() {
        this.fechaF = new Fecha();
        this.masc = new Mascota();
        this.objP = new ArrayList<>();
        this.objS = new ArrayList<>();
    }
    public double totalPago(){
        double rec=0;
        for(Servicios servicios: objS){
            rec+=servicios.getValor();
        }
        return rec;
    }

    @Override
    public String toString() {
        return "FACTURA\nFecha "+fechaF+"\n Propietario: "+objP+"\nMascota"+masc+
                "\nServicios: "+objS+"\nTotal: "+ totalPago(); 
    }
    public Fecha getFechaF() {
        return fechaF;
    }

    public void setFechaF(Fecha fechaF) {
        this.fechaF = fechaF;
    }

    public Mascota getMasc() {
        return masc;
    }

    public void setMasc(Mascota masc) {
        this.masc = masc;
    }

    public ArrayList<Persona> getObjP() {
        return objP;
    }

    public void setObjP(ArrayList<Persona> objP) {
        this.objP = objP;
    }

    public ArrayList<Servicios> getObjS() {
        return objS;
    }

    public void setObjS(ArrayList<Servicios> objS) {
        this.objS = objS;
    }
    
}
