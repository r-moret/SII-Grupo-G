package es.uma.informatica.sii.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
import es.uma.informatica.sii.exceptions.DatosInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.TitulacionInexistente;

@Stateless
public class DatosEJB implements DatosInterface {
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	private File cargarArchivo(String fichero) throws SecretariaException {
		int idxExtension = fichero.lastIndexOf('.');
		String formato = fichero.substring(idxExtension, fichero.length());
		
		String nuevoFichero = fichero;
		if(formato.equals(".csv")) {
			nuevoFichero = fichero.substring(0, idxExtension) + ".xlsx";
			
			LoadOptions opciones = new LoadOptions(FileFormatType.CSV);
			Workbook workbook;
			try {
				workbook = new Workbook(fichero, opciones);
				workbook.save(nuevoFichero, SaveFormat.XLSX);
			} catch (Exception e) {
				throw new SecretariaException("Error al convertir el fichero de .CSV a .XLSX");
			}
		}
		else if(!formato.equals(".xls") && !formato.equals(".xlsx")) {
			throw new SecretariaException("Formato de fichero no reconocido");
		}
		
		return new File(nuevoFichero);
	}
	
	@Override
	public List<DatosAlumnado> importarDatosAlumnado(String fichero) throws SecretariaException {
		File archivo = cargarArchivo(fichero);
		
		return importarDatosAlumnado(archivo);
	}
	
	private List<DatosAlumnado> importarDatosAlumnado(File excel) {
		List<DatosAlumnado> listaDatos = new ArrayList<DatosAlumnado>();
        InputStream excelStream = null;
        try {
        	excelStream = new FileInputStream(excel);
            //Lista de hojas excel.
            @SuppressWarnings("resource")
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            //Primera hoja
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            //Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;                    
            //Obtengo el número de filas ocupadas en la hoja
            int filas = hssfSheet.getLastRowNum();        
            //En el documento los datos empiezan en fila 5       
            for (int f = 5; f < filas; f++) {
            	 
                hssfRow = hssfSheet.getRow(f);
                DatosAlumnado da = new DatosAlumnado();
                if (hssfRow == null){ //¿Se puede omitir si sabemos que no habrá filas vacias?
                    break;
                    
                }else{    
                	int c = 0; 
                	
                 	da.setDni(hssfRow.getCell(c).getStringCellValue());
                	da.setNombre(hssfRow.getCell(c++).getStringCellValue());
                	da.setApellido1(hssfRow.getCell(c++).getStringCellValue());
                	da.setApellido2(hssfRow.getCell(c++).getStringCellValue());
                	da.setNumExpediente((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setNumArchivo((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setEmailInstitucional(hssfRow.getCell(c++).getStringCellValue());
                	da.setEmailPersonal(hssfRow.getCell(c++).getStringCellValue());
                	da.setTelefono((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setMovil((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setDireccionNotificacion(hssfRow.getCell(c++).getStringCellValue());
                	da.setLocalidadNotificacion(hssfRow.getCell(c++).getStringCellValue());
                	da.setProvinciaNotificacion(hssfRow.getCell(c++).getStringCellValue());
                	da.setCpNotificacion((int) hssfRow.getCell(c++).getNumericCellValue());  
                    da.setFechaMatricula(Timestamp.valueOf(formatearFecha(hssfRow.getCell(c++).getDateCellValue())));
                	da.setTurnoPreferente(hssfRow.getCell(c++).getStringCellValue());
                	da.setGruposAsignados(hssfRow.getCell(c++).getStringCellValue());
                	da.setNotaMedia((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosSuperados((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosFB((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosOB((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosCF((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosPE((int) hssfRow.getCell(c++).getNumericCellValue());
                	da.setCreditosTF((int) hssfRow.getCell(c++).getNumericCellValue());   
                	
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
	public void registrarDatosAlumnado(List<DatosAlumnado> datos) throws SecretariaException{
		
		if(datos == null){
			throw new SecretariaException();
		}

		for(DatosAlumnado d: datos){
			DatosAlumnado da = em.find(DatosAlumnado.class, d.getDni());
			if(da == null){
				throw new DatosInexistente();
			}
			
			em.persist(d);
		}
	}

	@Override
	public List<Asignatura> importarDatosAsignaturas(String fichero) throws SecretariaException {
		File archivo = cargarArchivo(fichero);
		
		return importarDatosAsignaturas(archivo);
	}
	
	private List<Asignatura> importarDatosAsignaturas(File excel) throws TitulacionInexistente {
		List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excel);
    
            @SuppressWarnings("resource")
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            
			for(int i=0;i<hssfWorkbook.getNumberOfSheets();i++){
				
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            
            HSSFRow hssfRow;                    
      
            int filas = hssfSheet.getLastRowNum();        
  
            for (int f = 2; f < filas; f++) {
            	 
                hssfRow = hssfSheet.getRow(f);
                Asignatura a = new Asignatura();
                if (hssfRow == null){
                    break;
                    
                }else{    
                	int c = 0; 
                	Titulacion t = em.find(Titulacion.class, (int) hssfRow.getCell(c).getNumericCellValue()); if(t==null){throw new TitulacionInexistente();}
					a.setTitulacion(t);
                 	a.setOfertada(hssfRow.getCell(c++).getBooleanCellValue());
                	a.setCodigo((int) hssfRow.getCell(c++).getNumericCellValue());
                	a.setReferencia((int) hssfRow.getCell(c++).getNumericCellValue());
                	a.setNombre(hssfRow.getCell(c++).getStringCellValue());
                	a.setCurso((int) hssfRow.getCell(c++).getNumericCellValue());
                	a.setCreditos((int) hssfRow.getCell(c++).getNumericCellValue());
                	a.setDuracion((int) hssfRow.getCell(c+3).getStringCellValue().charAt(0));
					a.setPlazas(tratarPlazas(hssfRow.getCell(c++).getStringCellValue()));	//Asumimos formato establecido en el archivo (mirar tratarPlazas).
					if(hssfRow.getCell(c++) != null) {
						a.setIdioma(hssfRow.getCell(c).getStringCellValue());
					}   
                	listaAsignaturas.add(a);
                }
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
		return listaAsignaturas;
	}

	@Override
	public void registrarDatosAsignaturas(List<Asignatura> asignaturas) throws SecretariaException{
		
		if(asignaturas == null){
			throw new SecretariaException();
		}

		for(Asignatura a: asignaturas){
			Asignatura asig = em.find(Asignatura.class, a.getReferencia());
			if(asig == null){
				throw new AsignaturaInexistente();
			}
			
			em.persist(a);
		}
	}
	
	private String formatearFecha(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm");
		String fechaFormateada = formatter.format(d);
		return fechaFormateada + ":00";
	}
	
	private Integer tratarPlazas(String str) {
		Integer res;
		if(str.equals("-")) {
			res = 200;			
		}else if(str.equals("Sin lím.")){
			res=200;
		}else{
			res = Integer.valueOf(str);
		}
	
		return res;
	}

}
