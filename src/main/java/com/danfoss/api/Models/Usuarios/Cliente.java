package com.danfoss.api.Models.Usuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Usuarios.PreciosUsuarios.HistorialDescuentoUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private int id;
    private String NombreCliente;
    private int IdUsuarioRegistra;
    private int IdTipoCliente;
    private TipoCliente tipoCliente;
    private double Descuento;
    private byte EsDescuento;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private String TipoPrecio;
    private String tipoClienteDesc;
    private byte Status;

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
    public double getDescuento() {
        return Descuento;
    }
    public void setDescuento(double descuento) {
        Descuento = descuento;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public String getTipoPrecio() {
        return TipoPrecio;
    }
    public void setTipoPrecio(String tipoPrecio) {
        this.TipoPrecio = tipoPrecio;
    }
    public String getTipoClienteDesc() {
        return tipoClienteDesc;
    }
    public void setTipoClienteDesc(String tipoClienteDesc) {
        this.tipoClienteDesc = tipoClienteDesc;
    }
    public byte getEsDescuento() {
        return EsDescuento;
    }
    public void setEsDescuento(byte esDescuento) {
        EsDescuento = esDescuento;
    }
    public byte getStatus() {
        return Status;
    }
    public void setStatus(byte status) {
        Status = status;
    }

    public Cliente(){}

    public Cliente(int idCliente){
        setId(idCliente);
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

    public Cliente CargarPorId() throws Exception {
        Cliente result = new Cliente();
        HashMap<String, Object> params = new HashMap<>();
        params.put("1", getId());

        DataTable dt = new Persistencia().Query("CALL SP_Cliente_CargarPorId", params);
        if (dt.Rows.size() > 0) {
            result = loadCliente(dt.Rows.get(0));
        }
        return result;
    }

    public void Insertar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getNombreCliente());
            params.put("2", getTipoCliente().getId());
            params.put("3", getIdUsuarioRegistra());
            params.put("4", getEsDescuento());

            DataTable dt = new Persistencia().Query("CALL SP_Cliente_Insertar", params);
            if(dt.Rows.size() > 0) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
                if (getTipoCliente().getId() == 1) {
                    if (getEsDescuento() == 1) {
                       InsertarDescuento();
                    }
                }
            }
        }
        catch (Exception e) {
            throw new Exception("Error al insertar cliente " + e.getMessage());
        }
    }

    private void InsertarDescuento() throws Exception {

            HistorialDescuentoUsuario historialDescuentoUsuario = new HistorialDescuentoUsuario();
            historialDescuentoUsuario.setIdCliente(getId());

            //Descativamos cualquier descuento anterior asignado al cliente
            historialDescuentoUsuario.DesactivarPorIdCliente();

            //Insertamos nuevo porcentaje de descuento del cliente
            historialDescuentoUsuario.setPorcentaje(getDescuento());
            historialDescuentoUsuario.setIdUsuarioRegistro(getIdUsuarioRegistra());
            historialDescuentoUsuario.Insertar();
    }

    public boolean Actualizar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getNombreCliente());
            params.put("3", getTipoCliente().getId());
            params.put("4", getEsDescuento());

            new Persistencia().ExceuteNonQuery("CALL SP_Cliente_Actualizar", params);
            if (getTipoCliente().getId() == 1) {
                if (getEsDescuento() == 1) {
                    InsertarDescuento();
                }
            }
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al insertar cliente " + e.getMessage());
        }
    }

    public boolean Eliminar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            new Persistencia().ExceuteNonQuery("CALL SP_Cliente_Eliminar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al eliminar el cliente " + e.getMessage());
        }
    }

    public boolean Desactivar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            new Persistencia().ExceuteNonQuery("CALL SP_Cliente_Desactivar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al desactivar cliente " + e.getMessage());
        }
    }

    public boolean Activar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            new Persistencia().ExceuteNonQuery("CALL SP_Cliente_Activar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al activar cliente " + e.getMessage());
        }
    }

    private Cliente loadCliente(Map<String, String> row) {
        Cliente c = new Cliente();

        c.setId(Integer.parseInt(row.get("Id")));
        c.setNombreCliente(row.get("NombreCliente"));
        c.setTipoCliente(new TipoCliente(Integer.parseInt(row.get("IdTipoCliente"))));
        c.setTipoClienteDesc(row.get("DescripcionTipoCliente"));
        c.setStatus(Byte.parseByte(row.get("Status")));
        if (row.get("EsDescuento") != null) {
            c.setEsDescuento(Byte.parseByte(row.get("EsDescuento")));
        }

        if (c.getTipoCliente().getId() == 2) {
            c.setTipoPrecio("-");
        }
        else {
            if (c.getEsDescuento() == 1) {
                c.setTipoPrecio("Descuento");
                if (row.get("Porcentaje") != null) {
                    c.setDescuento(Double.parseDouble(row.get("Porcentaje")));
                }
            }
            else {
                c.setTipoPrecio("Listado de Precios");
            }
        }
        return c;
    }
}
