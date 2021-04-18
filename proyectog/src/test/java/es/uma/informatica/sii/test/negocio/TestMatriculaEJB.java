package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.negocio.MatriculaInterface;

public class TestMatriculaEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;

	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";

	private static final String MATRICULA_EJB = "java:global/classes/MatriculaEJB";

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private MatriculaInterface matriculaEJB;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIG_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();

		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}

	@Before
	public void setUp() throws Exception {
		matriculaEJB = (MatriculaInterface) ctx.lookup(MATRICULA_EJB);
		BaseDatos.initBaseDatos();
	}

	@Requisitos({ "RF1" })
	@Test
	public void testConsultarMatricula() {

		// 1 caso - paso un expediente vacio
		try {
			matriculaEJB.consultarMatricula(null);
			// No falla
			fail("Permite consultar un expediente nulo");
		} catch (ExpedienteInexistente exc1) {
			/* Falla y lanza la excepcion correcta */} 
		catch (Exception e) {
			fail("Lanza la excepción incorrecta");
		}
		
		// 2 caso - paso un expediente no existente en la bbdd
		try {
			Expediente ex1 = new Expediente();
			ex1.setNumExpediente(5);
			ex1.setAlumno(em.find(Alumno.class, 1));
			ex1.setTitulacion(em.find(Titulacion.class, 1));
		
			matriculaEJB.consultarMatricula(ex1);
			fail("Permite actualizar un expediente inexistente en la base de datos");
		} catch (AlumnoInexistente exc2) {
			/* Falla y lanza la excepcion correcta */
		} catch (Exception e) {
			fail("Lanza la excepción incorrecta");
		}
		
		// 3 caso - devuelve una lista nula
		Expediente expediente = em.find(Expediente.class, 8);

		try {
	
			expediente.setMatriculas(null);
			assertTrue("El método devuelve Null en vez de una lista vacía", matriculaEJB.consultarMatricula(expediente).isEmpty());	

		} catch (Exception e) {
			fail("El método lanza una excepción cuando el comportamiento es correcto");
		}
		
		Expediente expediente2 = em.find(Expediente.class, 8);
		// El metodo devuelve una lista distinta
		
		try {
			assertTrue("El método no devuelve una lista de matrículas correcta",expediente2.getMatriculas().equals(matriculaEJB.consultarMatricula(expediente2)));
		} catch (Exception e) {
			fail("El método lanza una excepción al comprobar un expediente correcto");
		}

	}

}
