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
            throw new Exception("Error no se logro insertar" + e.getMessage());
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
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
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
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    public ArrayList<Capacidad> ListarPorSistema(int idSistema) throws Exception {
        try {
            ArrayList<Capacidad> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idSistema);

            DataTable dt = new Persistencia().Query("CALL SP_CapacidadSistema_Listar", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadCapacidad(row));
                }
            }
            return result;
        }
        catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    public Capacidad CargarPorValor() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getValor());
            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_CargarPorValor", params);
            if (dt.Rows.size() > 0) {
               return loadCapacidad(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Capacidad();
    }

    public Capacidad CargarPorId(int id) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", id);
            DataTable dt = new Persistencia().Query("CALL SP_Capacidad_CargarPorId", params);
            if (dt.Rows.size() > 0) {
                return loadCapacidad(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Capacidad();
    }
    private  Capacidad loadCapacidad(Map<String, String> row) {
        Capacidad c = new Capacidad();
        c.setId(Integer.parseInt(row.get("Id")));
        c.setValor(Integer.parseInt(row.get("Valor")));
        return c;
    }
}
