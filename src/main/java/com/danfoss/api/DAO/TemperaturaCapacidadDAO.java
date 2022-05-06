package com.danfoss.api.DAO;

import com.danfoss.api.Models.Selecciones.Capacidad;
import com.danfoss.api.Models.Selecciones.Sistema_SVC;

public class TemperaturaCapacidadDAO {

    private Sistema_SVC sistema_svc;
    private Capacidad capacidad;

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
}
