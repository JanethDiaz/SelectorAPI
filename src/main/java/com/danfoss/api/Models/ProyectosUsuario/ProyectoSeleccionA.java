package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Plantillas.PlantillaBase;
import com.danfoss.api.Models.Selecciones.PlantillaSeleccion;
import com.danfoss.api.Models.Selecciones.Seleccion;
import com.danfoss.api.Models.Selecciones.SeleccionGruposProductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProyectoSeleccionA extends PlantillaBase {
    private int idUsuario;
    private ArrayList<SeleccionGruposProductos> productosSeleccion;

    private double costoTotal;
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public ArrayList<SeleccionGruposProductos> getProductosSeleccion() {
        return productosSeleccion;
    }
    public void setProductosSeleccion(ArrayList<SeleccionGruposProductos> productosSeleccion) {
        this.productosSeleccion = productosSeleccion;
    }
    public double getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public ProyectoSeleccionA() {

    }

    ProyectoSeleccionA(PlantillaSeleccion p) {
        setIdProyecto(p.getIdProyecto());
        setIdSeleccion(p.getIdSeleccion());
        if (p.getSeleccion() != null) {
            setSeleccion(p.getSeleccion());
            setIdSeleccion(p.getSeleccion().getId());
        }
        setCantidad(p.getCantidad());
        setAreaSeleccion(p.getAreaSeleccion());
        setId(p.getId());
    }

    public  void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuario());
            params.put("2", getIdSeleccion());
            params.put("3", getIdProyecto());
            params.put("4", getCantidad());
            params.put("5", getAreaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_Insertar", params);
            if ( dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
            }
        }
        catch (Exception e) {
            throw new Exception("Error no se logro insertar" + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getCantidad());
            params.put("3", getAreaSeleccion());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectoSeleccion_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectoSeleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public ArrayList<ProyectoSeleccionA> Listar(int idProyecto) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idProyecto);
            ArrayList<ProyectoSeleccionA> result = new ArrayList<>();

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_ListarPorIdProyecto", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProyectoSeleccion(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }
    boolean Existe() {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdProyecto());
            params.put("2", getIdSeleccion());
            params.put("3", getAreaSeleccion());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_CargarPorId_IdSeleccion", params);

            return  dt.Rows.size() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    void CargarProductosSeleccion(int idSeleccion) {
        try
        {
            SeleccionGruposProductos seleccionGruposProductos = new SeleccionGruposProductos();
            setProductosSeleccion(seleccionGruposProductos.ListarPorIdSeleccion(idSeleccion));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  ProyectoSeleccionA loadProyectoSeleccion(Map<String, String> row) throws Exception {

        ProyectoSeleccionA ps = new ProyectoSeleccionA();
        ps.setId(Integer.parseInt(row.get("Id")));
        ps.setIdSeleccion(Integer.parseInt(row.get("IdSeleccion")));
        ps.setIdProyecto(Integer.parseInt(row.get("IdProyecto")));
        ps.setAreaSeleccion(row.get("AreaSeleccion"));
        ps.setDescripcionPlantilla(row.get("DescripcionPlantilla"));
        if (row.get("UrlCoolSelector") != null ) {
            ps.setUrlCoolSelector(row.get("UrlCoolSelector"));
        }


        if(row.get("CantidadSeleccion") != null) {
            ps.setCantidad(Integer.parseInt(row.get("CantidadSeleccion")));
        }

        if (row.get("CostoTotal") != null ) {
            ps.setCostoTotal( Double.parseDouble(row.get("CostoTotal")));
        }

        if ( getIdSeleccion() > 0 ) {
            Seleccion seleccion = new Seleccion();
            seleccion.setId(getIdSeleccion());
            seleccion.CargarPorId();
            ps.setSeleccion(seleccion);
        }

        return ps;
    }
}
