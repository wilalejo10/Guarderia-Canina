
package Modelo;

/**
 *
 * @author User
 */
public abstract class Servicios {
    protected String id;
    protected int  cantPer;
    protected double Valor;
    protected Boolean Estado;

    public Servicios(String id, int cantPer, double Valor, Boolean Estado) {
        this.id = id;
        this.cantPer = cantPer;
        this.Valor = Valor;
        this.Estado = Estado;
    }
    public Servicios() {
        this.id = "";
        this.cantPer = 0;
        this.Valor = 0;
        this.Estado = true;
    }
    public abstract Boolean aforo();

    @Override
    public String toString() {
        return "Servicios\nId:" + id + "Aforo" + cantPer + "Valor" + Valor + "Disponibildiad" + Estado;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantPer() {
        return cantPer;
    }

    public void setCantPer(int cantPer) {
        this.cantPer = cantPer;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }
    
}
