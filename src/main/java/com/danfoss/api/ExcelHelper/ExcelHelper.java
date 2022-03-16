package com.danfoss.api.ExcelHelper;

import com.danfoss.api.Models.Productos.Modelo;
import com.danfoss.api.Models.Productos.Producto;
//import com.mysql.cj.api.result.Row;
//import com.sun.deploy.security.ValidationState.TYPE;
//import javafx.scene.control.Cell;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "ListadoPrecios";

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
}
