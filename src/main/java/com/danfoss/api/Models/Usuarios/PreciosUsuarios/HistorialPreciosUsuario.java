package com.danfoss.api.Models.Usuarios.PreciosUsuarios;

import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class HistorialPreciosUsuario {
    private int id;
    private Double precio;
    private int idCliente;
    private int idProducto;
    private int idUsuarioRegistra;
    private byte Activo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public int getIdUsuarioRegistra() {
        return idUsuarioRegistra;
    }
    public void setIdUsuarioRegistra(int idUsuarioRegistra) {
        this.idUsuarioRegistra = idUsuarioRegistra;
    }
    public byte getActivo() {
        return Activo;
    }
    public void setActivo(byte activo) {
        Activo = activo;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public  void Insertar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdCliente());
            params.put("2", getIdUsuarioRegistra());
            params.put("3", getPrecio());
            params.put("4", getIdProducto());
            new Persistencia().Query("CALL SP_HistorialPreciosUsuario_Insertar", params);
        }
        catch (Exception e) {
            throw new Exception("Error no se logro insertar en HistorialPreciosUsuario" + e.getMessage());
        }
    }
    public  void DesactivarPorIdCliente () throws  Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdCliente());
            new Persistencia().ExceuteNonQuery("CALL SP_HistorialPreciosUsuario_Desactivar", params);
        } catch (Exception e) {
            throw new Exception("Error no se logro desactivar todos los precios del cliente en HistorialPreciosUsuario " + e.getMessage());
        }
    }
}
