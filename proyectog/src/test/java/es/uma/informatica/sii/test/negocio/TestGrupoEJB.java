package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.io.File;
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
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Matricula.MatriculaId;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo.SolicitudCambioGrupoID;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.AsignacionIndebida;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.MatriculaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.SolicitudDuplicada;
import es.uma.informatica.sii.negocio.GrupoInterface;
import es.uma.informatica.sii.negocio.PreferenciaSelector;
import es.uma.informatica.sii.negocio.AlgoritmoSelector;

public class TestGrupoEJB {

	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private static final String GLASSFISH_CONFIG_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	private static final String ENCUESTA_EJB = "java:global/classes/GrupoEJB";
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private GrupoInterface grupoEJB;
	
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
		
		grupoEJB = (GrupoInterface) ctx.lookup(ENCUESTA_EJB);
		BaseDatos.initBaseDatos();
	}
	
	@After
	public void tearDown() {
		if(em.isOpen()) {
			em.close();
		}
		
		emf.close();
	}
	
	@Requisitos({"RF4"})
	@Test
	public void testRegistrarSolicitudCambioGrupo() {
		try {
			grupoEJB.registrarSolicitudCambioGrupo(null);
			fail("No lanza una excepción (SecretariaException) avisando de que la solicitud era nula");
		} 
		catch(SecretariaException e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza una excepción distinta a SecretariaException");
		}
	
		Expediente ex = em.find(Expediente.class, 8);
		Grupo g1 = em.find(Grupo.class, "id2");
		Grupo g2 = em.find(Grupo.class, "id1");
		
		try {
			SolicitudCambioGrupo sol = new SolicitudCambioGrupo();
			List<File> docs = new ArrayList<>();
			sol.setDocumentacionAportada(docs);
			sol.setExpediente(ex);
			sol.setFechaRealizada(Timestamp.valueOf("2020-09-22 09:07:00"));
			sol.setGrupoActual(g1);
			sol.setGrupoSolicitado(g2);
			
			grupoEJB.registrarSolicitudCambioGrupo(sol);
			SolicitudCambioGrupoID id = new SolicitudCambioGrupoID(ex.getNumExpediente(), g1.getId());
			assertEquals("No se registra la encuesta correctamente", sol.getExpediente(), em.find(SolicitudCambioGrupo.class, id).getExpediente());
			assertEquals("No se registra la encuesta correctamente", sol.getGrupoActual(), em.find(SolicitudCambioGrupo.class, id).getGrupoActual());
		} 
		catch (Exception e) {
			fail("Lanza una excepción cuando se está registrando una encuesta correcta");
		}
		
		try {
			SolicitudCambioGrupo sol = new SolicitudCambioGrupo();
			List<File> docs = new ArrayList<>();
			sol.setDocumentacionAportada(docs);
			sol.setExpediente(ex);
			sol.setFechaRealizada(Timestamp.valueOf("2020-09-30 12:01:00"));
			sol.setGrupoActual(g1);
			sol.setGrupoSolicitado(g2);
			
			grupoEJB.registrarSolicitudCambioGrupo(sol);
			fail("Permite registrar una nueva solicitud cuando ya se tiene una para ese grupo registrada");
		} 
		catch (SolicitudDuplicada e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza la excepción incorrecta 1");
		}
	}
	
	@Requisitos({"RF3"})
	@Test
	public void testAsignarGrupos() throws SecretariaException {
		AlgoritmoSelector algo = new PreferenciaSelector();
		
		List<Matricula> matriculas = new ArrayList<>();
		
		MatriculaId id1 = new MatriculaId("20/21", 999);
		Matricula m1 = em.find(Matricula.class, id1);
		
		MatriculaId id2 = new MatriculaId("20/21", 1001);
		Matricula m2 = em.find(Matricula.class, id2);
		
		matriculas.add(m1);
		matriculas.add(m2);
		
		try {
			grupoEJB.asignarGrupos(null, matriculas.get(0));
			fail("No lanza una excepción cuando el algoritmo de selección es nulo");
		}
		catch(SecretariaException e) {
			try {
				grupoEJB.asignarGrupos(algo, null);
				fail("No lanza una excepción cuando la matrícula es nula");
			}
			catch(SecretariaException a) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception a) {
				fail("Lanza la excepción incorrecta");
			}
		}
		catch(Exception e) {
			fail("Lanza la excepción incorrecta");
		}
		
		try {
			for(Matricula mat : matriculas) {
				grupoEJB.asignarGrupos(algo, mat);
			}
		}
		catch(AsignacionIndebida e) {
			fail("No detecta correctamente si todas las asignaturas de un mismo curso tienen el mismo grupo");
		}
		catch(Exception e) {
			fail("Lanza una excepción no esperada " + e.toString());
		}
		
		Matricula m = new Matricula();
		Expediente ex = new Expediente();
		m.setCursoAcademico("noExiste");
		ex.setNumExpediente(-3000);
		m.setExpediente(ex);
		
		try {
			grupoEJB.asignarGrupos(algo, m);
			fail("Permite asignar grupo a una asignatura no almacenada en la base de datos");
		}
		catch(MatriculaInexistente e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("Lanza la excepción incorrecta");
		}
	}
	
	private String ultimoCursoMatriculado(Expediente ex) {
		Integer maxUltimoAnyo = -1;
		for(Matricula m : ex.getMatriculas()) {
			Integer ultimoAnyo = Integer.parseInt(m.getCursoAcademico().substring(3,5));
			if(ultimoAnyo > maxUltimoAnyo) {
				maxUltimoAnyo = ultimoAnyo;
			}
		}
		Integer previoAnyo = maxUltimoAnyo - 1;
		String ultimoCurso = previoAnyo.toString() + "/" + maxUltimoAnyo.toString();
		
		return ultimoCurso;
	}
	
	@Requisitos({"RF4"})
	@Test
	public void testReasignarGrupoInvalido() {
		Grupo gp1 = em.find(Grupo.class, "id1");
		Expediente ex1 = em.find(Expediente.class, 8);
		
		try {
			grupoEJB.reasignarGrupo(null, gp1);
		}
		catch(SecretariaException e) {
			try {
				grupoEJB.reasignarGrupo(ex1, null);
			}
			catch(SecretariaException a) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception a) {
				fail("No lanza la excepción correcta");
			}
		}
		catch(Exception e) {
			fail("No lanza la excepción correcta");
		}
		
		Grupo gp2 = new Grupo();
		gp2.setId("noExiste");
		gp2.setCurso(1);
		gp2.setLetra("K");
		gp2.setTurno("tarde");
		gp2.setIngles(false);
		
		Expediente ex2 = new Expediente();
		ex2.setNumExpediente(16);
		ex2.setTitulacion(em.find(Titulacion.class, 1));
		ex2.setMatriculas(new ArrayList<Matricula>());
		
		try {
			grupoEJB.reasignarGrupo(ex1, gp2);
		}
		catch(GrupoInexistente e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("No lanza la excepción que se esperaba (GrupoInexistente)");
		}
		
		try {
			grupoEJB.reasignarGrupo(ex2, gp1);
		}
		catch(ExpedienteInexistente e) {
			/* COMPORTAMIENTO CORRECTO */
		}
		catch(Exception e) {
			fail("No lanza la excepción que se esperaba (ExpedienteInexistente)");
		}
	}
	
	@Requisitos({"RF4"})
	@Test
	public void testReasignarGrupo() {
		Expediente al1 = em.find(Expediente.class, 8); // 1 B
		Grupo gp1 = em.find(Grupo.class, "id1"); // 1 A
		
		try {
			grupoEJB.reasignarGrupo(al1, gp1);
			
			String ultimoCurso = ultimoCursoMatriculado(al1);
			Matricula.MatriculaId idMat = new Matricula.MatriculaId(ultimoCurso, al1.getNumExpediente());
			Matricula mat = em.find(Matricula.class, idMat);
			
			for(AsignaturasPorMatriculas asigMat : mat.getAsignaturasPorMatriculas()) {
				if(asigMat.getAsignatura().getCurso() == gp1.getCurso()) {
					assertEquals("No se efectua el cambio de grupo en una asignatura de " + gp1.getCurso() + "º", asigMat.getGrupo(), gp1);
				}
			}
		}
		catch(Exception e) {
			fail("Lanza una excepción inesperada cuando el cambio debia ser efectivo");
		}
		
	}
	
	@Requisitos({"RF5"})
	@Test
	public void testPlazasTotales() {
		try {
			grupoEJB.plazasTotales(null);
		}
		catch(SecretariaException e) {
			try {
				Grupo g = new Grupo();
				g.setId("id7");
				grupoEJB.plazasTotales(g);
				fail("No lanza la excepción esperada, permite un grupo no almacenado en la base de datos");
			}
			catch(GrupoInexistente a) {
				/* COMPORTAMIENTO ESPERADO */
			}
			catch(Exception a) {
				fail("Lanza la excepción incorrecta");
			}
		}
		catch(Exception e) {
			fail("Lanza la excepción incorrecta");
		}
		
		Grupo g1 = em.find(Grupo.class, "id1"); // Dos grupos relacionados
		Grupo g2 = em.find(Grupo.class, "id2");
		Integer plazas = g1.getPlazas() + g2.getPlazas();
		
		Grupo g3 = em.find(Grupo.class, "id3"); // Grupo sin relacionar
		
		try {
			assertEquals("No coincide el numero de plazas totales de dos grupos relacionados", grupoEJB.plazasTotales(g1), grupoEJB.plazasTotales(g2));
			assertEquals("El numero de plazas totales no es correcto", plazas, grupoEJB.plazasTotales(g1));
			
			assertEquals("No coincide el numero de plazas totales de un grupo sin relaciones con su atributo interno", g3.getPlazas(), grupoEJB.plazasTotales(g3));
		}
		catch(Exception e) {
			fail("Lanza una excepcion inesperada");
		}
	}
	
	@Requisitos({"RF9"})
	@Test
	public void testActualizarGrupo() {
		try {
			grupoEJB.actualizarGrupo(null);
			fail("Permite actualizar un grupo nulo");
		}
		catch(SecretariaException exc1) {
			try {
				Grupo gr = new Grupo();
				gr.setId("id5");
				gr.setCurso(3);
				gr.setLetra("A");
				gr.setTurno("tarde");
				gr.setIngles(false);
				gr.setTitulacion(em.find(Titulacion.class, 1));
				
				grupoEJB.actualizarGrupo(gr);
				fail("Permite actualizar un grupo inexistente en la base de datos");
			}
			catch(GrupoInexistente exc2) {
				/* COMPORTAMIENTO CORRECTO */
			}
			catch(Exception exc2) {
				fail("Lanza la excepción incorrecta");
			}
		}
		catch(Exception exc1) {
			fail("Lanza la excepción incorrecta");
		}
		
		Grupo gr = em.find(Grupo.class, "id1");
		
		gr.setIngles(true);
		
		try {
			grupoEJB.actualizarGrupo(gr);
		} catch (Exception e) {
			fail("Lanza una excepción en la actualización de un grupo correcto");
		}
		
		assertEquals("El campo actualizado no se ha guardado en la base de datos", gr.getIngles(), em.find(Grupo.class, "id1").getIngles());	
	}
}
