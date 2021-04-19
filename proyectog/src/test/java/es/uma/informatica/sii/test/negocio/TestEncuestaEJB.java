package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Clase;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.exceptions.MalAsignacionEncuesta;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.EncuestaInterface;

public class TestEncuestaEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaEJB";
	
	// TODO: ERROR -> El acceso a la BD lo tienen que hacer los EJB. 
	// 		 NO ES NECESARIO UN ENTITY MANAGER EN LOS TEST
	@PersistenceContext(name=PERSISTENCE_UNIT)
	private static EntityManager em;
	
	private EncuestaInterface encuestaEJB;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIG_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}

	@Before
	public void setUp() throws Exception {
		encuestaEJB = (EncuestaInterface) ctx.lookup(ENCUESTA_EJB);
		BaseDatos.initBaseDatos();
	}
	
	private Encuesta crearEncuesta(boolean incompatibilidadHoraria) {
		Encuesta enc = new Encuesta();
		if(incompatibilidadHoraria) {
			Expediente exp1 = em.find(Expediente.class, 1);
			
			enc.setExpediente(exp1);
			enc.setFechaEnvio(Timestamp.valueOf("2020-21-08 10:00:00"));
			
			Asignatura asig1 = em.find(Asignatura.class, 1);
			Asignatura asig2 = em.find(Asignatura.class, 2);
			Asignatura asig3 = em.find(Asignatura.class, 3);
			
			Grupo g1 = em.find(Grupo.class, "id1");
			Grupo g2 = em.find(Grupo.class, "id2");
			Grupo g3 = em.find(Grupo.class, "id3");
			
			Clase c1 = new Clase();
			c1.setDia("Lunes");
			c1.setHoraInicio("10:45");
			c1.setAsignatura(asig1);
			c1.setGrupo(g1);
			
			Clase c2 = new Clase();
			c2.setDia("Lunes");
			c2.setHoraInicio("10:45");
			c2.setAsignatura(asig2);
			c2.setGrupo(g2);
			
			Clase c3 = new Clase();
			c3.setDia("Miercoles");
			c3.setHoraInicio("12:45");
			c3.setAsignatura(asig1);
			c3.setGrupo(g1);
			
			Clase c4 = new Clase();
			c4.setDia("Miercoles");
			c4.setHoraInicio("12:45");
			c4.setAsignatura(asig3);
			c4.setGrupo(g3);
			
			List<GruposPorAsignatura> gpa = new ArrayList<>();
			
			GruposPorAsignatura gp1 = new GruposPorAsignatura();
			gp1.setCursoAcademico("20/21");
			gp1.setAsignatura(asig1);
			gp1.setGrupo(g1);
			
			GruposPorAsignatura gp2 = new GruposPorAsignatura();
			gp2.setCursoAcademico("20/21");
			gp2.setAsignatura(asig3);
			gp2.setGrupo(g3);
			
			gpa.add(gp1);
			gpa.add(gp2);
			
			enc.setGruposPorAsignatura(gpa);
		}
		else {
			Expediente exp1 = em.find(Expediente.class, 8);
			
			enc.setExpediente(exp1);
			enc.setFechaEnvio(Timestamp.valueOf("2020-16-08 04:00:00"));
			
			Asignatura asig1 = em.find(Asignatura.class, 1);
			Asignatura asig2 = em.find(Asignatura.class, 2);
			Asignatura asig3 = em.find(Asignatura.class, 3);
			
			Grupo g1 = em.find(Grupo.class, "id1");
			Grupo g2 = em.find(Grupo.class, "id2");
			Grupo g3 = em.find(Grupo.class, "id3");
			
			Clase c1 = new Clase();
			c1.setDia("Martes");
			c1.setHoraInicio("08:45");
			c1.setAsignatura(asig1);
			c1.setGrupo(g1);
			
			Clase c2 = new Clase();
			c2.setDia("Lunes");
			c2.setHoraInicio("10:45");
			c2.setAsignatura(asig2);
			c2.setGrupo(g2);
			
			Clase c3 = new Clase();
			c3.setDia("Miercoles");
			c3.setHoraInicio("10:45");
			c3.setAsignatura(asig1);
			c3.setGrupo(g1);
			
			Clase c4 = new Clase();
			c4.setDia("Jueves");
			c4.setHoraInicio("10:45");
			c4.setAsignatura(asig3);
			c4.setGrupo(g3);
			
			List<GruposPorAsignatura> gpa = new ArrayList<>();
			
			GruposPorAsignatura gp1 = new GruposPorAsignatura();
			gp1.setCursoAcademico("20/21");
			gp1.setAsignatura(asig1);
			gp1.setGrupo(g1);
			
			GruposPorAsignatura gp2 = new GruposPorAsignatura();
			gp2.setCursoAcademico("20/21");
			gp2.setAsignatura(asig3);
			gp2.setGrupo(g3);
			
			gpa.add(gp1);
			gpa.add(gp2);
			
			enc.setGruposPorAsignatura(gpa);
		}
		
		return enc;
	}
	
	@Requisitos({"RF2"})
	@Test
	@Ignore
	public void testRegistroEncuestaCorrecto() {
		try {
			encuestaEJB.registrarEncuesta(null);
			fail("No lanza una excepción (SecretariaException) avisando de que la encuesta era nula");
		} 
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepción distinta a SecretariaException");
		}
		
		Encuesta enc = crearEncuesta(false);
		Expediente expedienteEncuesta = enc.getExpediente();
		assertNotNull("El expediente asociado a la encuesta no está registrado en la base de datos", em.find(Expediente.class, expedienteEncuesta.getNumExpediente()));
	
		try {
			encuestaEJB.registrarEncuesta(enc);
			Encuesta newEnc = crearEncuesta(true);
			newEnc.setExpediente(expedienteEncuesta);
			newEnc.setFechaEnvio(enc.getFechaEnvio());
			encuestaEJB.registrarEncuesta(newEnc);
			
			Encuesta.EncuestaID id = new Encuesta.EncuestaID(newEnc.getFechaEnvio(), newEnc.getExpediente().getNumExpediente());
			
			// Aseguramos que hemos sobreescrito la base de datos con los nuevos datos que están en newEnc
			assertEquals(newEnc, em.find(Encuesta.class, id));
		} 
		catch (Exception e) {
			fail("Lanza una excepción cuando se está registrando una encuesta correcta");
		}	
	}
	
	@Requisitos({"RF2"})
	@Test
	@Ignore
	public void testDistintoGrupoMismoCurso() {
		/*  Simulamos una encuesta con un conflicto en el cual dos asignaturas del mismo año
		 *  tienen grupos distintos asignados
		 */ 
		Encuesta enc = new Encuesta();
		enc.setFechaEnvio(Timestamp.valueOf("2020-09-03 10:00:00"));
		enc.setExpediente(em.find(Expediente.class, 8));
		
		List<GruposPorAsignatura> gruposAsignatura = new ArrayList<>();
		GruposPorAsignatura gpa1 =  new GruposPorAsignatura();
		gpa1.setAsignatura(em.find(Asignatura.class, 1)); // Asignatura de primero
		gpa1.setCursoAcademico("20/21");
		gpa1.setGrupo(em.find(Grupo.class, "id1")); // Grupo A de primero
		
		GruposPorAsignatura gpa2 =  new GruposPorAsignatura();
		gpa2.setAsignatura(em.find(Asignatura.class, 2)); // Asignatura de primero
		gpa2.setCursoAcademico("20/21");
		gpa2.setGrupo(em.find(Grupo.class, "id2")); // Grupo B de primero
		
		gruposAsignatura.add(gpa1);
		gruposAsignatura.add(gpa2);
		
		enc.setGruposPorAsignatura(gruposAsignatura);
		
		try {
			encuestaEJB.registrarEncuesta(enc);
			fail("No lanza una excepción (MalAsignacionEncuesta) avisando de que la encuesta tiene una seleccion incorrecta de grupos");
		} 
		catch(MalAsignacionEncuesta e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepción distinta a MalAsignacionEncuesta");
		}
		
		/*  Simulamos una encuesta sin conflicto donde tienen distintos grupos asignados pero
		 *  para distintos años
		 */ 
		Encuesta enc2 = new Encuesta();
		enc2.setFechaEnvio(Timestamp.valueOf("2020-09-03 11:00:00"));
		enc2.setExpediente(em.find(Expediente.class, 1));
		
		List<GruposPorAsignatura> gruposAsignatura2 = new ArrayList<>();
		GruposPorAsignatura gpa3 =  new GruposPorAsignatura();
		gpa3.setAsignatura(em.find(Asignatura.class, 1)); // Asignatura de primero
		gpa3.setCursoAcademico("20/21");
		gpa3.setGrupo(em.find(Grupo.class, "id1")); // Grupo A de primero
		
		GruposPorAsignatura gpa4 =  new GruposPorAsignatura();
		gpa4.setAsignatura(em.find(Asignatura.class, 3)); // Asignatura de segundo
		gpa4.setCursoAcademico("20/21");
		gpa4.setGrupo(em.find(Grupo.class, "id3")); // Grupo B de segundo
		
		gruposAsignatura2.add(gpa3);
		gruposAsignatura2.add(gpa4);
		
		enc2.setGruposPorAsignatura(gruposAsignatura2);
		
		try {
			encuestaEJB.registrarEncuesta(enc2);
		} 
		catch(Exception e) {
			fail("Lanza una excepción cuando la asignación de grupo de la encuesta es correcta");
		}
	}
	
	@Requisitos({"RF6"})
	@Test
	public void testDetectarIncompatibilidadHoraria() {
		try {
			encuestaEJB.incompatibilidadHoraria(null);
			fail("No lanza una excepción (SecretariaException) avisando de que la encuesta era nula");
		}
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepcion distinta a SecretariaException" + e.toString());
		}
		
		Encuesta encCompatible = crearEncuesta(false);
		Encuesta encIncompatible = crearEncuesta(true);
		
		try {
			assertFalse("Detecta incompatibilidad en una lista sin incompatibilidad horaria", encuestaEJB.incompatibilidadHoraria(encCompatible));
			assertTrue("No detecta incompatibilidad en una lista con incompatibilidad horaria", encuestaEJB.incompatibilidadHoraria(encIncompatible));
		} catch (Exception e) {
			fail("Lanza una excepción al comprobar una encuesta correcta");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}
