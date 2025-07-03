/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author User
 */
public class Restaurante extends Servicios{

    public Restaurante(String id, int cantPer, double Valor, Boolean Estado) {
        super(id, cantPer, Valor, Estado);
    }

    public Restaurante() {
        super();
    }

    @Override
    public Boolean aforo() {
        if(cantPer>10){
            Estado=false;
        }
        return Estado;
    }
    
}
