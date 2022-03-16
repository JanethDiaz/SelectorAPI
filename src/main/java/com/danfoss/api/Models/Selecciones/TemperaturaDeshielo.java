package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TemperaturaDeshielo {
    private int id;
    private int idTemperatura;
    private int idDeshielo;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTemperatura() {
        return idTemperatura;
    }

    public void setIdTemperatura(int idTemperatura) {
        this.idTemperatura = idTemperatura;
    }

    public int getIdDeshielo() {
        return idDeshielo;
    }

    public void setIdDeshielo(int idDeshielo) {
        this.idDeshielo = idDeshielo;
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
            params.put("1", getIdTemperatura());
            params.put("2", getIdDeshielo());

            DataTable dt = new Persistencia().Query("CALL SP_TemperaturaDeshielo_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdTemperatura());
            params.put("3", getIdDeshielo());

            DataTable dt = new Persistencia().Query("CALL SP_TemperaturaDeshielo_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_TemperaturaDeshielo_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }
    public ArrayList<TemperaturaDeshielo> Listar() throws Exception {
        try {
            ArrayList<TemperaturaDeshielo> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_TemperaturaDeshielo_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadTemperaturaDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar " + e.getMessage());
        }
    }

    private  TemperaturaDeshielo loadTemperaturaDeshielo(Map<String, String> row) {

        TemperaturaDeshielo td= new TemperaturaDeshielo();
        td.setId(Integer.parseInt(row.get("Id")));
        td.setIdDeshielo(Integer.parseInt(row.get("IdDeshielo")));
        td.setIdTemperatura(Integer.parseInt(row.get("IdTemperatura")));
        //if (row.get("Status") != null)
        td.setStatus(Byte.parseByte(row.get("Status")));
        //if (row.get("Activo") != null)
        td.setActivo(Byte.parseByte(row.get("Activo")));

        return td;
    }
}
