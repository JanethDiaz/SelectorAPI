package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;
import java.util.Map;

public class UsuarioSeleccion {
    private int id;
    private int idUsuario;
    private int idSeleccion;
    private  byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
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
            params.put("2", getIdSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_UsuarioSeleccion_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdUsuario());
            params.put("3", getIdSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_UsuarioSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_UsuarioSeleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public  UsuarioSeleccion cargarPorId(int idUsuarioSeleccion) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idUsuarioSeleccion);

            DataTable dt = new Persistencia().Query("CALL SP_UsuarioSeleccion_CargarPorIdUsuario", params);

            if (dt.Rows.size() > 0) {
                return loadUsuarioSeleccion(dt.Rows.get(0));
            } else {
                throw new Exception("Usuario no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al cargar" + e.getMessage());
        }
    }
    private  UsuarioSeleccion loadUsuarioSeleccion(Map<String, String> row) {

        UsuarioSeleccion us = new UsuarioSeleccion();
        us.setId(Integer.parseInt(row.get("Id")));
        us.setIdSeleccion(Integer.parseInt(row.get("IdSeleccion")));
        us.setIdUsuario(Integer.parseInt(row.get("IdUsuario")));
        //if (row.get("Activo") != null)
        us.setActivo(Byte.parseByte(row.get("Activo")));

        return us;
    }
}
