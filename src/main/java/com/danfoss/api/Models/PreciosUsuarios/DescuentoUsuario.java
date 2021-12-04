package com.danfoss.api.Models.PreciosUsuarios;

public class DescuentoUsuario {
    private int Id;
    private int Porcentaje;
    private int IdUsuario;
    private int IdProducto;
    private byte Activo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        Porcentaje = porcentaje;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public byte getActivo() {
        return Activo;
    }

    public void setActivo(byte activo) {
        Activo = activo;
    }
}
