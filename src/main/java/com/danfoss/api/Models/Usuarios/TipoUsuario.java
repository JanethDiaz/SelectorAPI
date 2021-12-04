package com.danfoss.api.Models.Usuarios;

public class TipoUsuario {
    private int Id;
    private String Descripcion;
    private  byte Activo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public byte getActivo() {
        return Activo;
    }

    public void setActivo(byte activo) {
        Activo = activo;
    }
}
