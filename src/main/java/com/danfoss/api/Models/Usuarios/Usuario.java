package com.danfoss.api.Models.Usuarios;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
    private int Id;
    private String Usuario;
    private String Pasword;
    //private DateTimeFormat FechaRegistro;
    private int IdUsuarioRegistra;
    private int IdTipoUsuuario;
    private byte Status;
    private byte Activo;

    public static Usuario cargarPorNombre(String username) {
        return new Usuario();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPasword() {
        return Pasword;
    }

    public void setPasword(String pasword) {
        Pasword = pasword;
    }

    public int getIdUsuarioRegistra() {
        return IdUsuarioRegistra;
    }

    public void setIdUsuarioRegistra(int idUsuarioRegistra) {
        IdUsuarioRegistra = idUsuarioRegistra;
    }

    public int getIdTipoUsuuario() {
        return IdTipoUsuuario;
    }

    public void setIdTipoUsuuario(int idTipoUsuuario) {
        IdTipoUsuuario = idTipoUsuuario;
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
}
