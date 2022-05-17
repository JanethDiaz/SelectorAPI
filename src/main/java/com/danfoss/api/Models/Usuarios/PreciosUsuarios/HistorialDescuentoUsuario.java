package com.danfoss.api.Models.Usuarios.PreciosUsuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.HashMap;

public class HistorialDescuentoUsuario {
    private int Id;
    private double Porcentaje;
    private int IdUsuarioRegistro;
    private byte Activo;
    private int IdCliente;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
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
    public double getPorcentaje() {
        return Porcentaje;
    }
    public void setPorcentaje(double porcentaje) {
        Porcentaje = porcentaje;
    }
    public int getIdCliente() {
        return IdCliente;
    }
    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public void Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuarioRegistro());
            params.put("2", getPorcentaje());
            params.put("3", getIdCliente());
            DataTable dt = new Persistencia().Query("CALL SP_HistorialDescuentosUsuario_Insertar", params);
        }
        catch (Exception e) {
            throw new Exception("Error al insertar en la tabla HistorialDescuentosUsuario " + e.getMessage());
        }
    }

    public  void DesactivarPorIdCliente () throws  Exception{
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdCliente());

            new Persistencia().ExceuteNonQuery("CALL SP_HistorialDescuentosUsuario_Desactivar", params);

        } catch (Exception e) {
            throw new Exception("Error no se logro desactivar" + e.getMessage());
        }
    }
}
