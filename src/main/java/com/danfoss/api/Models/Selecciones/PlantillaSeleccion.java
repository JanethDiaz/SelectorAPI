package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Plantillas.PlantillaBase;

import java.util.HashMap;

public class PlantillaSeleccion extends PlantillaBase {

    public void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcionPlantilla());

            DataTable dt = new Persistencia().Query("CALL SP_PlantillaSeleccion_Insertar", params);
            if(dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
            }
        }
        catch (Exception e)
        {
            throw new Exception("Error al tratar de Insertar Plantilla " + e.getMessage());
        }
    }

    public void crearNombre(Seleccion seleccion) throws Exception {
        try
        {
            String nombrePlantilla = "Evaporador de " + seleccion.getCapacidad().getValor() + " TR @" +
                    seleccion.getTemperatura().getValor() + "°C, " + seleccion.getSistema_svc().getDescripcionSistema() + ". "
                    + getDescripcionDeshielo(seleccion.getDeshielo());

            setDescripcionPlantilla(nombrePlantilla);
        }
        catch (Exception e)
        {
            throw new Exception("Error al crear el nombre de la plantilla " + e.getMessage());
        }
    }

    public void CargarPorId() throws Exception{

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getSeleccion().getIdPlantillaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_PlantillaSeleccion_CargarPorId", params);
            if (dt.Rows.size() > 0) {
                setId(getSeleccion().getIdPlantillaSeleccion());
                setDescripcionPlantilla(dt.Rows.get(0).get("DescripcionPlantilla"));
            }
        }
        catch (Exception e) {
            throw new Exception("Error al cargar la plantilla por Id " + e.getMessage());
        }
    }

    private String getDescripcionDeshielo(Deshielo deshielo ) throws Exception {
        try {
            if (deshielo.getIdPadre() > 0 ) {

                Deshielo padre = deshielo.CargarPorId();

                if (padre.getId() > 0 ) {

                    if (padre.getDescripcionDeshielo().contains("Deshielo")) {
                        return padre.getDescripcionDeshielo() + " con " + deshielo.getDescripcionDeshielo();
                    }
                    else
                        return "Deshielo " + padre.getDescripcionDeshielo() + " con " + deshielo.getDescripcionDeshielo();
                }
            }
            else {
                return "Deshielo " + deshielo.getDescripcionDeshielo();
            }
        }
        catch (Exception e) {
            throw new Exception("Error al crear la descripcion del hielo " + e.getMessage() );
        }

        return "";
    }

}
