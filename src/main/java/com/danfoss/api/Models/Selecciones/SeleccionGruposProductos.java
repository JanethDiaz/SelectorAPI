package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeleccionGruposProductos {
    private int id;
    private int idProducto;
    private int idGrupo;
    private int Cantidad;
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
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
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

            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdProducto());
            params.put("3", getIdGrupo());
            params.put("4", getCantidad());

            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public ArrayList<SeleccionGruposProductos> Listar() throws Exception {
        try {
            ArrayList<SeleccionGruposProductos> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSeleccionGruposProducto(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }

    private  SeleccionGruposProductos loadSeleccionGruposProducto(Map<String, String> row) {

        SeleccionGruposProductos sgp = new SeleccionGruposProductos();
        sgp.setId(Integer.parseInt(row.get("Id")));
        sgp.setIdGrupo(Integer.parseInt(row.get("IdGrupo")));
        sgp.setIdProducto(Integer.parseInt(row.get("IdProducto")));
        sgp.setCantidad(Integer.parseInt(row.get("Row")));
        //if (row.get("Activo") != null)
        sgp.setActivo(Byte.parseByte(row.get("Activo")));

        return sgp;
    }
}

