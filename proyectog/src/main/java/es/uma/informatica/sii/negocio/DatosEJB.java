package es.uma.informatica.sii.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import es.uma.informatica.sii.entidades.DatosAlumnado;

public class DatosEJB implements DatosEJBInterface {
	

	@Override
	public List<DatosAlumnado> importarDatosAlumnado(File excel) {
		List<DatosAlumnado> listaDatos = new ArrayList<DatosAlumnado>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excel);
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;                        
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;            
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue;  
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int r = 0; r < rows; r++) {
                hssfRow = hssfSheet.getRow(r);
                DatosAlumnado da = new DatosAlumnado();
                if (hssfRow == null){
                    break;
                }else{
                    for (int c = 0; c < (cols = hssfRow.getFirstCellNum()); c++) {
                         	da.setDni(hssfRow.getCell(c).getStringCellValue());
                        	da.setNombre(hssfRow.getCell(c).getStringCellValue());
                        	da.setApellido1(hssfRow.getCell(c).getStringCellValue());
                        	da.setApellido2(hssfRow.getCell(c).getStringCellValue());
                        	da.setNumExpediente((int) hssfRow.getCell(c).getNumericCellValue()); // ojo
                        	da.setEmailInstitucional(hssfRow.getCell(c).getStringCellValue());
                        	da.setEmailPersonal(hssfRow.getCell(c).getStringCellValue());
                        	da.setTelefono((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setMovil((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setDireccionNotificacion(hssfRow.getCell(c).getStringCellValue());
                        	da.setLocalidadNotificacion(hssfRow.getCell(c).getStringCellValue());
                        	da.setProvinciaNotificacion(hssfRow.getCell(c).getStringCellValue());
                        	da.setCpNotificacion((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setFechaMatricula((Timestamp) hssfRow.getCell(c).getDateCellValue()); // ojo
                        	da.setTurnoPreferente(hssfRow.getCell(c).getStringCellValue());
                        	da.setGruposAsignados(hssfRow.getCell(c).getStringCellValue());
                        	da.setNotaMedia((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosSuperados((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosFB((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosOB((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosCF((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosPE((int) hssfRow.getCell(c).getNumericCellValue());
                        	da.setCreditosTF((int) hssfRow.getCell(c).getNumericCellValue());              
                    }
                    listaDatos.add(da);
                }
            }            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No se encontró el fichero: " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error al procesar el fichero: " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error al procesar el fichero después de cerrarlo: " + ex);
            }
        }
	

		return listaDatos;
	}

	@Override
	public void registrarDatos(DatosAlumnado datos) {
		// TODO Auto-generated method stub
		
	}


}
