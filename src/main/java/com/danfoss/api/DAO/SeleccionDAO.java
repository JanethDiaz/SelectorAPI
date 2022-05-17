package com.danfoss.api.DAO;

import com.danfoss.api.Models.Selecciones.Capacidad;
import com.danfoss.api.Models.Selecciones.Deshielo;
import com.danfoss.api.Models.Selecciones.Sistema_SVC;
import com.danfoss.api.Models.Selecciones.Temperatura;

public class SeleccionDAO {
    private String areaSeleccion;
    private Sistema_SVC sistema_svc;
    private Capacidad capacidad;
    private Temperatura temperatura;
    private Deshielo deshielo;
    private int cantidad;
    private int idProyecto;
    private int idUsuario;
    public String getAreaSeleccion() {
        return areaSeleccion;
    }
    public void setAreaSeleccion(String areaSeleccion) {
        this.areaSeleccion = areaSeleccion;
    }
    public Sistema_SVC getSistema_svc() {
        return sistema_svc;
    }
    public void setSistema_svc(Sistema_SVC sistema_svc) {
        this.sistema_svc = sistema_svc;
    }
    public Capacidad getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(Capacidad capacidad) {
        this.capacidad = capacidad;
    }
    public Temperatura getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }
    public Deshielo getDeshielo() {
        return deshielo;
    }
    public void setDeshielo(Deshielo deshielo) {
        this.deshielo = deshielo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
