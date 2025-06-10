package Modelo;

import java.util.ArrayList;
/**
 *
 * @author User
 */
public class Recaudo {
    private ArrayList<Factura> ListaF;

    public Recaudo(ArrayList<Factura> ListaF) {
        this.ListaF = ListaF;
    }
    public Recaudo() {
        this.ListaF = new ArrayList<>();
    }
    public double totalRecaudo(){
        double rec=0;
        for(Factura factura: ListaF){
            rec+=factura.totalPago();
        }
        return rec;
    }
    public ArrayList<Factura> getListaF() {
        return ListaF;
    }

    public void setListaF(ArrayList<Factura> ListaF) {
        this.ListaF = ListaF;
    }
    
}
