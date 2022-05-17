package com.danfoss.api.Models.Usuarios.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class DescuentoUsuario {
    private int id;
    private int Porcentaje;
    private int idCliente;
    private int idProducto;
    private byte Activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        Porcentaje = porcentaje;
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

    public byte getActivo() {
        return Activo;
    }

    public void setActivo(byte activo) {
        Activo = activo;
    }

    public  void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdCliente());
            params.put("2", getIdProducto());

            DataTable dt = new Persistencia().Query("CALL SP_DescuentosUsuario_Insertar", params);
            if ( dt.Rows.size() > 0 ) {
                if (dt.Rows.get(0).get("Id") != null) {
                    setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
                }
            }
        }
        catch (Exception e) {
            throw new Exception("Error al tratar de insertar el descuento del cliente " + e.getMessage());
        }
    }
}
