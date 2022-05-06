package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Selecciones.PlantillaSeleccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectosUsuario {
    private int id;
    private String nombreProyecto;
    private int idUsuario;
    private List<PlantillaSeleccion> plantillas;
    private String fechaProyecto;
    private double costoTotal;
    private byte activo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProyecto() {
        return nombreProyecto;
    }
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public byte getActivo() {
        return activo;
    }
    public void setActivo(byte activo) {
        this.activo = activo;
    }
    public List<PlantillaSeleccion> getPlantillas() {
        return plantillas;
    }
    public void setPlantillas(List<PlantillaSeleccion> plantillas) {
        this.plantillas = plantillas;
    }
    public String getFechaProyecto() {
        return fechaProyecto;
    }
    public void setFechaProyecto(String fechaProyecto) {
        this.fechaProyecto = fechaProyecto;
    }
    public double getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }


    public void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getNombreProyecto());
            params.put("2", getIdUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Insertar", params);

            if ( dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
                if (getId() > 0) {
                    insertarSelecciones();
                }
            }
        }
        catch (Exception e) {
            throw new Exception("Error al intentar guardar el proyecto " + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getNombreProyecto());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectosUsario_ActualizarNombre", params);
            insertarSelecciones();

            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }

//    private void insertarSelecciones() throws Exception {
//        try
//        {
//            if (getPlantillas().size() > 0) {
//                for (PlantillaSeleccion p:
//                        getPlantillas()) {
//                    ProyectoSeleccion ps = new ProyectoSeleccion();
//                    ps.setIdProyecto(p.getIdProyecto());
//                    ps.setIdSeleccion(p.getIdSeleccion());
//                    ps.setIdUsuario(getIdUsuario());
//                    if ( p.getSeleccion() != null ) {
//                        ps.setIdSeleccion(p.getSeleccion().getId());
//                    }
//                    ps.setCantidad(p.getCantidad());
//                    ps.setAreaSeleccion(p.getAreaSeleccion());
//                    ps.setIdProyecto(getId());
//                    if ( !ps.existe() && p.getSeleccion() != null ) {
//                        ps.Insertar();
//                    }
//                    else {
//                        ps.setId(p.getId());
//                        ps.Actualizar();
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
    private void insertarSelecciones() throws Exception {
        try
        {
            if (getPlantillas().size() > 0) {
                for (PlantillaSeleccion p:
                        getPlantillas()) {
                    ProyectoSeleccionA ps = new ProyectoSeleccionA(p);
                    if (!ps.Existe() && ps.getSeleccion() != null) {
                        ps.Insertar();
                    }
                    else {
                        ps.Actualizar();
                    }
                }
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ArrayList<ProyectosUsuario> Listar() throws Exception {
        try {
            ArrayList<ProyectosUsuario> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_ListarPorIdUsuario", params);

            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProyectosUusario(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    private  ProyectosUsuario loadProyectosUusario(Map<String, String> row) {

        ProyectosUsuario pu = new ProyectosUsuario();
        pu.setId(Integer.parseInt(row.get("Id")));
        pu.setNombreProyecto(row.get("NombreProyecto"));
        pu.setFechaProyecto(row.get("FechaRegistro"));
        if (row.get("Activo") != null)
            pu.setActivo(Byte.parseByte(row.get("Activo")));
        return pu;
    }
}
