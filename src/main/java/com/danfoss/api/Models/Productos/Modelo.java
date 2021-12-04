package com.danfoss.api.Models.Productos;

public class Modelo {
    private int Id;
    private String Descripcion;
    private byte Status;
    private byte Activo;

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
