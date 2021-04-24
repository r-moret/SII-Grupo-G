package es.uma.informatica.sii.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
import es.uma.informatica.sii.exceptions.DatosInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
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
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
            //Primera hoja
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            //Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            XSSFRow xssfRow;                    
            //Obtengo el número de filas ocupadas en la hoja
            int filas = xssfSheet.getLastRowNum();        
            //En el documento los datos empiezan en fila 5       
            for (int f = 5; f <= filas; f++) {
            	 
            	xssfRow = xssfSheet.getRow(f);
                DatosAlumnado da = new DatosAlumnado();
                
                if (xssfRow == null){ //¿Se puede omitir si sabemos que no habrá filas vacias?
                    break;
                    
                }else{    
                	for(int i = 0; i <= 24; i++) {
                		xssfRow.getCell(i).setCellType(CellType.STRING);
                	}
                	
                 	da.setDni(xssfRow.getCell(0).getStringCellValue());
                	da.setNombre(xssfRow.getCell(1).getStringCellValue());
                	da.setApellido1(xssfRow.getCell(2).getStringCellValue());
                	da.setApellido2(xssfRow.getCell(3).getStringCellValue());
                	
                	da.setNumExpediente(Integer.valueOf(xssfRow.getCell(4).getStringCellValue()));
                	da.setNumArchivo(Integer.valueOf(xssfRow.getCell(5).getStringCellValue()));
                	
                	da.setEmailInstitucional(xssfRow.getCell(6).getStringCellValue());
                	da.setEmailPersonal(xssfRow.getCell(7).getStringCellValue());
                	da.setTelefono(xssfRow.getCell(8).getStringCellValue());
                	da.setMovil(xssfRow.getCell(9).getStringCellValue());
                	da.setDireccionNotificacion(xssfRow.getCell(10).getStringCellValue());
                	da.setLocalidadNotificacion(xssfRow.getCell(11).getStringCellValue());
                	da.setProvinciaNotificacion(xssfRow.getCell(12).getStringCellValue());
          
                	da.setCpNotificacion(Integer.valueOf(xssfRow.getCell(13).getStringCellValue()));  
                    
                	da.setFechaMatricula(Timestamp.valueOf(formatearFecha(xssfRow.getCell(14).getStringCellValue())));
                	
                	da.setTurnoPreferente(xssfRow.getCell(15).getStringCellValue());
                	da.setGruposAsignados(xssfRow.getCell(16).getStringCellValue());
                	
                	da.setNotaMedia(Double.valueOf(xssfRow.getCell(17).getStringCellValue()));
                	
                	da.setCreditosSuperados(Double.valueOf(xssfRow.getCell(18).getStringCellValue()));
                	da.setCreditosFB(Double.valueOf(xssfRow.getCell(19).getStringCellValue()));
                	da.setCreditosOB(Double.valueOf(xssfRow.getCell(20).getStringCellValue()));
                	da.setCreditosOP(Double.valueOf(xssfRow.getCell(21).getStringCellValue()));
                	da.setCreditosCF(Double.valueOf(xssfRow.getCell(22).getStringCellValue()));
                	da.setCreditosPE(Double.valueOf(xssfRow.getCell(23).getStringCellValue()));
                	da.setCreditosTF(Double.valueOf(xssfRow.getCell(24).getStringCellValue()));   
                	
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
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
            
			for(int i=0;i<xssfWorkbook.getNumberOfSheets();i++){
				
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
            
            XSSFRow xssfRow;                    
      
            int filas = xssfSheet.getLastRowNum();        
  
            for (int f = 2; f < filas; f++) {
            	 
                xssfRow = xssfSheet.getRow(f);
                Asignatura a = new Asignatura();
                if (xssfRow == null){
                    break;
                    
                }else{    
                	int c = 0; 
                	Titulacion t = em.find(Titulacion.class, (int) xssfRow.getCell(c).getNumericCellValue()); if(t==null){throw new TitulacionInexistente();}
					a.setTitulacion(t);
                 	a.setOfertada(tratarBooleanos(xssfRow.getCell(c++).getStringCellValue()));
                	a.setCodigo((int) xssfRow.getCell(c++).getNumericCellValue());
                	a.setReferencia((int) xssfRow.getCell(c++).getNumericCellValue());
                	a.setNombre(xssfRow.getCell(c++).getStringCellValue());
                	a.setCurso((int) xssfRow.getCell(c++).getNumericCellValue());
                	a.setCreditos((int) xssfRow.getCell(c++).getNumericCellValue());
                	a.setDuracion((int) xssfRow.getCell(c+3).getStringCellValue().charAt(0));
					a.setPlazas(tratarPlazas(xssfRow.getCell(c++).getStringCellValue()));	//Asumimos formato establecido en el archivo (mirar tratarPlazas).
					if(xssfRow.getCell(c++) != null) {
						a.setIdioma(xssfRow.getCell(c).getStringCellValue());
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
	
	@Override
	public List<Expediente> importarDatosExpediente(String fichero) throws SecretariaException{
		File archivo = cargarArchivo(fichero);
		
		return importarDatosExpediente(archivo);
	}
	
	private List<Expediente> importarDatosExpediente(File excel)  throws SecretariaException{
		List<Expediente> listaExpedientes = new ArrayList<Expediente>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excel);
            
            @SuppressWarnings("resource")
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
            
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            
            XSSFRow xssfRow; 
            
            int filas = xssfSheet.getLastRowNum();       
            
            for (int f = 2; f < filas; f++) {
            	 
                xssfRow = xssfSheet.getRow(f);
                Expediente e = new Expediente();
                if (xssfRow == null){
                    break;
                    
                }else{    
                	int c = 0; 
                	
                 	e.setNumExpediente((int) xssfRow.getCell(c).getNumericCellValue());
                	e.setActivo(tratarBooleanos(xssfRow.getCell(c++).getStringCellValue()));
                	e.setNotaMediaProvisional((float) xssfRow.getCell(c++).getNumericCellValue());
         
                	listaExpedientes.add(e);
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
		return listaExpedientes;
	}
	
	@Override
	public void registrarDatosExpediente(List<Expediente> listaExpedientes) throws SecretariaException{
		
		if(listaExpedientes == null){
			throw new SecretariaException();
		}

		for(Expediente e: listaExpedientes){
			Expediente de = em.find(Expediente.class, e.getNumExpediente());
			if(de == null){
				throw new ExpedienteInexistente();
			}
			
			em.persist(e);
		}
	}
	
	private String formatearFecha(String date) {
		String anyo = date.substring(date.lastIndexOf("/")+1, date.indexOf(" "));
		String mes = date.substring(date.indexOf("/")+1, date.lastIndexOf("/"));
		String dia = date.substring(0, date.indexOf("/"));
		String hora = date.substring(date.lastIndexOf(" ")+1, date.indexOf(":"));
		String minuto = date.substring(date.lastIndexOf(":")+1, date.length());
		
		return anyo + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":00";
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

	private boolean tratarBooleanos(String str){
		return str.equals("Sí");
	}
}
