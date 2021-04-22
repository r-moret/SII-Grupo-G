package es.uma.informatica.sii.test.negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.GrupoInterface;

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
	
	@Test
	@Ignore
	public void testRegistrarCambioGrupo() {
		// TODO
	}
	
	@Test
	@Ignore
	public void testAsignarGrupos() {
		// TODO
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
	
	
	@Test
	@Ignore
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
	
	@Test 
	@Ignore
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
					assertEquals(asigMat.getGrupo(), gp1);
				}
			}
		}
		catch(Exception e) {
			fail("Lanza una excepción inesperada cuando el cambio debia ser efectivo");
		}
		
	}
	
	@Test
	@Ignore
	public void testAforoMaximo() {
		// TODO
	}
	
	@Requisitos({"RF9"})
	@Test
	@Ignore
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
