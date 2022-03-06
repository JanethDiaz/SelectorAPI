package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deshielo {
    private int id;
    private String descripcionDeshielo;
    private int idPadre;
    private int etiqueta;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionDeshielo() {
        return descripcionDeshielo;
    }

    public void setDescripcionDeshielo(String descripcionDeshielo) {
        this.descripcionDeshielo = descripcionDeshielo;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
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
            params.put("1", getDescripcionDeshielo());
            params.put("2", getIdPadre());
            params.put("3", getEtiqueta());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcionDeshielo());
            params.put("3", getIdPadre());
            params.put("4", getEtiqueta());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public ArrayList<Deshielo> Listar() throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    private  Deshielo loadDeshielo(Map<String, String> row) {

        Deshielo d= new Deshielo();
        d.setId(Integer.parseInt(row.get("Id")));
        d.setDescripcionDeshielo(row.get("DescripcionDeshielo"));
        d.setIdPadre(Integer.parseInt(row.get("IdPadre")));
        d.setEtiqueta(Integer.parseInt(row.get("Etiqueta")));
        //if (row.get("Status") != null)
        d.setStatus(Byte.parseByte(row.get("Status")));
        //if (row.get("Activo") != null)
        d.setActivo(Byte.parseByte(row.get("Activo")));

        return d;
    }
}
