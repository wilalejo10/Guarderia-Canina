/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class TipoDeServicio {
     int idTipoServicio;
     String tipoServicio;
     String costoUnidad;

    public TipoDeServicio() {
    }

    public TipoDeServicio(int idTipoServicio, String tipoServicio, String costoUnidad) {
        this.idTipoServicio = idTipoServicio;
        this.tipoServicio = tipoServicio;
        this.costoUnidad = costoUnidad;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(String costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    @Override
    public String toString() {
        return tipoServicio;
    }
}
