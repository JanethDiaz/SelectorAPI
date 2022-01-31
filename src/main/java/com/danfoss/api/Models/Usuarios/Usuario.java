package com.danfoss.api.Models.Usuarios;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private int id;
    private String Usuario;
    private String Pasword;
    //private DateTimeFormat FechaRegistro;
    private int IdUsuarioRegistra;
    private int IdTipoUsuuario;
    private byte Status;
    private byte Activo;

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
                    u.setPasword(row.get("Password"));
                }
                u.setIdTipoUsuuario(Integer.parseInt(row.get("IdTipoUsuario")));
                u.setStatus(Byte.parseByte(row.get("Status")));
                u.setActivo(Byte.parseByte(row.get("Activo")));
                return u;

            } else {
                throw new Exception("Usuario no encontrado favor de verificar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar el usuario" + e.getMessage());
        }
    }

    public  boolean Insertar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getUsuario());
            params.put("2", getPasword());
            params.put("3", getIdUsuarioRegistra());
            params.put("4", getIdTipoUsuuario());

            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Insertar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error, Usuario ya registrado." + e.getMessage());
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
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Usuario_Eliminar", params);
            return true;
        } catch (Exception e) {
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

    private  Usuario loadUsuario(Map<String, String> row) {
        Usuario u = new Usuario();
        u.setId(Integer.parseInt(row.get("Id")));
        u.setUsuario(row.get("Usuario"));
        if(row.get("Password") != null ) {
            u.setPasword(row.get("Password"));
        }
        u.setIdTipoUsuuario(Integer.parseInt(row.get("IdTipoUsuario")));
        u.setStatus(Byte.parseByte(row.get("Status")));
        u.setActivo(Byte.parseByte(row.get("Activo")));

        return u;
    }
}
