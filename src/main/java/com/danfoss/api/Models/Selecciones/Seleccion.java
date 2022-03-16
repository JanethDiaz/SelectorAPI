package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seleccion {
    private int id;
    private int idSistema_SVC;
    private int idCapacidad;
    private int idDeshielo;
    private int idTemperatura;
    private int urlCoolSelector;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSistema_SVC() {
        return idSistema_SVC;
    }

    public void setIdSistema_SVC(int idSistema_SVC) {
        this.idSistema_SVC = idSistema_SVC;
    }

    public int getIdCapacidad() {
        return idCapacidad;
    }

    public void setIdCapacidad(int idCapacidad) {
        this.idCapacidad = idCapacidad;
    }

    public int getIdDeshielo() {
        return idDeshielo;
    }

    public void setIdDeshielo(int idDeshielo) {
        this.idDeshielo = idDeshielo;
    }

    public int getIdTemperatura() {
        return idTemperatura;
    }

    public void setIdTemperatura(int idTemperatura) {
        this.idTemperatura = idTemperatura;
    }

    public int getUrlCoolSelector() {
        return urlCoolSelector;
    }

    public void setUrlCoolSelector(int urlCoolSelector) {
        this.urlCoolSelector = urlCoolSelector;
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
            params.put("1", getIdSistema_SVC());
            params.put("2", getIdCapacidad());
            params.put("3", getIdDeshielo());
            params.put("4", getIdTemperatura());
            params.put("5", getUrlCoolSelector());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se longro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdSistema_SVC());
            params.put("3", getIdCapacidad());
            params.put("4", getIdDeshielo());
            params.put("5", getIdTemperatura());
            params.put("6", getUrlCoolSelector());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }
    public ArrayList<Seleccion> Listar() throws Exception {
        try {
            ArrayList<Seleccion> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSeleccion(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    private  Seleccion loadSeleccion(Map<String, String> row) {

        Seleccion s = new Seleccion();
        s.setId(Integer.parseInt(row.get("Id")));
        s.setIdCapacidad(Integer.parseInt(row.get("IdCapacidad")));
        s.setIdDeshielo(Integer.parseInt(row.get("IdDeshielo")));
        s.setIdSistema_SVC(Integer.parseInt(row.get("IdSistema_SVC")));
        s.setIdTemperatura(Integer.parseInt(row.get("IdTemperatura")));
        s.setUrlCoolSelector(Integer.parseInt(row.get("UrlCoolSelector")));
        //if (row.get("Activo") != null)
        s.setActivo(Byte.parseByte(row.get("Activo")));

        return s;
    }
}
