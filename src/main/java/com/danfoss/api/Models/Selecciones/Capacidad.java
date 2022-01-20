package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Capacidad {
    private int id;
    private int valor;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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

            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getValor());


            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }

    public ArrayList<Capacidad> Listar() throws Exception {
        try {
            ArrayList<Capacidad> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadCapacidad(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }

    private  Capacidad loadCapacidad(Map<String, String> row) {

        Capacidad c = new Capacidad();
        c.setId(Integer.parseInt(row.get("Id")));
        c.setValor(Integer.parseInt(row.get("Valor")));
        //if (row.get("Status") != null)
            c.setStatus(Byte.parseByte(row.get("Status")));
        //if (row.get("Activo") != null)
            c.setActivo(Byte.parseByte(row.get("Activo")));

        return c;
    }
}
