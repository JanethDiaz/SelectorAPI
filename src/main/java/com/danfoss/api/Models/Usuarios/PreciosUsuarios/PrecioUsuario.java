package com.danfoss.api.Models.Usuarios.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class PrecioUsuario {
    private int Id;
    private int idProducto;
    private int idCliente;
    private byte Activo;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public byte getActivo() {
        return Activo;
    }
    public void setActivo(byte activo) {
        Activo = activo;
    }

    public  int Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdProducto());
            params.put("2", getIdCliente());

            DataTable dt = new Persistencia().Query("CALL SP_PreciosUsuario_Insertar", params);

        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
        return 0;// checar return
    }
}
