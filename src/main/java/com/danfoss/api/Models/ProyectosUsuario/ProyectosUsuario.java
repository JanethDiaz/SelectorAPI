package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProyectosUsuario {
    private int id;
    private String nombreProyecto;
    private int idUsuario;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
            params.put("1", getIdUsuario());
            params.put("2", getNombreProyecto());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getNombreProyecto());
            params.put("3", getIdUsuario());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectosUsuario_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }

    public ArrayList<ProyectosUsuario> Listar() throws Exception {
        try {
            ArrayList<ProyectosUsuario> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_ ProyectosUsuario_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProyectosUusario(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    private  ProyectosUsuario loadProyectosUusario(Map<String, String> row) {

        ProyectosUsuario pu = new ProyectosUsuario();
        pu.setId(Integer.parseInt(row.get("Id")));
        pu.setNombreProyecto(row.get("nombreProyecto"));
        if (row.get("Activo") != null)
            pu.setActivo(Byte.parseByte(row.get("Activo")));
        return pu;
    }
}