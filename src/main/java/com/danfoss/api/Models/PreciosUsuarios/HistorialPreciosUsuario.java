package com.danfoss.api.Models.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class HistorialPreciosUsuario {
    private int id;
    private Double precio;
    private int idPreciosUsuario;
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

    public int getIdPreciosUsuario() {
        return idPreciosUsuario;
    }

    public void setIdPreciosUsuario(int idPreciosUsuario) {
        this.idPreciosUsuario = idPreciosUsuario;
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

    public  int Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdPreciosUsuario());
            params.put("2", getIdUsuarioRegistra());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosUsuario_Insertar", params);

        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
        return 0;// Checar Return
    }
    public  void DesactivarPorIdPrecioUsuario () throws  Exception{
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdPreciosUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialPreciosUsuario_Desactivar");

        } catch (Exception e) {
            throw new Exception("Error no se logro desactivar" + e.getMessage());
        }
    }
}
