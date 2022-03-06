package com.danfoss.api.Models.Productos;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;
import java.util.Map;

public class Modelo {
    private int Id;
    private String Descripcion;
    private byte Status;
    private byte Activo;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public byte getStatus() {
        return Status;
    }
    public void setStatus(byte status) {
        Status = status;
    }
    public byte getActivo() {
        return Activo;
    }
    public void setActivo(byte activo) {
        Activo = activo;
    }

    public Modelo (){}
    public Modelo (int idModelo) {
        this.setId(idModelo);
    }
    public Modelo (String desc) {
        this.setDescripcion(desc);
    }

    public  Modelo cargarPorDescripcion(String modelo) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", modelo);

            DataTable dt = new Persistencia().Query("CALL SP_Modelos_CargarModeloPorDescripcion");

            if (dt.Rows.size() > 0) {
                return loadModelo(dt.Rows.get(0)) ;
            }

        }
        catch (Exception e) {
                throw new Exception("error no se logro cargar" + e.getMessage());
        }
        return new Modelo();
    }
    public  Modelo Insertar() throws Exception {
        Modelo result = new Modelo();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcion());

            DataTable dt = new Persistencia().Query("CALL SP_Modelos_Insertar", params);
            if (dt.Rows.size() > 0) {
                result = loadModelo(dt.Rows.get(0));
            }
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
        return result;
    }
    private Modelo loadModelo(Map<String, String> row) {
        Modelo m = new Modelo();
        m.setId(Integer.parseInt(row.get("Id")));
        m.setDescripcion(row.get("Descripcion"));

        return m;
    }
}
