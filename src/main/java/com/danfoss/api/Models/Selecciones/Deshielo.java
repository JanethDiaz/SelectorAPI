package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deshielo {
    private int id;
    private String descripcionDeshielo;
    private int idPadre;
    private int idSistema;
    private int etiqueta;
    private byte status;
    private byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionDeshielo() {
        return descripcionDeshielo;
    }

    public void setDescripcionDeshielo(String descripcionDeshielo) {
        this.descripcionDeshielo = descripcionDeshielo;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }
    public int getEtiqueta() {
        return etiqueta;
    }
    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }
    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        this.status = status;
    }
    public byte getActivo() {
        return activo;
    }
    public void setActivo(byte activo) {
        this.activo = activo;
    }
    public int getIdSistema() {
        return idSistema;
    }
    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }
    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcionDeshielo());
            params.put("2", getIdPadre());
            params.put("3", getEtiqueta());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcionDeshielo());
            params.put("3", getIdPadre());
            params.put("4", getEtiqueta());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public ArrayList<Deshielo> Listar() throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    public Deshielo CargarPorDescripcion() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getDescripcionDeshielo());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_CargarPorDescripcion", params);
            if (dt.Rows.size() > 0) {
                return loadDeshielo(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Deshielo();
    }

    public Deshielo CargarPorIdPadreDescripcion() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdPadre());
            params.put("2", getDescripcionDeshielo());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_CargarPorIdPadreDescripcion", params);
            if (dt.Rows.size() > 0) {
                return loadDeshielo(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Deshielo();
    }

    public Deshielo CargarPorId() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdPadre());

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_CargarPorId", params);
            if (dt.Rows.size() > 0) {
                return loadDeshielo(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Deshielo();
    }

    public Deshielo CargarPorId(int id) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", id);

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_CargarPorId", params);
            if (dt.Rows.size() > 0) {
                return loadDeshielo(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Deshielo();
    }

    public ArrayList<Deshielo> ListarPorTemperatura( int idTemperatura) throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idTemperatura);
            DataTable dt = new Persistencia().Query("CALL SP_TemperaturaDeshielo_Listar", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar las temperaturas por capacidad " + e.getMessage());
        }
    }

    public ArrayList<Deshielo> ListarPorIdPadre() throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            DataTable dt;
            if(getIdSistema() == 1 || getIdSistema() == 2) {
                 dt = new Persistencia().Query("CALL SP_Deshielo_Listar_Recirculado", params);
            }
            else {
                 dt = new Persistencia().Query("CALL SP_Deshielo_ListarPorIdPadre", params);
            }
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar el deshielo por idPadre " + e.getMessage());
        }
    }

    public ArrayList<Deshielo> ListarPorParametros(int idSistema, int idCapacidad, int idTemperatura) throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idSistema);
            params.put("2", idCapacidad);
            params.put("3", idTemperatura);

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo_ListarPorParametros", params);

            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar el deshielo por idPadre " + e.getMessage());
        }
    }

    public ArrayList<Deshielo> ListarPorParametrosIdPadre1(int idSistema, int idCapacidad, int idTemperatura, int idPadre) throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idSistema);
            params.put("2", idCapacidad);
            params.put("3", idTemperatura);
            params.put("4", idPadre);

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo1_ListarPorParametrosIdPadre", params);

            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar el deshielo por idPadre " + e.getMessage());
        }
    }

    public ArrayList<Deshielo> ListarPorParametrosIdPadre2(int idSistema, int idCapacidad, int idTemperatura, int idPadre) throws Exception {
        try {
            ArrayList<Deshielo> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idSistema);
            params.put("2", idCapacidad);
            params.put("3", idTemperatura);
            params.put("4", idPadre);

            DataTable dt = new Persistencia().Query("CALL SP_Deshielo2_ListarPorParametrosIdPadre", params);

            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadDeshielo(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar el deshielo por idPadre " + e.getMessage());
        }
    }

    private  Deshielo loadDeshielo(Map<String, String> row) {

        Deshielo d= new Deshielo();
        d.setId(Integer.parseInt(row.get("Id")));
        d.setDescripcionDeshielo(row.get("DescripcionDeshielo"));
        if (row.get("IdPadre") != null) {
            d.setIdPadre(Integer.parseInt(row.get("IdPadre")));
        }
        return d;
    }
}
