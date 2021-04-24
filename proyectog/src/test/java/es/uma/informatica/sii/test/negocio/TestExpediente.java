package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.ExpedienteInterface;

public class TestExpediente {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	private static final String ALUMNO_EJB = "java:global/classes/ExpedienteEJB";
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private ExpedienteInterface expedienteEJB;
	
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
		
		expedienteEJB = (ExpedienteInterface) ctx.lookup(ALUMNO_EJB);
		BaseDatos.initBaseDatos();
	}
	
	@After
	public void tearDown() {
		if(em.isOpen()) {
			em.close();
		}
		emf.close();
	}

	@Requisitos({"RF9"})
	@Test
	public void testActualizarExpediente() {
		
		// Caso 1 - paso un expediente nulo
		try {
			expedienteEJB.actualizarExpediente(null);
			fail("Permite actualizar un expediente nulo");
		}
		catch(SecretariaException exc1) {
			// Excepcion correcta
		}
		catch(Exception exc1) {
			fail("Lanza la excepciï¿½n incorrecta 2");
		}
		
		// Caso 2 - paso un expediente no existente en la bbdd
		try {
			Expediente exp = new Expediente();
			exp.setNumExpediente(4);
			exp.setTitulacion(em.find(Titulacion.class, 1));
			
			Alumno al1 = new Alumno();
			al1.setDni("12C");
			al1.setNombreCompleto("Antonio Moret Sánchez");
			al1.setEmailInstitucional("anton@gmail.es");
			
			exp.setAlumno(al1);
			List<Matricula> matris = new ArrayList<>();
			exp.setMatriculas(matris);
			exp.setActivo(false);
			
			expedienteEJB.actualizarExpediente(exp);
			fail("Permite actualizar un expediente inexistente en la base de datos");
		}
		catch(ExpedienteInexistente exc2) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception exc2) {
			fail("Lanza la excepcion incorrecta 1");
		}
		
		// Modifico un expediente existente en la bbdd
		Expediente exp = em.find(Expediente.class, 1);
		exp.setActivo(true);
		
		try {
			expedienteEJB.actualizarExpediente(exp);
		} catch (Exception e) {
			fail("Lanza una excepciï¿½n en la actualizaciï¿½n de un alumno correcto");
		}
		
		assertEquals("El campo actualizado no se ha guardado en la base de datos", exp.getActivo(), em.find(Expediente.class, 1).getActivo());	
	}

}
