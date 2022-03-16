package com.danfoss.api.Models.Usuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.Map;

public class TipoCliente {
    private int id;
    private String DescripcionTipoCliente;

    public TipoCliente() {}
    public TipoCliente(int idTipoCliente) {
        setId(idTipoCliente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionTipoCliente() {
        return DescripcionTipoCliente;
    }

    public void setDescripcionTipoCliente(String descripcionTipoCliente) {
        DescripcionTipoCliente = descripcionTipoCliente;
    }

    public ArrayList<TipoCliente> Listar() throws Exception {
        try {
            ArrayList<TipoCliente> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_TipoCliente_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    TipoCliente tc = new TipoCliente();
                    tc.setId(Integer.parseInt(row.get("Id")));
                    tc.setDescripcionTipoCliente(row.get("DescripcionTipoCliente"));
                    result.add(tc);
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro Listar correctamente " + e.getMessage());
        }
    }
}
