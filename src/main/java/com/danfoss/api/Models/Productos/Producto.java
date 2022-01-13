package com.danfoss.api.Models.Productos;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.ExcelHelper.ExcelHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto {
    private int id;
    private String Codigo;
    private String Descripcion;
    private int IdUsuarioRegistro;
    private Modelo modelo;
    private byte Status;
    private byte Activo;
    private double Precio;

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setId(int id) {
        id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public int getIdUsuarioRegistro() {
        return IdUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(int idUsuarioRegistro) {
        IdUsuarioRegistro = idUsuarioRegistro;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public  Producto cargarPorId(int idProducto) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idProducto);

            DataTable dt = new Persistencia().Query("CALL SP_Productos_CargarPorId", params);

            if (dt.Rows.size() > 0) {
                return loadProducto(dt.Rows.get(0));
            } else {
                throw new Exception("Producto no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getCodigo());
            params.put("3", getModelo().getId());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }
    public boolean ActualizarDescripcion() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcion());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_ActualizarDescripcion", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }
    public  boolean Insertar() throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getCodigo());
            params.put("2", getDescripcion());
            params.put("3", getIdUsuarioRegistro());
            params.put("4", getModelo().getId());

            DataTable dt = new Persistencia().Query("CALL SP_Productos_Insertar", params);
            return  true;
        } catch (Exception e) {
            throw new Exception("Error, producto ya existente" + e.getMessage());
        }
    }
    public  void ActualizarListaPrecios(MultipartFile file) {

        try {
            List<Producto> productos = ExcelHelper.excelToProducts(file.getInputStream());
            for (Producto p : productos) {
                Modelo m = new Modelo();
                m = m.cargarPorDescripcion(p.getModelo().getDescripcion());
                if (m.getId() > 0) {
                    p.setModelo(m);
                } else {
                    p.setModelo(p.getModelo().Insertar());
                }
                Producto producto = cargarPorCodigo(p.getCodigo());
                if (producto.getId() > 0){
                    producto.setDescripcion(p.getDescripcion());
                    producto.ActualizarDescripcion();

                    HistorialPrecioProducto hpp = new HistorialPrecioProducto(producto.getId());
                    hpp.setIdProducto(producto.getId());
                    hpp.DesactivarPorIdProducto();
                    hpp.setPrecio(p.getPrecio());
                    hpp.setIdUsuarioRegistro(1);
                    hpp.Insertar();
                } else {
                    p.Insertar();

                    HistorialPrecioProducto hpp = new HistorialPrecioProducto(producto.getId());
                    hpp.setIdProducto(producto.getId());
                    hpp.setPrecio(p.getPrecio());
                    hpp.setIdUsuarioRegistro(1);
                    hpp.Insertar();
                }
            }

        }
        catch(Exception e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public  Producto cargarPorCodigo(String codigo) throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", codigo);

            DataTable dt = new Persistencia().Query("CALL SP_Producto_CargarPorCodigo", params);

            if (dt.Rows.size() > 0) {
                return loadProducto(dt.Rows.get(0));
            } else {
                throw new Exception("Producto no encontrado favor de validar sus credenciales");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }
    }

    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Productos_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public  ArrayList<Producto> Listar() throws Exception {
        try {
            ArrayList<Producto> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Producto_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProducto(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }
    private  Producto loadProducto(Map<String, String> row) {

        Producto p = new Producto();
        p.setId(Integer.parseInt(row.get("Id")));
        p.setCodigo(row.get("Codigo"));
        p.setDescripcion(row.get("Descripcion"));
        if(row.get("IdUsuarioRegistra") != null)
            p.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistra")));
        p.setModelo(new Modelo(Integer.parseInt(row.get("IdModelo"))));
        if (row.get("Status") != null)
            p.setStatus(Byte.parseByte(row.get("Status")));
        if (row.get("Activo") != null)
            p.setActivo(Byte.parseByte(row.get("Activo")));

        return p;
    }
}
