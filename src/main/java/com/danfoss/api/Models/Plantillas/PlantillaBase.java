package com.danfoss.api.Models.Plantillas;

import com.danfoss.api.Models.Selecciones.Seleccion;

public class PlantillaBase {
    private int id;
    private String descripcionPlantilla;
    private Seleccion seleccion;
    private int idSeleccion;
    private int idProyecto;
    private int cantidad = 1;
    private String areaSeleccion;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcionPlantilla() {
        return descripcionPlantilla;
    }
    public void setDescripcionPlantilla(String descripcionPlantilla) {
        this.descripcionPlantilla = descripcionPlantilla;
    }
    public String getAreaSeleccion() {
        return areaSeleccion;
    }
    public void setAreaSeleccion(String areaSeleccion) {
        this.areaSeleccion = areaSeleccion;
    }
    public Seleccion getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getIdSeleccion() {
        return idSeleccion;
    }
    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }
    public int getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
