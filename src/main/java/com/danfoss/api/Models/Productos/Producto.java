package com.danfoss.api.Models.Productos;

import com.danfoss.api.DAO.UploadProductosDAO;
import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.ExcelHelper.ExcelHelper;
import org.springframework.web.multipart.MultipartFile;

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
    private String UrlProductStore;
    private byte Status;
    private byte Activo;
    private double Precio;
    private String DescModelo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return Codigo;
    }
    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
    public double getPrecio() {
        return Precio;
    }
    public void setPrecio(double precio) {
        Precio = precio;
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
    public String getDescModelo() {
        return DescModelo;
    }
    public void setDescModelo(String descModelo) {
        DescModelo = descModelo;
    }
    public String getUrlProductStore() {
        return UrlProductStore;
    }
    public void setUrlProductStore(String urlProductStore) {
        UrlProductStore = urlProductStore;
    }

    public  Producto cargarPorId(int idProducto) throws Exception {

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", idProducto);

            DataTable dt = new Persistencia().Query("CALL SP_Producto_CargarPorId", params);

            if (dt.Rows.size() > 0) {
                return loadProducto(dt.Rows.get(0));
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar producto" + e.getMessage());
        }

        return new Producto();
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getCodigo());
            params.put("3", getModelo().getId());
            params.put("4", getDescripcion());
            params.put("5", getUrlProductStore());

            new Persistencia().ExceuteNonQuery("CALL SP_Producto_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }
    private boolean ActualizarDescripcion() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getDescripcion());

            new Persistencia().ExceuteNonQuery("CALL SP_Producto_ActualizarDescripcion", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro la modificacion" + e.getMessage());
        }
    }
    public  int Insertar() {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getCodigo());
            params.put("2", getDescripcion());
            params.put("3", getIdUsuarioRegistro());
            params.put("4", getModelo().getId());
            params.put("5", getUrlProductStore());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Insertar", params);

            return Integer.parseInt(dt.Rows.get(0).get("Id"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public  void ActualizarListaPrecios(UploadProductosDAO uploadProductosDAO) throws RuntimeException {
        try
        {
            MultipartFile file = uploadProductosDAO.getFile();
            List<Producto> productos = ExcelHelper.excelToProducts(file.getInputStream());
            for (Producto p : productos) {
                p.setIdUsuarioRegistro(uploadProductosDAO.getIdUsuario());
                if (!p.getCodigo().equals("")) {
                    Modelo m = new Modelo();
                    m = m.cargarPorDescripcion(p.getModelo().getDescripcion());
                    if (m.getId() > 0) {
                        p.setModelo(m);
                    } else {
                        p.setModelo(p.getModelo().Insertar());
                    }
                    Producto producto = cargarPorCodigo(p.getCodigo());
                    if (producto.getId() > 0) {
                        producto.setDescripcion(p.getDescripcion());
                        producto.ActualizarDescripcion();

                        HistorialPrecioProducto hpp = new HistorialPrecioProducto(producto.getId());
                        hpp.setIdProducto(producto.getId());
                        hpp.DesactivarPorIdProducto();
                        hpp.setPrecio(p.getPrecio());
                        hpp.setIdUsuarioRegistro(uploadProductosDAO.getIdUsuario());
                        hpp.Insertar();
                    }
                    else {
                        if ( p.getModelo().getId() > 0 ) {
                            int idProducto = p.Insertar();
                            if (idProducto > 0) {
                                HistorialPrecioProducto hpp = new HistorialPrecioProducto();
                                hpp.setIdProducto(idProducto);
                                hpp.setPrecio(p.getPrecio());
                                hpp.setIdUsuarioRegistro(1);
                                hpp.Insertar();
                            }
                        }
                    }
                }
            }
        }
        catch( Exception e ) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public  Producto cargarPorCodigo(String codigo) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", codigo);

            DataTable dt = new Persistencia().Query("CALL SP_Producto_CargarPorCodigo", params);

            if (dt.Rows.size() > 0) {
                return loadProducto(dt.Rows.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Producto();
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Producto_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public static boolean ValidarCodigo(String codigo) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();

            DataTable dt = new Persistencia().Query("CALL SP_Producto_ValidarCodigo", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro la eliminacion" + e.getMessage());
        }
    }
    public  ArrayList<Producto> Listar() throws Exception {
        try
        {
            ArrayList<Producto> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Producto_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadProducto(row));
                }
            }
            return result;
        }
        catch (Exception e) {
            throw new Exception("Error no se logro listar correctamente" + e.getMessage());
        }
    }
    private  Producto loadProducto(Map<String, String> row) throws Exception {
        Producto p = new Producto();
        p.setId(Integer.parseInt(row.get("Id")));
        p.setCodigo(row.get("Codigo"));
        p.setDescripcion(row.get("Descripcion"));
        p.setModelo(new Modelo(Integer.parseInt(row.get("IdModelo"))));

        if(row.get("UrlProductStore") != null )
            p.setUrlProductStore(row.get("UrlProductStore"));
        if (row.get("Status") != null)
            p.setStatus(Byte.parseByte(row.get("Status")));
        if (row.get("Activo") != null)
            p.setActivo(Byte.parseByte(row.get("Activo")));
        if(row.get("Precio") != null)
            p.setPrecio(Double.parseDouble(row.get("Precio")));
        if (row.get("DescripcionModelo") != null)
            p.setDescModelo(row.get("DescripcionModelo"));
        if(row.get("IdUsuarioRegistra") != null)
            p.setIdUsuarioRegistro(Integer.parseInt(row.get("IdUsuarioRegistra")));
        if(row.get("UrlProductStore") != null)
            p.setUrlProductStore(row.get("UrlProductStore"));
        return p;
    }
}
