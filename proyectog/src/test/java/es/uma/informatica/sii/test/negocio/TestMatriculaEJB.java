package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Matricula.MatriculaId;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
import es.uma.informatica.sii.exceptions.CursoInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.MatriculaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.MatriculaEJB;
import es.uma.informatica.sii.negocio.MatriculaInterface;

public class TestMatriculaEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;

	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";

	private static final String MATRICULA_EJB = "java:global/classes/MatriculaEJB";

	private EntityManagerFactory emf;
	private EntityManager em;

	private MatriculaInterface matriculaEJB;

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
		
		matriculaEJB = (MatriculaInterface) ctx.lookup(MATRICULA_EJB);
		BaseDatos.initBaseDatos();
	}
	
	@After
	public void tearDown() {
		if(em.isOpen()) {
			em.close();
		}
		emf.close();
	}

	@Requisitos({ "RF1" })
	@Test
	@Ignore
	public void testConsultarMatricula() {

		// 1 caso - paso un expediente vacio
		try {
			matriculaEJB.consultarMatricula(null);
			// No falla
			fail("Permite consultar un expediente nulo");
		} catch (SecretariaException exc1) {
			/* Falla y lanza la excepcion correcta */} catch (Exception e) {
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
		} catch (ExpedienteInexistente exc2) {
			/* Falla y lanza la excepcion correcta */
		} catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}

		// 4 caso - Consulta correcta
		Expediente expediente2 = em.find(Expediente.class, 8);
		// El metodo devuelve una lista distinta

		try {
			List<Matricula> matri1 = expediente2.getMatriculas();
			List<Matricula> matri2 = matriculaEJB.consultarMatricula(expediente2);
			
			if(matri1.size() != matri2.size()) {
				fail("El metodo no devuelve la lista correcta de matriculas");
			} 
			else {
				// SE CREA UN BUCLE INDIRECTO ENTRE ASIGNATURA - ASIGNATURAPORMATRICULA - MATRICULA
				assertEquals("ERROR", matri1, matri2);
			}
			
			
		} catch (Exception e) {
			fail("El metodo lanza una excepcion al comprobar un expediente correcto");
		}
	}


	
	// Test que comprueba los datos pasados por parametro de la consulta
	
	@Requisitos({ "RF1" })
	@Test
	public void testConsultarMatriculaPorCurso() {
		// 1 caso - paso un expediente vacio
		try {
			matriculaEJB.consultarMatricula(null, "20/21");
			// No falla
			fail("Permite consultar un expediente nulo");
		} catch (SecretariaException exc1) {
			/* Falla y lanza la excepcion correcta */} 
		catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}

		// 2 caso - paso un expediente no existente en la bbdd
		try {
			Expediente ex1 = new Expediente();
			ex1.setNumExpediente(5);
			ex1.setAlumno(em.find(Alumno.class, 1));
			ex1.setTitulacion(em.find(Titulacion.class, 1));

			matriculaEJB.consultarMatricula(ex1, "20/21");
			fail("Permite actualizar un expediente inexistente en la base de datos");
		} catch (ExpedienteInexistente exc2) {
			/* Falla y lanza la excepcion correcta */
		} catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
		// 3 caso - paso un curso null
		try {
			Expediente exp = em.find(Expediente.class, 8);
			matriculaEJB.consultarMatricula(exp, null);
			// No falla
			fail("Permite consultar un expediente por un curso acadico nulo");
		} catch (SecretariaException exc3) {
			/* Falla y lanza la excepcion correcta */} catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}

	}

	// Test que comprueba el funcionamiento correcto de la consulta
	@Requisitos({ "RF1" })
	@Test
	public void testBuscarMatriculaPorCurso() {

		// Caso 1 - paso un curso academico que no esta en ninguna matricula del
		// expediente
		Expediente exp = em.find(Expediente.class, 8);
		try {
			matriculaEJB.consultarMatricula(exp, "19/20");
			fail("Permite consultar un expediente con un curso academico no correspondiente a ninguna matricula");
		} catch (CursoInexistente exc2) {

		} catch (Exception e) {
			fail("Lanza una excepcion incorrecta");
		}
		
		// Caso 2 - Paso un curso perteneciente a una matricula del expediente
		try {
			// Solo hay una matricula en la bbdd para ese alumno
			Matricula m = exp.getMatriculas().get(0);
			assertTrue("El metodo devuelve una matricula incorrecta",
					m.equals(matriculaEJB.consultarMatricula(exp, "20/21")));
		} catch (Exception e) {
			fail("El metodo lanza una excepcion al comprobar un expediente con un curso academico correcto");
		}
	}
	
	@Requisitos({ "RF1" })
	@Test
	public void testListarTodasLasMatriculas() {
		//Caso 1 - Veo si devuelvo las matriculas ya existentes en la bbdd
		MatriculaId id1 = new MatriculaId("20/21",8);
		Matricula m1 = em.find(Matricula.class, id1);
		
		MatriculaId id2 = new MatriculaId("19/20",1);
		Matricula m2 = em.find(Matricula.class, id2);
		
		List<Matricula> matriculas = new ArrayList<Matricula>();
		matriculas.add(m1);
		matriculas.add(m2);
		
		try {
			assertTrue("El metodo no devuelve una lista correcta",matriculas.equals(matriculaEJB.consultarMatriculas()));
		} catch(Exception e) {
			fail("Lanza una excepciï¿½n inesperada");
		}
		
		// Meto una nueva matricula en la bbdd y veo si tambien me la devuelve
		Matricula m3 = new Matricula();
		m3.setCursoAcademico("18/19");
		m3.setExpediente(em.find(Expediente.class, 1));
		m3.setEstado(true);
		m3.setNumArchivo(10);
		m3.setTurnoPreferente("maï¿½ana");
		m3.setFechaMatricula(Timestamp.valueOf("2020-09-04 10:07:37"));
		
		em.persist(m3);
		matriculas.add(m3);
		
		try {
			assertTrue("El metodo no devuelve una lista correcta", matriculas.equals(matriculaEJB.consultarMatriculas()));
		} catch(Exception e) {
			fail("Lanza una excepciï¿½n inesperada");
		}
		
	}
	
	// Metodo que comprueba los datos pasados por parametro de desmatricular
	@Requisitos({ "RF9" })
	@Test
	public void testComprobacionParametrosDesmatricular() {
		// Caso 1 - Matricula nula
		try {
			matriculaEJB.desmatricularAsignatura(null, em.find(Asignatura.class,1));
			// No falla
			fail("Permite consultar una matricula nula");
		} catch (SecretariaException exc1) {
			/* Falla y lanza la excepcion correcta */} 
		catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
		// Caso 2 - Matricula pasada por parametro no existente en la bbdd
		Matricula m3 = new Matricula();
		m3.setCursoAcademico("17/18");
		Expediente exp = new Expediente();
		exp.setNumExpediente(2);
		m3.setExpediente(exp);
		m3.setEstado(true);
		m3.setNumArchivo(10);
		m3.setTurnoPreferente("mañana");
		m3.setFechaMatricula(Timestamp.valueOf("2020-09-04 10:07:37"));
		
		try {
			matriculaEJB.desmatricularAsignatura(m3, em.find(Asignatura.class,1));
			fail("Permite desmatricular a un matricula inexistente en la bbdd");
		} catch (MatriculaInexistente e1) {
			
		} catch(Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
		// Caso 3 - Asignatura nula
		try {
			MatriculaId id1 = new MatriculaId("20/21",8);
			Matricula m1 = em.find(Matricula.class, id1);
			
			matriculaEJB.desmatricularAsignatura(m1, null);
			// No falla
			fail("Permite consultar una asignatura nula");
		} catch (SecretariaException exc3) {
			/* Falla y lanza la excepcion correcta */} 
		catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
		
		// Caso 4 - Asignatura pasada por parametro no existente en la bbdd
	
		MatriculaId id1 = new MatriculaId("20/21",8);
		Matricula m1 = em.find(Matricula.class, id1);
		
		Asignatura asig = new Asignatura();
		asig.setReferencia(100);
		try {
			matriculaEJB.desmatricularAsignatura(m1, asig);
			fail("Permite desmatricular de una asignatura no existente en la bbdd");
		} catch (AsignaturaInexistente e) {
		
		} catch (Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
	}
	
	
	// Metodo que comprueba el funcionamiento correcto de desmatricular
	@Requisitos({ "RF9" })
	@Test
	public void testDesmatricularAsignatura() {
		
		// Caso 1 - La matricula no contiene la asignatura pasada por parametro 
		MatriculaId id1 = new MatriculaId("20/21",8);
		Matricula m1 = em.find(Matricula.class, id1);
		
		Asignatura asig2 = em.find(Asignatura.class, 2);
		try {
			matriculaEJB.desmatricularAsignatura(m1, asig2);
			fail("Permite desmatricular de una asignatura en la que no está matriculada");
		} catch(SecretariaException e) {
			
		} catch(Exception e) {
			fail("Lanza la excepcion incorrecta");
		}
		
		
		// Caso 2 - No desmatricula bien
		Asignatura asig1 = em.find(Asignatura.class, 1);
		try{
			matriculaEJB.desmatricularAsignatura(m1, asig1);
			// mat1 solo tiene asig1, si se ha desmatriculado, ahora la lista de m1 es vacia
			assertTrue("No ha desmatriculado correctamente", m1.getAsignaturasPorMatriculas().isEmpty());
		} catch(Exception e){
			fail("Se ha lanzado una excepcion inesperada");
		}
	}
}