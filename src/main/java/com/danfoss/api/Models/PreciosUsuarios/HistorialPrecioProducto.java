package com.danfoss.api.Models.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;
import java.util.Map;

public class HistorialPrecioProducto {
    private int Id;
    private int IdPreciosUsuario;
    private int IdUsuarioRegistro;
    private byte Activo;

    public  HistorialPrecioProducto cargarPorId(int id) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", id);

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosProducto_CargarPorId", params);

            if (dt.Rows.size() > 0) {
                return loadHistorial(dt.Rows.get(0));
            } else {
                throw new Exception("Produ" +
                        "cto no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }
    }
    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdPreciosUsuario());
            params.put("3", getIdUsuarioRegistro());
            params.put("4", getActivo());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosProducto_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }

    private HistorialPrecioProducto loadHistorial(Map<String, String> row) {
        HistorialPrecioProducto H = new HistorialPrecioProducto();

        H.setId(Integer.parseInt(row.get("Id")));
        H.setIdPreciosUsuario(Integer.parseInt(row.get("IdPreciosUsuario")));
        H.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistro")));
        H.setActivo(Byte.parseByte(row.get("Activo")));

        return H;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdPreciosUsuario() {
        return IdPreciosUsuario;
    }

    public void setIdPreciosUsuario(int idPreciosUsuario) {
        IdPreciosUsuario = idPreciosUsuario;
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

    public void setActivo(byte activo) {
        Activo = activo;
    }
}
