/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author User
 */
public class Cuidador extends Persona{
    private Boolean Disponibilidad;

    public Cuidador(Boolean Disponibilidad, String id, String nom, String tel, String email) {
        super(id, nom, tel, email);
        this.Disponibilidad = Disponibilidad;
    }

    public Cuidador(Boolean Disponibilidad) {
        super();
        this.Disponibilidad = Disponibilidad;
    }

    public Boolean getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(Boolean Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

}
