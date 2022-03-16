package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CapacidadSistema {
    private int id;
    private int idSistema;
    private int idCapacidad;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }

    public int getIdCapacidad() {
        return idCapacidad;
    }

    public void setIdCapacidad(int idCapacidad) {
        this.idCapacidad = idCapacidad;
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
            params.put("1", getIdSistema());
            params.put("2", getIdCapacidad());

            DataTable dt = new Persistencia().Query("CALL SP_CapacidadSistema_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdSistema());
            params.put("3", getIdCapacidad());


            DataTable dt = new Persistencia().Query("CALL SP_CapacidadSistema_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_CapacidadSistema_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public ArrayList<CapacidadSistema> Listar() throws Exception {
        try {
            ArrayList<CapacidadSistema> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_CapacidadSistema_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadCapacidadSistema(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    private  CapacidadSistema loadCapacidadSistema(Map<String, String> row) {

        CapacidadSistema cs= new CapacidadSistema();
        cs.setId(Integer.parseInt(row.get("Id")));
        cs.setIdCapacidad(Integer.parseInt(row.get("IdCapacidad")));
        cs.setIdSistema(Integer.parseInt(row.get("IdSistema")));
        //if (row.get("Status") != null)
        cs.setStatus(Byte.parseByte(row.get("Status")));
        //if (row.get("Activo") != null)
        cs.setActivo(Byte.parseByte(row.get("Activo")));

        return cs;
    }
}
