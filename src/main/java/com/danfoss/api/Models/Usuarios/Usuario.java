package com.danfoss.api.Models.Usuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private int id;
    private String Usuario;
    private String Password;
    //private DateTimeFormat FechaRegistro;
    private int IdUsuarioRegistra;
    private int IdTipoUsuuario;
    private int IdCliente;
    private byte Status;
    private byte Activo;
    private TipoUsuario tipoUsuario;
    public int getId() {
        return id;
    }
    public void setId(int Id) {
        id = Id;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
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
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public int getIdCliente() {
        return IdCliente;
    }
    public void setIdCliente(int idCliente) {
        this.IdCliente = idCliente;
    }

    public static Usuario cargarPorNombre(String username) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", username);

            //ejecutar SP y nos regresa como resultado un DataTable (filas y columnas)
            DataTable dt = new Persistencia().Query("CALL SP_Usuario_CargarPorNombre", params);
            if (dt.Rows.size() > 0) {
                //llenar un objeto obteniendo los valores de cada registro
                Usuario u = new Usuario();
                Map<String, String> row = dt.Rows.get(0);
                u.setId(Integer.parseInt(row.get("Id")));
                u.setUsuario(row.get("Usuario"));
                if(row.get("Password") != null ) {
                    u.setPassword(row.get("Password"));
                }
                if (row.get("IdCliente") != null ) {
                    u.setIdCliente(Integer.parseInt(row.get("IdCliente")));
                }
                u.setIdTipoUsuuario(Integer.parseInt(row.get("IdTipoUsuario")));
                u.setStatus(Byte.parseByte(row.get("Status")));
                u.setActivo(Byte.parseByte(row.get("Activo")));
                return u;

            } else {
                throw new Exception("Usuario no encontrado favor de verificar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al cargar el usuario por nombre " + e);
        }
    }

    public Usuario login() throws Exception{
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getUsuario());
            params.put("2", getPassword());

            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Login", params);
            if (dt.Rows.size() > 0) {
                Usuario u = new Usuario();
                Map<String, String> row = dt.Rows.get(0);
                u.setId(Integer.parseInt(row.get("Id")));
                u.setTipoUsuario(new TipoUsuario(Integer.parseInt(row.get("IdTipoUsuario")), row.get("Descripcion")));
                u.setStatus(Byte.parseByte(row.get("Status")));
                return u;
            }
        }
        catch (Exception e) {
            throw new Exception("Error al loguear el usuario " + e);
        }
        return new Usuario();
    }

    public  boolean Insertar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getUsuario());
            params.put("2", getPassword());
            params.put("3", 1);
            params.put("4", 3);
            params.put("5", getIdCliente());

            new Persistencia().ExceuteNonQuery("CALL SP_Usuario_Insertar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error al insertar el usuario " + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getUsuario());
            params.put("3", getIdTipoUsuuario());

            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Eliminar", params);
            return true;
        }
        catch (Exception e) {
            throw new Exception("Error no se logro la Eliminacion" + e.getMessage());
        }
    }

    public  ArrayList<Usuario> Listar() throws Exception {
        try {
            ArrayList<Usuario> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadUsuario(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro Listar correctamente " + e.getMessage());
        }
    }

    public  ArrayList<Usuario> ListarPorCliente(int idCliente) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idCliente);

            ArrayList<Usuario> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Usuario_ListarPorIdCliente", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadUsuario(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro Listar los usuarios del cliente" + e.getMessage());
        }
    }

    private  Usuario loadUsuario(Map<String, String> row) {
        Usuario u = new Usuario();
        u.setId(Integer.parseInt(row.get("Id")));
        u.setUsuario(row.get("Usuario"));
        if(row.get("Password") != null ) {
            u.setPassword(row.get("Password"));
        }
//        u.setIdTipoUsuuario(Integer.parseInt(row.get("IdTipoUsuario")));
        u.setStatus(Byte.parseByte(row.get("Status")));
//        u.setActivo(Byte.parseByte(row.get("Activo")));

        return u;
    }
}
