package com.danfoss.api.Models.Usuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private int id;
    private String NombreCliente;
    private int IdUsuarioRegistra;
    private int IdTipoCliente;
    private TipoCliente tipoCliente;
    private int Descuento;
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public int getIdUsuarioRegistra() {
        return IdUsuarioRegistra;
    }

    public void setIdUsuarioRegistra(int idUsuarioRegistra) {
        IdUsuarioRegistra = idUsuarioRegistra;
    }

    public int getIdTipoCliente() {
        return IdTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        IdTipoCliente = idTipoCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getDescuento() {
        return Descuento;
    }

    public void setDescuento(int descuento) {
        Descuento = descuento;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Cliente> Listar() throws Exception {
        try {
            ArrayList<Cliente> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Cliente_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadCliente(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro Listar correctamente " + e.getMessage());
        }
    }

    public int Insertar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getNombreCliente());
            params.put("2", getTipoCliente().getId());
//            params.put("3", getIdUsuarioRegistra());
            params.put("3", 1);

            DataTable dt = new Persistencia().Query("CALL SP_Cliente_Insertar", params);
            return Integer.parseInt(dt.Rows.get(0).get("Id"));
        }
        catch (Exception e) {
            throw new Exception("Error al insertar cliente " + e.getMessage());
        }
    }

    public boolean Actualizar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getNombreCliente());
            params.put("3", getIdTipoCliente());

            new Persistencia().ExceuteNonQuery("CALL SP_Cliente_Actualizar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al insertar cliente " + e.getMessage());
        }
    }

    private Cliente loadCliente(Map<String, String> row) {
        Cliente c = new Cliente();

        c.setId(Integer.parseInt(row.get("Id")));
        c.setNombreCliente(row.get("NombreCliente"));
        c.setTipoCliente(new TipoCliente(Integer.parseInt(row.get("IdTipoCliente"))));

        return c;
    }
}
