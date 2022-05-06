package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.Models.Productos.Producto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SeleccionGruposProductos {
    private int id;
    private int idProducto;
    private int idGrupo;
    private byte activo;
    private double precio;
    private double precioTotal;
    //encabezados de la tabla
    private String descripcionSeleccion;
    private int cantidad;
    private int cantidadFija;
    private String codigo;
    private String descripcionModelo;
    private String descripcionProdcto;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cnt) {
        cantidad = cnt;
    }
    public byte getActivo() {
        return activo;
    }
    public void setActivo(byte activo) {
        this.activo = activo;
    }
    public String getDescripcionSeleccion() {
        return descripcionSeleccion;
    }
    public void setDescripcionSeleccion(String descripcionSeleccion) {
        this.descripcionSeleccion = descripcionSeleccion;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcionModelo() {
        return descripcionModelo;
    }
    public void setDescripcionModelo(String descripcionModelo) {
        this.descripcionModelo = descripcionModelo;
    }
    public String getDescripcionProdcto() {
        return descripcionProdcto;
    }
    public void setDescripcionProdcto(String descripcionProdcto) {
        this.descripcionProdcto = descripcionProdcto;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    public  void Insertar() throws Exception {
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdProducto());
            params.put("2", getIdGrupo());
            params.put("3", getCantidad());
            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Insertar", params);
        }
        catch (Exception e)
        {
            throw new Exception("Error al insertar SeleccionGruposProductos IdProducto: " + getIdProducto()
                    + " IdGrupo " + getIdGrupo() + "Cantidad " + getCantidad() + " " + e.getMessage() );
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdProducto());
            params.put("3", getIdGrupo());
            params.put("4", getCantidad());

            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Actualizar", params);
            return true;

        } catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }
    public int getCantidadFija() {
        return cantidadFija;
    }
    public void setCantidadFija(int cantidadFija) {
        this.cantidadFija = cantidadFija;
    }

    public ArrayList<SeleccionGruposProductos> Listar() throws Exception {
        try {
            ArrayList<SeleccionGruposProductos> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_SeleccionGruposProductos_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSeleccionGruposProducto(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }
    public ArrayList<SeleccionGruposProductos> ListarPorIdSeleccion(int IdSeleccion) throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", IdSeleccion);
            ArrayList<SeleccionGruposProductos> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_GrupoSeleccion_ListarPorIdSeleccion", params);
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSeleccionGruposProducto(row));
                }
            }
            return result;
        }
        catch (Exception e) {
            throw new Exception("Error al listar los productos de la seleccion " + e.getMessage());
        }
    }


    public void crearSeleccionGruposProductos(Iterator<Cell> cellsInRow) throws IOException {
        int cellIdx = 1;
        try {
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();

                if (cellIdx > 8) {
                    break;
                }

                switch (cellIdx) {
                    case 7:
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            int valor = (int) Double.parseDouble(String.valueOf(currentCell.getNumericCellValue()));
                            setCantidad(valor);
                        }
                        else {
                            cellIdx++;
                            continue;
                        }
                        break;
                    case 8:
                        Producto producto = new Producto();
                        if (currentCell.getCellType() == CellType.STRING) {
                            if (!currentCell.getStringCellValue().isEmpty()) {
                                producto = producto.cargarPorCodigo(currentCell.getStringCellValue());
                                setIdProducto(producto.getId());
                            }
                        }
                        break;
                    default:
                        break;
                }
                cellIdx++;
            }
        }
        catch (Exception e) {
            throw new IOException("Error al leer celda en crearSeleccionGruposProductos " + e.getMessage());
        }

    }
    private  SeleccionGruposProductos loadSeleccionGruposProducto(Map<String, String> row) {
        SeleccionGruposProductos sgp = new SeleccionGruposProductos();
        sgp.setId(Integer.parseInt(row.get("Id")));
        if (row.get("DescripcionSeleccion") != null ) {
            sgp.setDescripcionSeleccion(row.get("DescripcionSeleccion"));
        }
        if (row.get("Cantidad") != null ) {
            sgp.setCantidad(Integer.parseInt(row.get("Cantidad")));
            sgp.setCantidadFija(Integer.parseInt(row.get("Cantidad")));
        }
        if (row.get("Codigo") != null ) {
            sgp.setCodigo(row.get("Codigo"));
        }
        if (row.get("DescripcionModelo") != null ) {
            sgp.setDescripcionModelo(row.get("DescripcionModelo"));
        }
        if (row.get("Descripcion") != null ) {
            sgp.setDescripcionProdcto(row.get("Descripcion"));
        }
        if (row.get("Precio") != null) {
            sgp.setPrecio(Double.parseDouble(row.get("Precio")));
            double total = sgp.getPrecio()  * sgp.getCantidad();
            sgp.setPrecioTotal(total);
        }
        return sgp;
    }
}

