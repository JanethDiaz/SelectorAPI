package com.danfoss.api.ExcelHelper;

import com.danfoss.api.Models.Productos.Modelo;
import com.danfoss.api.Models.Productos.Producto;
//import com.mysql.cj.api.result.Row;
//import com.sun.deploy.security.ValidationState.TYPE;
//import javafx.scene.control.Cell;
import com.danfoss.api.Models.Selecciones.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "ListadoPrecios";
    static String SHEET_BD_SELECCIONES = "Base de Datos-Selecciones";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Producto> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Producto> productoList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                int cellIdx = 0;
                Producto producto = new Producto();

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            producto.setCodigo(currentCell.getStringCellValue());
                            break;

                        case 1:
                            producto.setModelo(new Modelo(currentCell.getStringCellValue()));
                            break;

                        case 2:
                            producto.setDescripcion(currentCell.getStringCellValue());
                            break;

                        case 3:
                            producto.setPrecio(Double.parseDouble(String.valueOf(currentCell.getNumericCellValue())));
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                productoList.add(producto);
            }
            workbook.close();
            return productoList;
        }
        catch (IOException e)
        {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static ArrayList<PlantillaSeleccion> excelToSelecciones(InputStream is) throws RuntimeException {
        ArrayList<PlantillaSeleccion> plantillaSeleccions = new ArrayList<>();
        ArrayList<Seleccion> seleccions = new ArrayList<>();
        try
        {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET_BD_SELECCIONES);
            Iterator<Row> rows = sheet.iterator();
            GrupoSeleccion grupoSeleccion = new GrupoSeleccion();
            SeleccionGruposProductos seleccionGruposProductos = new SeleccionGruposProductos();

            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRowSeleccion = currentRow.iterator();
                Iterator<Cell> cellsInRowGrupos = currentRow.iterator();
                Iterator<Cell> cellsInRowGruposProducto = currentRow.iterator();


                Seleccion seleccion = new Seleccion().crearSeleccion(cellsInRowSeleccion);

                if (seleccion.getSistema_svc() == null) {
                    rowNumber++;
                    continue;
                }

                seleccion.CargarPorParametros();

                if (seleccion.getId() > 0) {
                    grupoSeleccion.setIdSeleccion(seleccion.getId());
                }
                else {
                    PlantillaSeleccion plantilla = new PlantillaSeleccion();

                    plantilla.crearNombre(seleccion);
                    plantilla.Insertar();
                    plantillaSeleccions.add(plantilla);
                    seleccion.Insertar(plantilla.getId());
                    grupoSeleccion.setIdSeleccion(seleccion.getId());
                }
                seleccions.add(seleccion);
                grupoSeleccion.crearGrupo(cellsInRowGrupos);
                seleccionGruposProductos.setIdGrupo(grupoSeleccion.getId());
                seleccionGruposProductos.crearSeleccionGruposProductos(cellsInRowGruposProducto);
                if (seleccionGruposProductos.getIdProducto() > 0 ) {
                    seleccionGruposProductos.Insertar();
                }
                rowNumber++;
            }
            workbook.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("fail to parse Excel file: " + e);
        }

        return plantillaSeleccions;
    }
}
