package com.danfoss.api.Models.ProyectosUsuario;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Selecciones.PlantillaSeleccion;
import com.danfoss.api.Models.Selecciones.SeleccionGruposProductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectosUsuario {
    private int id;
    private String nombreProyecto;
    private int idUsuario;
    private int idCliente;
    private List<PlantillaSeleccion> plantillas;
    private ArrayList<ProyectoSeleccionA> selecciones;
    private ArrayList<SeleccionGruposProductos> productosSeleccion = new ArrayList<>();
    private String fechaProyecto;
    private double costoTotal;
    private byte esDescuento;
    private double descuento;
    private int cantidadSelecciones;
    private byte activo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProyecto() {
        return nombreProyecto;
    }
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public byte getActivo() {
        return activo;
    }
    public void setActivo(byte activo) {
        this.activo = activo;
    }
    public List<PlantillaSeleccion> getPlantillas() {
        return plantillas;
    }
    public void setPlantillas(List<PlantillaSeleccion> plantillas) {
        this.plantillas = plantillas;
    }
    public String getFechaProyecto() {
        return fechaProyecto;
    }
    public void setFechaProyecto(String fechaProyecto) {
        this.fechaProyecto = fechaProyecto;
    }
    public double getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    public int getCantidadSelecciones() {
        return cantidadSelecciones;
    }
    public void setCantidadSelecciones(int cantidadSelecciones) {
        this.cantidadSelecciones = cantidadSelecciones;
    }
    public ArrayList<ProyectoSeleccionA> getSelecciones() {
        return selecciones;
    }
    public void setSelecciones(ArrayList<ProyectoSeleccionA> selecciones) {
        this.selecciones = selecciones;
    }
    public ArrayList<SeleccionGruposProductos> getProductosSeleccion() {
        return productosSeleccion;
    }
    public void setProductosSeleccion(ArrayList<SeleccionGruposProductos> productosSeleccion) {
        this.productosSeleccion = productosSeleccion;
    }
    public byte getEsDescuento() {
        return esDescuento;
    }
    public void setEsDescuento(byte esDescuento) {
        this.esDescuento = esDescuento;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void Insertar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getNombreProyecto());
            params.put("2", getIdUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Insertar", params);

            if ( dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
//                if (getId() > 0) {
//                    insertarSelecciones();
//                }
            }
        }
        catch (Exception e) {
            throw new Exception("Error al intentar guardar el proyecto " + e.getMessage());
        }
    }

    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getNombreProyecto());

            new Persistencia().ExceuteNonQuery("CALL SP_ProyectosUsario_ActualizarNombre", params);
            //insertarSelecciones();
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro Actualizar" + e.getMessage());
        }
    }

    private void insertarSelecciones() throws Exception {
        try
        {
            if (getPlantillas().size() > 0) {
                for (PlantillaSeleccion p:
                        getPlantillas()) {
                    ProyectoSeleccionA ps = new ProyectoSeleccionA(p);
                    if (!ps.Existe() && ps.getSeleccion() != null) {
                        ps.setIdUsuario(getIdUsuario());
                        ps.setIdProyecto(getId());
                        ps.Insertar();
                    }
                    else {
                        ps.Actualizar();
                    }
                }
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ArrayList<ProyectosUsuario> Listar() throws Exception {
        try {
            ArrayList<ProyectosUsuario> result = new ArrayList<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdUsuario());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_ListarPorIdUsuario", params);

            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProyectosUusario(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectosUsuario_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    private int cargarCantidadSelecciones()  {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_ProyectoSeleccion_CargarCantidadSeleccionesPorIdProyecto", params);

            if (dt.Rows.size() > 0) {
                if (dt.Rows.get(0).get("Cantidad") != null ) {
                    return Integer.parseInt(dt.Rows.get(0).get("Cantidad"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void CargarSelecciones() {
        try {
            ProyectoSeleccionA proyectoSeleccionA = new ProyectoSeleccionA();
            setSelecciones(proyectoSeleccionA.Listar(getId()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    private double CalcularCostoTotal() {
        double result = 0f;
        try {
            CargarSelecciones();
            for (ProyectoSeleccionA proyectoSeleccionA:
                 getSelecciones()) {
                SeleccionGruposProductos seleccionGruposProductos = new SeleccionGruposProductos();
                for ( SeleccionGruposProductos producto:
                        seleccionGruposProductos.ListarPorIdSeleccion(proyectoSeleccionA.getIdSeleccion(), getIdCliente())) {
                    producto.setDescripcionPlantilla(proyectoSeleccionA.getDescripcionPlantilla());
                    producto.setAreaSeleccion(proyectoSeleccionA.getAreaSeleccion());
                    producto.setCantidad(producto.getCantidad() * proyectoSeleccionA.getCantidad());
                    if (getEsDescuento() == 1) {
                        producto.setPrecio(calcularPrecioDescuento(producto.getPrecio()));
                    }
                    double total = producto.getPrecio()  * producto.getCantidad();
                    producto.setPrecioTotal(total);
                    this.productosSeleccion.add( producto );
                    result += ( producto.getCantidad()  * producto.getPrecio() );
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private double calcularPrecioDescuento(double precio) {
        return precio - ( getDescuento() * precio / 100 );
    }

    private  ProyectosUsuario loadProyectosUusario(Map<String, String> row) {

        ProyectosUsuario pu = new ProyectosUsuario();
        pu.setIdCliente(getIdCliente());
        pu.setId(Integer.parseInt(row.get("Id")));
        pu.setNombreProyecto(row.get("NombreProyecto"));
        pu.setFechaProyecto(row.get("FechaRegistro"));
        if (row.get("EsDescuento")!= null) {
            pu.setEsDescuento(Byte.parseByte(row.get("EsDescuento")));
        }
        if (row.get("Porcentaje")!= null) {
            pu.setDescuento(Double.parseDouble(row.get("Porcentaje")));
        }
        pu.setCantidadSelecciones(pu.cargarCantidadSelecciones());
        pu.setCostoTotal(pu.CalcularCostoTotal());
        if (row.get("Activo") != null)
            pu.setActivo(Byte.parseByte(row.get("Activo")));

        return pu;
    }
}
