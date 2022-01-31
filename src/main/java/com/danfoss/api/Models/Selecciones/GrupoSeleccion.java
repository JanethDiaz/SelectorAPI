package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrupoSeleccion {
    private int id;
    private String descripcionSeleccion;
    private int idSeleccion;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionSeleccion() {
        return descripcionSeleccion;
    }

    public void setDescripcionSeleccion(String descripcionSeleccion) {
        this.descripcionSeleccion = descripcionSeleccion;
    }

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcionSeleccion());
            params.put("2", getIdSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_GruposSeleccion_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcionSeleccion());
            params.put("3", getIdSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_GruposSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_GruposSeleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public ArrayList<GrupoSeleccion> Listar() throws Exception {
        try {
            ArrayList<GrupoSeleccion> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_GruposSeleccion_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadGrupoSeleccion(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }

    private  GrupoSeleccion loadGrupoSeleccion(Map<String, String> row) {

        GrupoSeleccion gs = new GrupoSeleccion();
        gs.setId(Integer.parseInt(row.get("Id")));
        gs.setDescripcionSeleccion(row.get("DescripcionSeleccion"));
        gs.setIdSeleccion(Integer.parseInt(row.get("IdSeleccion")));
        //if (row.get("Activo") != null)
        gs .setActivo(Byte.parseByte(row.get("Activo")));

        return gs;
    }
}
