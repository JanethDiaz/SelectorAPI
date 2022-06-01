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
            params.put("2", getUrlCoolSelector());

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

    public void crearUrlCoolSelector(Seleccion seleccion) throws  Exception {
        try
        {
            String path1 = getPath1(seleccion.getSistema_svc());
            String path2 = getPath2(seleccion.getSistema_svc());
            String path3 = getPath3(path2, seleccion.getCapacidad());
            String path4 = getPath4(path3, seleccion.getTemperatura(), seleccion.getSistema_svc());
            String url = path1 + "/" +
                    path2 + "/" +
                    path3 + "/" +
                    path4 + "/" +
                    getFileName(path2, seleccion);
            setUrlCoolSelector(url);
        }
        catch (Exception e)
        {
            throw new Exception("Error al crear la url para el archivo coolSelector de la plantilla " + e.getMessage());
        }
    }

    private String getPath1(Sistema_SVC sistema_svc) {
        return sistema_svc.getDescripcionSistema().contains("Recirculado") ? "Sistema Recirculado" : "Sistema DX";
    }

    private String getPath2(Sistema_SVC sistema_svc) {
        if (esRecirculado(sistema_svc)) {
            return getNombreRecirculado(sistema_svc);
        }
        else return sistema_svc.getDescripcionSistema().replace(" ","");
    }

    private String getPath3(String path2, Capacidad capacidad) {
        return path2 + "-" + capacidad.getValor() + "TR";
    }

    private String getPath4(String path3, Temperatura temperatura, Sistema_SVC sistema_svc) {
        if (esRecirculado(sistema_svc)) {
            return path3 + "@" + temperatura.getValor() + "C";
        }
        else return path3 + "@" + temperatura.getValor();
    }

    private String getFileName(String path2, Seleccion seleccion) {
        String result = path2 + "-(" + seleccion.getCapacidad().getValor() + "TR" + "@" +
                seleccion.getTemperatura().getValor() + "C)";
        String descripcionDeshielo = getNombreDeshielo(seleccion.getDeshielo());
        if (!descripcionDeshielo.equals("")) {
            result += "-" + descripcionDeshielo;
        }
        result += getExtFile(seleccion);

        return result;
    }

    private String getExtFile(Seleccion seleccion) {
        if (esRecirculado(seleccion.getSistema_svc())) {
            int temp = Integer.parseInt(seleccion.getTemperatura().getValor().replace("-", ""));
            if ( temp > 10 ) {
                return "-ICLX.csprj";
            }
            else return "-ICS.csprj";
        }
        else return ".csprj";
    }

    private String getNombreDeshielo(Deshielo deshielo) {
        if ( deshielo.getIdPadre() > 0 ) {
            Deshielo centerParent = deshielo.CargarPorId();
            if (centerParent.getId() > 0 ) {
                return  "(GC-" + getCenterNameDeshielo(centerParent) + "-" + getLastNameDeshielo(deshielo) + ")";
            }
            else return "";
        }
        else return "";
    }

    private String getCenterNameDeshielo(Deshielo deshielo) {
        return deshielo.getId() == 3 ? "N" : "DS";
    }

    private String getLastNameDeshielo(Deshielo deshielo) {
        return deshielo.getId() == 6 ? "DL" : "CP";
    }

    private boolean esRecirculado(Sistema_SVC sistema_svc) {
        return sistema_svc.getDescripcionSistema().contains("Recirculado");
    }

    private String getNombreRecirculado(Sistema_SVC sistema_svc) {
        String result = "";
        switch (sistema_svc.getDescripcionSistema()){
            case "Recirculado (4:1)":
                result = "R4";
                break;
            case "Recirculado (3:1)" :
                result = "R3";
                break;
            case "Recirculado (1.2:1)":
                result = "R1.2";
                break;
        }
        return result;
    }

    public void CargarPorId() throws Exception{

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getSeleccion().getIdPlantillaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_PlantillaSeleccion_CargarPorId", params);
            if (dt.Rows.size() > 0) {
                setId(getSeleccion().getIdPlantillaSeleccion());
                setDescripcionPlantilla(dt.Rows.get(0).get("DescripcionPlantilla"));
                if(dt.Rows.get(0).get("UrlCoolSelector") != null) {
                    setUrlCoolSelector(dt.Rows.get(0).get("UrlCoolSelector"));
                }

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
