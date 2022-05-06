package com.danfoss.api.Models.Selecciones;

import com.danfoss.api.DataAccess.DataTable;
import com.danfoss.api.DataAccess.Persistencia;
import com.danfoss.api.ExcelHelper.ExcelHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class Seleccion {
    private int id;
    private Sistema_SVC sistema_svc;
    private Capacidad capacidad;
    private Temperatura temperatura;
    private Deshielo deshielo;
    private int idSistema_SVC;
    private int idCapacidad;
    private int idDeshielo;
    private int idTemperatura;
    private int urlCoolSelector;
    private byte activo;
    private byte status;
    private int idPlantillaSeleccion;
    private String areaSeleccion;

    //Encabezados de la tabla
    private int capacidadDesc;
    private String temperaturaDesc;
    private String deshielo1;
    private String deshielo2;
    private String deshielo3;
    private String tituloPlantilla;
    public String getAreaSeleccion() {
        return areaSeleccion;
    }
    public void setAreaSeleccion(String areaSeleccion) {
        this.areaSeleccion = areaSeleccion;
    }
    public int getIdPlantillaSeleccion() {
        return idPlantillaSeleccion;
    }
    public void setIdPlantillaSeleccion(int idPlantillaSeleccion) {
        this.idPlantillaSeleccion = idPlantillaSeleccion;
    }
    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdSistema_SVC() {
        return idSistema_SVC;
    }
    public void setIdSistema_SVC(int idSistema_SVC) {
        this.idSistema_SVC = idSistema_SVC;
    }
    public int getIdCapacidad() {
        return idCapacidad;
    }
    public void setIdCapacidad(int idCapacidad) {
        this.idCapacidad = idCapacidad;
    }
    public int getIdDeshielo() {
        return idDeshielo;
    }
    public void setIdDeshielo(int idDeshielo) {
        this.idDeshielo = idDeshielo;
    }
    public int getIdTemperatura() {
        return idTemperatura;
    }
    public void setIdTemperatura(int idTemperatura) {
        this.idTemperatura = idTemperatura;
    }
    public int getUrlCoolSelector() {
        return urlCoolSelector;
    }
    public void setUrlCoolSelector(int urlCoolSelector) {
        this.urlCoolSelector = urlCoolSelector;
    }
    public byte getActivo() {
        return activo;
    }
    public void setActivo(byte activo) {
        this.activo = activo;
    }
    public Sistema_SVC getSistema_svc() {
        return sistema_svc;
    }
    public void setSistema_svc(Sistema_SVC sistema_svc) {
        this.sistema_svc = sistema_svc;
    }
    public Capacidad getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(Capacidad capacidad) {
        this.capacidad = capacidad;
    }
    public Temperatura getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }
    public Deshielo getDeshielo() {
        return deshielo;
    }
    public void setDeshielo(Deshielo deshielo) {
        this.deshielo = deshielo;
    }
    public String getTituloPlantilla() {
        return tituloPlantilla;
    }
    public void setTituloPlantilla(String tituloPlantilla) {
        this.tituloPlantilla = tituloPlantilla;
    }
    public int getCapacidadDesc() {
        return capacidadDesc;
    }
    public void setCapacidadDesc(int capacidadDesc) {
        this.capacidadDesc = capacidadDesc;
    }
    public String getTemperaturaDesc() {
        return temperaturaDesc;
    }
    public void setTemperaturaDesc(String temperaturaDesc) {
        this.temperaturaDesc = temperaturaDesc;
    }
    public String getDeshielo1() {
        return deshielo1;
    }
    public void setDeshielo1(String deshielo1) {
        this.deshielo1 = deshielo1;
    }
    public String getDeshielo2() {
        return deshielo2;
    }
    public void setDeshielo2(String deshielo2) {
        this.deshielo2 = deshielo2;
    }
    public String getDeshielo3() {
        return deshielo3;
    }
    public void setDeshielo3(String deshielo3) {
        this.deshielo3 = deshielo3;
    }
    public void Insertar(int idPlantilla) throws Exception{
        try
        {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdSistema_SVC());
            params.put("2", getIdCapacidad());
            params.put("3", getIdDeshielo());
            params.put("4", getIdTemperatura());
            params.put("5", idPlantilla);

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Insertar", params);
            if(dt.Rows.size() > 0 ) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
                setIdPlantillaSeleccion(idPlantilla);
                //setIdPlantillaSeleccion( Integer.parseInt(dt.Rows.get(0).get("IdPlantillaSeleccion")));
            }
        }
        catch (Exception e) {
            throw new Exception("Error no se longro insertar Seleccion " + e.getMessage());
        }
    }
    public  boolean Actualizar() throws Exception {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());
            params.put("2", getIdSistema_SVC());
            params.put("3", getIdCapacidad());
            params.put("4", getIdDeshielo());
            params.put("5", getIdTemperatura());
            params.put("6", getUrlCoolSelector());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Actualizar", params);
            return true;

        }
        catch (Exception e) {
            throw new Exception("Error no se logro actualizar" + e.getMessage());
        }
    }
    public  boolean Eliminar() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Eliminar", params);
            return true;
        } catch (Exception e) {
            throw new Exception("Error no se logro eliminar" + e.getMessage());
        }
    }

    public ArrayList<Seleccion> Listar() throws Exception {
        try {
            ArrayList<Seleccion> result = new ArrayList<>();
            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_Listar");
            if (dt.Rows.size() > 0) {
                for ( Map<String, String> row: dt.Rows ) {
                    result.add(loadSeleccion(row));
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error no se logro listar" + e.getMessage());
        }
    }

    private  Seleccion loadSeleccion(Map<String, String> row) {

        Seleccion s = new Seleccion();
        s.setId(Integer.parseInt(row.get("Id")));
        s.setIdCapacidad(Integer.parseInt(row.get("IdCapacidad")));
        s.setIdDeshielo(Integer.parseInt(row.get("IdDeshielo")));
        s.setIdSistema_SVC(Integer.parseInt(row.get("IdSistema_SVC")));
        s.setIdTemperatura(Integer.parseInt(row.get("IdTemperatura")));
        // Cargamos la capacidad por id
        s.setCapacidad(new Capacidad().CargarPorId(s.getIdCapacidad()));
        s.setCapacidadDesc(s.getCapacidad().getValor());
        // Cargamos la temperatura por id
        s.setTemperatura(new Temperatura().CargarPorId(s.getIdTemperatura()));
        s.setTemperaturaDesc(s.getTemperatura().getValor());
        // Cargamos los deshielos de la seleccion
        loadDeshielos(s.getIdDeshielo(), s);

        if (row.get("DescripcionPlantilla") != null) {
            s.setTituloPlantilla(row.get("DescripcionPlantilla"));
        }

        return s;
    }

    private void loadDeshielos(int idDeshielo, Seleccion s) {
        Deshielo deshieloSeleccion = new Deshielo().CargarPorId(idDeshielo);

        if (deshieloSeleccion.getIdPadre() > 0) {
            Deshielo deshielo2 = deshieloSeleccion.CargarPorId();
            //s.setDeshielo2(deshielo2.getDescripcionDeshielo());
            if (deshielo2.getIdPadre() > 0) {
                Deshielo deshielo3 = deshielo2.CargarPorId();
                s.setDeshielo3(deshieloSeleccion.getDescripcionDeshielo());
                s.setDeshielo2(deshielo2.getDescripcionDeshielo());
                s.setDeshielo1(deshielo3.getDescripcionDeshielo());
            }
            else {
                s.setDeshielo3("-");
                s.setDeshielo2(deshieloSeleccion.getDescripcionDeshielo());
                s.setDeshielo1(deshielo2.getDescripcionDeshielo());
            }
        }
        else {
            s.setDeshielo1(deshieloSeleccion.getDescripcionDeshielo());
            s.setDeshielo2("-");
            s.setDeshielo3("-");
        }
    }

    public List<PlantillaSeleccion> ImportarSelecciones (MultipartFile file) throws  RuntimeException {
        try
        {
             return ExcelHelper.excelToSelecciones(file.getInputStream());
        }
        catch (Exception e)
        {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void CargarPorParametros() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getIdSistema_SVC());
            params.put("2", getIdCapacidad());
            params.put("3", getIdTemperatura());
            params.put("4", getIdDeshielo());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_CargarPorParametros", params);
            if (dt.Rows.size() > 0) {
                setId(Integer.parseInt(dt.Rows.get(0).get("Id")));
                setIdPlantillaSeleccion(Integer.parseInt(dt.Rows.get(0).get("IdPlantillaSeleccion")));
            }
        }
        catch (Exception e)
        {
            throw new Exception("Error al buscar la seleccion en: CargarPorParametros " + e.getMessage());
        }
    }

    public void CargarPorId() throws Exception {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("1", getId());

            DataTable dt = new Persistencia().Query("CALL SP_Seleccion_CargarPorId", params);
            if (dt.Rows.size() > 0) {
               Load(dt.Rows.get(0));
            }
        }
        catch (Exception e)
        {
            throw new Exception("Error al buscar la seleccion en: CargarPorParametros " + e.getMessage());
        }
    }

    public Seleccion crearSeleccion(Iterator<Cell> cellsInRow) throws IOException {
        Seleccion seleccion = new Seleccion();
        Sistema_SVC sistema_svc = new Sistema_SVC();
        Capacidad capacidad = new Capacidad();
        Temperatura temperatura = new Temperatura();
        Deshielo deshielo = new Deshielo();

        int cellIdx = 1;
        try {
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();

                if (cellIdx > 6 ) {
                    break;
                }

                switch (cellIdx) {
                    case 1:
                        if (!currentCell.getStringCellValue().equals("") && !currentCell.getStringCellValue().equals("    ")) {
                            sistema_svc.setDescripcionSistema(getNombreRealSistema(currentCell));
                            sistema_svc = sistema_svc.CargarPorDescripcion();
                            //obtenemos el id del sistema
                            if (sistema_svc.getId() > 0 ) {
                                seleccion.setIdSistema_SVC(sistema_svc.getId());
                                seleccion.setSistema_svc(sistema_svc);
                            }
                        }
                        else {
                            cellIdx = 7;
                           break;
                        }
                        break;
                    case 2:
                        //obtener el id de la capacidad
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            int valor =  (int) Double.parseDouble(String.valueOf(currentCell.getNumericCellValue()));
                            capacidad.setValor(valor);
                        }
                        else {
                            if (currentCell.getStringCellValue().isEmpty()) {
                                break;
                            }
                            capacidad.setValor(Integer.parseInt(currentCell.getStringCellValue()));
                        }

                        capacidad = capacidad.CargarPorValor();
                        if (capacidad.getId() > 0) {
                            seleccion.setIdCapacidad(capacidad.getId());
                            seleccion.setCapacidad(capacidad);
                        }
                        break;
                    case 3:
                        //obtener el id de la temperaura
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            int valor =  (int) Double.parseDouble(String.valueOf(currentCell.getNumericCellValue()));
                            temperatura.setValor(String.valueOf(valor));
                        }
                        else {
                            if (currentCell.getStringCellValue().isEmpty()) {
                                break;
                            }
                            temperatura.setValor(currentCell.getStringCellValue());
                        }
                        temperatura = temperatura.CargarPorValor();
                        if (temperatura.getId() > 0 ) {
                            seleccion.setIdTemperatura(temperatura.getId());
                            seleccion.setTemperatura(temperatura);
                        }
                        break;
                    case 4:
                        //obtener el deshielo1
                        if(currentCell.getStringCellValue().isEmpty()) {
                            break;
                        }

                        deshielo.setDescripcionDeshielo(currentCell.getStringCellValue());
                        deshielo = deshielo.CargarPorDescripcion();
                        if (deshielo.getId() > 0 ) {
                            seleccion.setIdDeshielo(deshielo.getId());
                            seleccion.setDeshielo(deshielo);
                        }
                        break;
                    case 5:
                    case 6:
                        if (currentCell.getStringCellValue().equals("-")) {
                           break;
                        }
                        else {
                            //obtener deshielo por idPadre
                            if (currentCell.getStringCellValue().isEmpty()) {
                                break;
                            }
                            deshielo.setIdPadre(deshielo.getId());
                            deshielo.setDescripcionDeshielo(currentCell.getStringCellValue());
                            deshielo = deshielo.CargarPorIdPadreDescripcion();
                            seleccion.setIdDeshielo(deshielo.getId());
                            seleccion.setDeshielo(deshielo);
                        }
                        break;
                    default:
                        break;
                }
                cellIdx++;
            }
        }
        catch (Exception e) {
            throw new IOException("Error al leer una celda en crearSeleccion " + e.getMessage());
        }

        return seleccion;
    }

    private String getNombreRealSistema(Cell currentCell) {
        String value = currentCell.getStringCellValue();
        switch (value) {
            case "R3":
                value = "Recirculado (3:1)";
                break;
            case "R4":
                value = "Recirculado (4:1)";
                break;
            case "R1.2":
                value = "Recirculado (1.2:1)";
                break;
            case "R1.3":
                value = "Recirculado (1.3:1)";
                break;
        }
        return value;
    }

    private void Load(Map<String, String> row) {
        setId(Integer.parseInt(row.get("Id")));
        setIdSistema_SVC(Integer.parseInt(row.get("IdSistema_SVC")));
        setIdCapacidad(Integer.parseInt(row.get("IdCapacidad")));
        setIdTemperatura(Integer.parseInt(row.get("IdTemperatura")));
        setIdDeshielo(Integer.parseInt(row.get("IdDeshielo")));
    }

}
