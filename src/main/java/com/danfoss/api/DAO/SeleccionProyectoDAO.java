package com.danfoss.api.DAO;

import com.danfoss.api.Models.Selecciones.Seleccion;

public class SeleccionProyectoDAO {

    private Seleccion seleccion;
    private int idCliente;

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
