package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sistema_SVC {
    private int id;
    private String descripcionSistema;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionSistema() {
        return descripcionSistema;
    }

    public void setDescripcionSistema(String descripcionSistema) {
        this.descripcionSistema = descripcionSistema;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
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
            params.put("1", getDescripcionSistema());

            DataTable dt = new Persistencia().Query("CALL SP_SistemaSVC_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcionSistema());

            DataTable dt = new Persistencia().Query("CALL SP_SistemaSVC_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_SistemaSVC_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }
    public ArrayList<Sistema_SVC> Listar() throws Exception {
        try {
            ArrayList<Sistema_SVC> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_SistemaSVC_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSistema_SVC(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }
    public Sistema_SVC CargarPorDescripcion() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcionSistema());
            DataTable dt = new Persistencia().Query("CALL SP_SistemaSVC_CargarPorDescripcion", params);
            if (dt.Rows.size() > 0) {
                return loadSistema_SVC(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new Sistema_SVC();
    }

    private  Sistema_SVC loadSistema_SVC(Map<String, String> row) {
        Sistema_SVC svc= new Sistema_SVC();
        svc.setId(Integer.parseInt(row.get("Id")));
        svc.setDescripcionSistema(row.get("DescripcionSistema"));
        return svc;
    }
}
