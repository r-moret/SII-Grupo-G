package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.EncuestaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.EncuestaInterface;

public class TestEncuestaEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	
	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaEJB";
	
	private EncuestaInterface encuestaEJB;
	
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
		
		encuestaEJB = (EncuestaInterface) ctx.lookup(ENCUESTA_EJB);
		BaseDatos.initBaseDatos();
	}
	
	@After
	public void tearDown() {
		if(em.isOpen()) {
			em.close();
		}
		emf.close();
	}
	
	private Encuesta crearEncuesta(boolean incompatibilidadHoraria) throws SecretariaException {
		Encuesta enc;
		if(incompatibilidadHoraria) {
			Expediente ex = new Expediente();
			ex.setNumExpediente(8);
			enc = encuestaEJB.obtenerEncuesta(Timestamp.valueOf("2020-08-21 10:00:00"), ex);
		}
		else {
			Expediente ex = new Expediente();
			ex.setNumExpediente(1);
			enc = encuestaEJB.obtenerEncuesta(Timestamp.valueOf("2020-09-21 16:00:00"), ex);
		}
		
		return enc;
	}
	
	@Test
	@Ignore
	public void testObtenerEncuesta() {
		try {
			Expediente ex = em.find(Expediente.class, 1);
			encuestaEJB.obtenerEncuesta(null, ex);
			fail("No lanza una excepci�n (SecretariaException) avisando de que la fecha de env�o era nula");
		}
		catch(SecretariaException e) {
			try {
				Timestamp fechaEnvio = Timestamp.valueOf("2020-09-21 16:00:00");
				encuestaEJB.obtenerEncuesta(fechaEnvio, null);
				fail("No lanza una excepci�n (SecretariaException) avisando de que el expediento era nulo");
			}
			catch(SecretariaException a) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception a) {
				fail("Lanza una excepci�n distinta a SecretariaException");
			}
		}
		catch(Exception e) {
			fail("Lanza una excepci�n distinta a SecretariaException");
		}
		
		try {
			Expediente ex = new Expediente();
			ex.setNumExpediente(3);
			Timestamp fechaEnvio = Timestamp.valueOf("2020-06-21 12:00:00");
			encuestaEJB.obtenerEncuesta(fechaEnvio, ex);
			fail("No lanza una excepci�n avisando de que la encuesta no existe en la base de datos");
		}
		catch(EncuestaInexistente e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepcion incorrecta, distinta a EncuestaInexistente");
		}
	}
	
	@Requisitos({"RF2"})
	@Test
	@Ignore
	public void testRegistroEncuestaCorrecto() {
		try {
			encuestaEJB.registrarEncuesta(null);
			fail("No lanza una excepci�n (SecretariaException) avisando de que la encuesta era nula");
		} 
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepci�n distinta a SecretariaException");
		}
	
		try {
			// Creo una encuesta cualquiera
			Encuesta enc = new Encuesta();
			enc.setFechaEnvio(Timestamp.valueOf("2020-01-26 11:20:04"));
			
			Expediente ex = em.find(Expediente.class, 1);
			enc.setExpediente(ex);
	
			encuestaEJB.registrarEncuesta(enc);
			
			// El mismo alumno crea una nueva encuesta (distinta fecha, mismo expediente)
			Encuesta newEnc = new Encuesta();
			newEnc.setExpediente(enc.getExpediente());
			newEnc.setFechaEnvio(Timestamp.valueOf("2020-09-26 11:20:04"));
			  
			encuestaEJB.registrarEncuesta(newEnc);

			// Aseguramos que hemos sobreescrito la base de datos con los nuevos datos
			//que est�n en newEnc
			assertEquals("No se sobreescriben los datos de la nueva encuesta realizada", newEnc.getFechaEnvio(), encuestaEJB.obtenerEncuesta(newEnc.getFechaEnvio(), newEnc.getExpediente()).getFechaEnvio()); 
			assertThrows("Siguen existiendo en la base de datos los datos que se deber�an haber actualizado", EncuestaInexistente.class, () -> encuestaEJB.obtenerEncuesta(enc.getFechaEnvio(), enc.getExpediente()));
		} 
		catch (Exception e) {
			fail("Lanza una excepci�n cuando se est� registrando una encuesta correcta");
		}	
	}
	
	@Requisitos({"RF6"})
	@Test
	@Ignore
	public void testDetectarIncompatibilidadHoraria() {
		try {
			encuestaEJB.incompatibilidadHoraria(null);
			fail("No lanza una excepci�n (SecretariaException) avisando de que la encuesta era nula");
		}
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepcion distinta a SecretariaException");
		}
		
		try {
			Encuesta encCompatible = crearEncuesta(false);
			Encuesta encIncompatible = crearEncuesta(true);
			assertFalse("Detecta incompatibilidad en una lista sin incompatibilidad horaria", encuestaEJB.incompatibilidadHoraria(encCompatible));
			assertTrue("No detecta incompatibilidad en una lista con incompatibilidad horaria", encuestaEJB.incompatibilidadHoraria(encIncompatible));
		} catch (Exception e) {
			fail("Lanza una excepci�n al comprobar una encuesta correcta");
		}
	}
}
