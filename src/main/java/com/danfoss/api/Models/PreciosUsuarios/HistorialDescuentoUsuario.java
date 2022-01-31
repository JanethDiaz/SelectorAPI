package com.danfoss.api.Models.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class HistorialDescuentoUsuario {
    private int Id;
    private int IdDescuentosUsuario;
    private int IdUsuarioRegistro;
    private byte Activo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdDescuentosUsuario() {
        return IdDescuentosUsuario;
    }

    public void setIdDescuentosUsuario(int idDescuentosUsuario) {
        IdDescuentosUsuario = idDescuentosUsuario;
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

    public  int Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdDescuentosUsuario());
            params.put("2", getIdUsuarioRegistro());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialDescuentosUsuario_Insertar", params);

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
        return 0;// Checar Return
    }

    public  void DesactivarPorIdDescuentoUsuario () throws  Exception{
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdDescuentosUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_HistorialDescuentosUsuario_Desactivar");

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
}
