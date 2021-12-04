package com.danfoss.api.Models.PreciosUsuarios;

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
}
