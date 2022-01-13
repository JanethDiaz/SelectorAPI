package com.danfoss.api.Models.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;
import java.util.Map;

public class HistorialPreciosUsuario {
    private int Id;
    private double Precio;
    private int IdUsuarioRegistro;
    private int IdProducto;
    private byte Activo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public int getIdUsuarioRegistro() {
        return IdUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(int idUsuarioRegistro) {
        IdUsuarioRegistro = idUsuarioRegistro;
    }

    public byte getActivo() {
        return Activo;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public void setActivo(byte activo) {
        Activo = activo;
    }


    public HistorialPreciosUsuario cargarPorId(int id) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", id);

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosProducto_CargarPorId", params);

            if (dt.Rows.size() > 0) {
                return loadHistorial(dt.Rows.get(0));
            } else {
                throw new Exception("Produ" +
                        "0cto no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }
    }
    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getPrecio());
            params.put("3", getIdUsuarioRegistro());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosProducto_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    public  boolean DesactivarPorIdProducto () throws  Exception{
            try {
                HashMap<String, Object> params = new HashMap<>();
                params.put("1", getIdProducto());

                DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosProducto_Desactivar");
                return true;


            } catch (Exception e) {
                throw new Exception("Error" + e.getMessage());
            }
    }

    private HistorialPreciosUsuario loadHistorial(Map<String, String> row) {
        HistorialPreciosUsuario H = new HistorialPreciosUsuario();

        H.setId(Integer.parseInt(row.get("Id")));
        H.setPrecio(Double.parseDouble("Precio"));
        H.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistro")));
        H.setActivo(Byte.parseByte(row.get("Activo")));

        return H;
    }
}