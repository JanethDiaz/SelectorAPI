package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Temperatura {
    private int id;
    private int Valor;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
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
            params.put("1", getValor());

            DataTable dt = new Persistencia().Query("CALL SP_Temperatura_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getValor());

            DataTable dt = new Persistencia().Query("CALL SP_Temperatura_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Temperatura_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar " + e.getMessage());
        }
    }

    public ArrayList<Temperatura> Listar() throws Exception {
        try {
            ArrayList<Temperatura> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Temperatura_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadTemperatura(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    private  Temperatura loadTemperatura(Map<String, String> row) {

        Temperatura t = new Temperatura();
        t.setId(Integer.parseInt(row.get("Id")));
        t.setValor(Integer.parseInt(row.get("Valor")));
        //if (row.get("Status") != null)
        t.setStatus(Byte.parseByte(row.get("Status")));
        //if (row.get("Activo") != null)
        t.setActivo(Byte.parseByte(row.get("Activo")));

        return t;
    }
}
