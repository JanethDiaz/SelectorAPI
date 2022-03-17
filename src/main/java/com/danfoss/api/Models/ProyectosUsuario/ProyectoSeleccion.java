package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProyectoSeleccion {
    private int id;
    private int idUsuario;
    private int idSeleccion;
    private int idProyecto;
    private byte Activo;

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

    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuario());
            params.put("2", getIdSeleccion());
            params.put("3", getIdProyecto());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdUsuario());
            params.put("3", getIdSeleccion());
            params.put("4", getIdProyecto());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectoSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }


    public ArrayList<ProyectoSeleccion> Listar() throws Exception {
        try {
            ArrayList<ProyectoSeleccion> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_Listar");
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

    private  ProyectoSeleccion loadProyectoSeleccion(Map<String, String> row) {

        ProyectoSeleccion ps = new ProyectoSeleccion();
        ps.setId(Integer.parseInt(row.get("Id")));
        ps.setIdUsuario(Integer.parseInt(row.get("IdUsuario")));
        ps.setIdSeleccion(Integer.parseInt(row.get("IdSeleccion")));
        ps.setIdProyecto(Integer.parseInt(row.get("IdProyecto")));
        if (row.get("Activo") != null)
            ps.setActivo(Byte.parseByte(row.get("Activo")));
        return ps;
    }
}
