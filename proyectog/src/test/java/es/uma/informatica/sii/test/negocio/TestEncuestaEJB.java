package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
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
		encuestaEJB = (EncuestaInterface) ctx.lookup(ENCUESTA_EJB);
		BaseDatos.initBaseDatos();
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
	public void testObtenerEncuesta() {
		try {
			Expediente ex = em.find(Expediente.class, 1);
			encuestaEJB.obtenerEncuesta(null, ex);
			fail("No lanza una excepción (SecretariaException) avisando de que la fecha de envío era nula");
		}
		catch(SecretariaException e) {
			try {
				Timestamp fechaEnvio = Timestamp.valueOf("2020-09-21 16:00:00");
				encuestaEJB.obtenerEncuesta(fechaEnvio, null);
				fail("No lanza una excepción (SecretariaException) avisando de que el expediento era nulo");
			}
			catch(SecretariaException a) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception a) {
				fail("Lanza una excepción distinta a SecretariaException");
			}
		}
		catch(Exception e) {
			fail("Lanza una excepción distinta a SecretariaException");
		}
		
		try {
			Expediente ex = new Expediente();
			ex.setNumExpediente(3);
			Timestamp fechaEnvio = Timestamp.valueOf("2020-06-21 12:00:00");
			encuestaEJB.obtenerEncuesta(fechaEnvio, ex);
			fail("No lanza una excepción avisando de que la encuesta no existe en la base de datos");
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
			fail("No lanza una excepciï¿½n (SecretariaException) avisando de que la encuesta era nula");
		} 
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepciï¿½n distinta a SecretariaException");
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
			//que estï¿½n en newEnc
			assertEquals("No se sobreescriben los datos de la nueva encuesta realizada", newEnc.getFechaEnvio(), encuestaEJB.obtenerEncuesta(newEnc.getFechaEnvio(), newEnc.getExpediente()).getFechaEnvio()); 
			assertThrows("Siguen existiendo en la base de datos los datos que se deberían haber actualizado", EncuestaInexistente.class, () -> encuestaEJB.obtenerEncuesta(enc.getFechaEnvio(), enc.getExpediente()));
		} 
		catch (Exception e) {
			fail("Lanza una excepciï¿½n cuando se estï¿½ registrando una encuesta correcta");
		}	
	}
	
	//  TODO: Determinar si es necesario comprobar si los grupos de dos asignaturas del mismo curso 
	//  deben ser iguales, o no
	//
	//	@Requisitos({"RF2"})
	//	@Test
	//	@Ignore
	//	public void testDistintoGrupoMismoCurso() {
	//		/*  Simulamos una encuesta con un conflicto en el cual dos asignaturas del mismo aï¿½o
	//		 *  tienen grupos distintos asignados
	//		 */ 
	//		Encuesta enc = new Encuesta();
	//		enc.setFechaEnvio(Timestamp.valueOf("2020-09-03 10:00:00"));
	//		enc.setExpediente(em.find(Expediente.class, 8));
	//		
	//		List<GruposPorAsignatura> gruposAsignatura = new ArrayList<>();
	//		GruposPorAsignatura gpa1 =  new GruposPorAsignatura();
	//		gpa1.setAsignatura(em.find(Asignatura.class, 1)); // Asignatura de primero
	//		gpa1.setCursoAcademico("20/21");
	//		gpa1.setGrupo(em.find(Grupo.class, "id1")); // Grupo A de primero
	//		
	//		GruposPorAsignatura gpa2 =  new GruposPorAsignatura();
	//		gpa2.setAsignatura(em.find(Asignatura.class, 2)); // Asignatura de primero
	//		gpa2.setCursoAcademico("20/21");
	//		gpa2.setGrupo(em.find(Grupo.class, "id2")); // Grupo B de primero
	//		
	//		gruposAsignatura.add(gpa1);
	//		gruposAsignatura.add(gpa2);
	//		
	//		enc.setGruposPorAsignatura(gruposAsignatura);
	//		
	//		try {
	//			encuestaEJB.registrarEncuesta(enc);
	//			fail("No lanza una excepciï¿½n (MalAsignacionEncuesta) avisando de que la encuesta tiene una seleccion incorrecta de grupos");
	//		} 
	//		catch(MalAsignacionEncuesta e) {
	//			/* COMPORTAMIENTO CORRECTO */
	//		}
	//		catch(Exception e) {
	//			fail("Lanza una excepciï¿½n distinta a MalAsignacionEncuesta");
	//		}
	//		
	//		/*  Simulamos una encuesta sin conflicto donde tienen distintos grupos asignados pero
	//		 *  para distintos aï¿½os
	//		 */ 
	//		Encuesta enc2 = new Encuesta();
	//		enc2.setFechaEnvio(Timestamp.valueOf("2020-09-03 11:00:00"));
	//		enc2.setExpediente(em.find(Expediente.class, 1));
	//		
	//		List<GruposPorAsignatura> gruposAsignatura2 = new ArrayList<>();
	//		GruposPorAsignatura gpa3 =  new GruposPorAsignatura();
	//		gpa3.setAsignatura(em.find(Asignatura.class, 1)); // Asignatura de primero
	//		gpa3.setCursoAcademico("20/21");
	//		gpa3.setGrupo(em.find(Grupo.class, "id1")); // Grupo A de primero
	//		
	//		GruposPorAsignatura gpa4 =  new GruposPorAsignatura();
	//		gpa4.setAsignatura(em.find(Asignatura.class, 3)); // Asignatura de segundo
	//		gpa4.setCursoAcademico("20/21");
	//		gpa4.setGrupo(em.find(Grupo.class, "id3")); // Grupo B de segundo
	//		
	//		gruposAsignatura2.add(gpa3);
	//		gruposAsignatura2.add(gpa4);
	//		
	//		enc2.setGruposPorAsignatura(gruposAsignatura2);
	//		
	//		try {
	//			encuestaEJB.registrarEncuesta(enc2);
	//		} 
	//		catch(Exception e) {
	//			fail("Lanza una excepciï¿½n cuando la asignaciï¿½n de grupo de la encuesta es correcta");
	//		}
	//	}
	
	@Requisitos({"RF6"})
	@Test
	@Ignore
	public void testDetectarIncompatibilidadHoraria() {
		try {
			encuestaEJB.incompatibilidadHoraria(null);
			fail("No lanza una excepciï¿½n (SecretariaException) avisando de que la encuesta era nula");
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
			fail("Lanza una excepciï¿½n al comprobar una encuesta correcta");
		}
	}
}
