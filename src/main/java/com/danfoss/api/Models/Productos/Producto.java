package com.danfoss.api.Models.Productos;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto {
    private int id;
    private String Codigo;
    private String Descripcion;
    private int IdUsuarioRegistro;
    private int IdModelo;
    private byte Status;
    private byte Activo;

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setId(int Id) {
        id = Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public int getIdUsuarioRegistro() {
        return IdUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(int idUsuarioRegistro) {
        IdUsuarioRegistro = idUsuarioRegistro;
    }

    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int idModelo) {
        IdModelo = idModelo;
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

    public  Producto cargarPorId(int idProducto) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idProducto);

            DataTable dt = new Persistencia().Query("CALL SP_Productos_CargarPorId", params);

            if (dt.Rows.size() > 0) {
//                Producto p = new Producto();
//                Map<String, String> row = dt.Rows.get(0);
//                p.setId(Integer.parseInt(row.get("Id")));
//                p.setCodigo(row.get("Codigo"));
//                p.setDescripcion(row.get("Descripcion"));
//                p.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistra")));
//                p.setIdModelo(Integer.parseInt(row.get("IdModelo")));
//                p.setStatus(Byte.parseByte(row.get("Status")));
//                p.setActivo(Byte.parseByte(row.get("Activo")));
                return loadProducto(dt.Rows.get(0));
            } else {
                throw new Exception("Producto no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getCodigo());
            params.put("3", getIdModelo());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }
    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getCodigo());
            params.put("2", getDescripcion());
            params.put("3", getIdUsuarioRegistro());
            params.put("4", getIdModelo());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error, producto ya existente" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public List<Producto> Listar() throws Exception {
        try {
            List<Producto> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Producto_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProducto(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }
    private  Producto loadProducto(Map<String, String> row) {
        Producto p = new Producto();
        p.setId(Integer.parseInt(row.get("Id")));
        p.setCodigo(row.get("Codigo"));
        p.setDescripcion(row.get("Descripcion"));
//        p.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistra")));
        if(row.get("IdModelo") != null) {
            p.setIdModelo(Integer.parseInt(row.get("IdModelo")));
        }
        p.setStatus(Byte.parseByte(row.get("Status")));
//        p.setActivo(Byte.parseByte(row.get("Activo")));

        return p;
    }
}
