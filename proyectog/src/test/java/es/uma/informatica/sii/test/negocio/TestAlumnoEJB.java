package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.negocio.AlumnoInterface;

public class TestAlumnoEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	private static final String ALUMNO_EJB = "java:global/classes/AlumnoEJB";
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private AlumnoInterface alumnoEJB;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIG_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
		
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		em.close();
		emf.close();
		
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

	@Before
	public void setUp() throws Exception {
		alumnoEJB = (AlumnoInterface) ctx.lookup(ALUMNO_EJB);
		BaseDatos.initBaseDatos();
	}

	@Requisitos({"RF9"})
	@Test
	@Ignore
	public void testActualizarAlumno() {
		
		try {
			alumnoEJB.actualizarAlumno(null);
			fail("Permite actualizar un alumno nulo");
		}
		catch(SecretariaException exc1) {
			try {
				Alumno al = new Alumno();
				al.setId(9);
				al.setEmailInstitucional("email@email.es");
				al.setNombreCompleto("Nombre Alumno");
				al.setDni("8888888S");
				alumnoEJB.actualizarAlumno(al);
				fail("Permite actualizar un alumno inexistente en la base de datos");
			}
			catch(AlumnoInexistente exc2) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception exc2) {
				fail("Lanza la excepci�n incorrecta");
			}
		}
		catch(Exception exc1) {
			fail("Lanza la excepci�n incorrecta");
		}
		
		Alumno al = em.find(Alumno.class, 1);
		
		al.setEmailInstitucional("otro@gmail.com");
		
		try {
			alumnoEJB.actualizarAlumno(al);
		} catch (Exception e) {
			fail("Lanza una excepci�n en la actualizaci�n de un alumno correcto");
		}
		
		assertEquals("El campo actualizado no se ha guardado en la base de datos", al.getEmailInstitucional(), em.find(Alumno.class, 1).getEmailInstitucional());	
	}
}