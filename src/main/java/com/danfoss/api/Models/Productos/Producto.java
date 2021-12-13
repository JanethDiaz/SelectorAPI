package com.danfoss.api.Models.Productos;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class Producto {
    private int Id;
    private String Codigo;
    private String Descripcion;
    private int IdUsuarioRegistro;
    private int IdModelo;
    private byte Status;
    private byte Activo;

    public int getId() {
        return Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setId(int id) {
        Id = id;
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

//    public static Producto cargarPorId(int idProducto) throws Exception {
//        try {
//            HashMap<String, Object> params = new HashMap<>();
//            params.put("1", getId());
//
//            DataTable dt = new Persistencia().Query("CALL SP_Productos_CargarPorId", params);
//            if (dt.Rows.size() > 0) {
//                return loadUsuario(dt);
//            } else {
//                throw new Exception("Producto no encontrado favor de validar sus credenciales");
//            }
//        } catch (Exception e) {
//            throw new Exception("Error, producto no encontrado" + e.getMessage());
//        }
//    }
//    public static boolean Insertar() throws Exception {
//
//        try {
//            HashMap<String, Object> params = new HashMap<>();
//            params.put("1", getCodigo());
//            params.put("2", getDescripcion());
//            params.put("3", getIdUsuarioRegistro());
//            params.put("4", getIdModelo());
//
//            DataTable dt = new Persistencia().Query("CALL SP_Productos_Insertar", params);
//            if (dt.Rows.size() > 0) {
//                return loadUsuario(dt);
//            } else {
//                throw new Exception("Producto insertado");
//            }
//        } catch (Exception e) {
//            throw new Exception("Error, producto ya existente" + e.getMessage());
//        }
//    }
//    public static boolean Eliminar() throws Exception {
//        try {
//            HashMap<String, Object> params = new HashMap<>();
//            params.put("1", getId());
//
//            DataTable dt = new Persistencia().Query("CALL SP_Productos_Eliminar", params);
//            if (dt.Rows.size() > 0) {
//                return loadUsuario(dt);
//            } else {
//                throw new Exception("Producto Eliminado");
//            }
//        } catch (Exception e) {
//            throw new Exception("Error, producto ya Eliminado" + e.getMessage());
//        }
//    }
//    public static ArrayList<Producto> Listar() throws Exception {
//        try {
//            HashMap<String, Object> params = new HashMap<>();
//
//            DataTable dt = new Persistencia().Query("CALL SP_Productos_Listar", params);
//            if (dt.Rows.size() > 0) {
//                return loadUsuario(dt);
//            } else {
//                throw new Exception("Productos Listados");
//            }
//        } catch (Exception e) {
//            throw new Exception("Error" + e.getMessage());
//        }
//    }
}
