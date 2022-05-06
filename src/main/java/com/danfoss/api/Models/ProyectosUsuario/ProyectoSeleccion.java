package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Selecciones.Seleccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProyectoSeleccion {
    private int id;
    private int idUsuario;
    private int idSeleccion;
    private int idProyecto;
    private byte Activo;
    private int cantidad;
    private String areaSeleccion;
    private String descripcionPlantilla;
    private Seleccion seleccion;

    public String getDescripcionPlantilla() {
        return descripcionPlantilla;
    }
    public void setDescripcionPlantilla(String descripcionPlantilla) {
        this.descripcionPlantilla = descripcionPlantilla;
    }
    public Seleccion getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
    public byte getActivo() {
        return Activo;
    }
    public void setActivo(byte activo) {
        Activo = activo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAreaSeleccion() {
        return areaSeleccion;
    }
    public void setAreaSeleccion(String areaSeleccion) {
        this.areaSeleccion = areaSeleccion;
    }


    public void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuario());
            params.put("2", getIdSeleccion());
            params.put("3", getIdProyecto());
            params.put("4", getCantidad());
            params.put("5", getAreaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_Insertar", params);
            if ( dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
            }
        }
        catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getCantidad());
            params.put("3", getAreaSeleccion());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectoSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }


    public ArrayList<ProyectoSeleccion> Listar(int idProyecto) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idProyecto);
            ArrayList<ProyectoSeleccion> result = new ArrayList<>();

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_ListarPorIdProyecto", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProyectoSeleccion(row));
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

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public boolean existe() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdProyecto());
            params.put("2", getIdSeleccion());
            params.put("3", getAreaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_CargarPorId_IdSeleccion", params);

            return  dt.Rows.size() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private  ProyectoSeleccion loadProyectoSeleccion(Map<String, String> row) {

        ProyectoSeleccion ps = new ProyectoSeleccion();
        ps.setId(Integer.parseInt(row.get("Id")));
        ps.setIdSeleccion(Integer.parseInt(row.get("IdSeleccion")));
        ps.setIdProyecto(Integer.parseInt(row.get("IdProyecto")));
        ps.setAreaSeleccion(row.get("AreaSeleccion"));
        ps.setDescripcionPlantilla(row.get("DescripcionPlantilla"));
        if (row.get("Activo") != null)
            ps.setActivo(Byte.parseByte(row.get("Activo")));

        if(row.get("CantidadSeleccion") != null)
            ps.setCantidad(Integer.parseInt(row.get("CantidadSeleccion")));


        return ps;
    }
}
