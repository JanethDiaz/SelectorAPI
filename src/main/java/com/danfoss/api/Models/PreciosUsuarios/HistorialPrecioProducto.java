package com.danfoss.api.Models.PreciosUsuarios;

public class HistorialPrecioProducto {
    private int Id;
    private int IdPreciosUsuario;
    private int IdUsuarioRegistro;
    private byte Activo;

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
