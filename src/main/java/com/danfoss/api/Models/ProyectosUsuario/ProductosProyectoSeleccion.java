package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class ProductosProyectoSeleccion {
    private int id;
    private int idProducto;
    private int idGrupo;
    private int cantidad;
    private int idProyectoSeleccion;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProyectoSeleccion() {
        return idProyectoSeleccion;
    }

    public void setIdProyectoSeleccion(int idProyectoSeleccion) {
        this.idProyectoSeleccion = idProyectoSeleccion;
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
            params.put("1", getIdProducto());
            params.put("2", getIdGrupo());
            params.put("3", getCantidad());
            params.put("4", getIdProyectoSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_ProductosProyectoSeleccion_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("1", getIdProducto());
            params.put("2", getIdGrupo());
            params.put("3", getCantidad());
            params.put("4", getIdProyectoSeleccion());

            new Persistencia().ExceuteNonQuery("CALL SP_ProductosProyectoSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_ProductosProyectoSeleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }
}
