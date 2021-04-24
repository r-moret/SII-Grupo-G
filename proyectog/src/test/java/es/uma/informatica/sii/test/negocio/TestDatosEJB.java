package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.DatosInterface;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDatosEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	
	private static final String DATOS_EJB = "java:global/classes/DatosEJB";
	
	private DatosInterface datosEJB;
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIG_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		
		datosEJB = (DatosInterface) ctx.lookup(DATOS_EJB);
		BaseDatos.initBaseDatos();
	}
	
	@After
	public void tearDown() {
		if(em.isOpen()) {
			em.close();
		}
		emf.close();
	}

	@Requisitos({"RF8"})
	@Test
	public void testAlumnosLeidos() {
		String ficheroAlumnos = "target/test-classes/files/alumnos.xlsx";
		
		FileInputStream inputStream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		int numFilasAlumnos;
		try {
			inputStream = new FileInputStream(ficheroAlumnos);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
			numFilasAlumnos = sheet.getPhysicalNumberOfRows() - 5;
		
			List<DatosAlumnado> listaAlumnos = datosEJB.importarDatosAlumnado(ficheroAlumnos);
			
			assertEquals("El numero de alumnos leidos es incorrecto", listaAlumnos.size(), numFilasAlumnos);
		} 
		catch(FileNotFoundException e) {
			fail("Lanza una excepción abriendo el fichero");
		}
		catch (IOException e) {
			fail("Lanza una excepción a la hora de trabajar con el fichero (Workbook)");
		} 
		catch (SecretariaException e) {
			fail("Lanza una excepción importando los datos del alumnado");
		}
	}
	
	@Requisitos({"RF8"})
	@Test
	public void testGruposLeidos() {
		String ficheroGrupos = "target/test-classes/files/grupos.xlsx";
		
		FileInputStream inputStream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		int numFilasGrupos;
		try {
			inputStream = new FileInputStream(ficheroGrupos);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
			numFilasGrupos = sheet.getPhysicalNumberOfRows() - 1;
		
			List<Grupo> listaGrupos = datosEJB.importarDatosGrupos(ficheroGrupos);
			
			assertEquals("El numero de alumnos leidos es incorrecto", listaGrupos.size(), numFilasGrupos);
		} 
		catch(FileNotFoundException e) {
			fail("Lanza una excepción abriendo el fichero");
		}
		catch (IOException e) {
			fail("Lanza una excepción a la hora de trabajar con el fichero (Workbook)");
		} 
		catch (SecretariaException e) {
			fail("Lanza una excepción importando los datos de los grupos");
		}
	}
	
	@Requisitos({"RF8"})
	@Test
	public void testExpedientesLeidos() {
		String ficheroExpedientes = "target/test-classes/files/expedientes.xlsx";
		
		FileInputStream inputStream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		int numFilasExpedientes;
		try {
			inputStream = new FileInputStream(ficheroExpedientes);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
			numFilasExpedientes = sheet.getPhysicalNumberOfRows() - 1;
		
			List<Expediente> listaExpedientes = datosEJB.importarDatosExpediente(ficheroExpedientes);
			
			assertEquals("El numero de alumnos leidos es incorrecto", listaExpedientes.size(), numFilasExpedientes);
		} 
		catch(FileNotFoundException e) {
			fail("Lanza una excepción abriendo el fichero");
		}
		catch (IOException e) {
			fail("Lanza una excepción a la hora de trabajar con el fichero (Workbook)");
		} 
		catch (SecretariaException e) {
			fail("Lanza una excepción importando los datos de los expedientes");
		}
	}
	
	@Requisitos({"RF8"})
	@Test
	public void testAsignaturasLeidas() {
		String ficheroAsignaturas = "target/test-classes/files/asignaturas.xlsx";
		
		FileInputStream inputStream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		int numFilasAsignaturas;
		try {
			inputStream = new FileInputStream(ficheroAsignaturas);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
			numFilasAsignaturas = sheet.getPhysicalNumberOfRows() - 1;
		
			List<Asignatura> listaAsignaturas = datosEJB.importarDatosAsignaturas(ficheroAsignaturas);
			
			assertEquals(listaAsignaturas.size(), numFilasAsignaturas);
		}
		catch(FileNotFoundException e) {
			fail("Lanza una excepción abriendo el fichero");
		}
		catch (IOException e) {
			fail("Lanza una excepción a la hora de trabajar con el fichero (Workbook)");
		} 
		catch (SecretariaException e) {
			fail("Lanza una excepción importando las asignaturas");
		}
	}

}
