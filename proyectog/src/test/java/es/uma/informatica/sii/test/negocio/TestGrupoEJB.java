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
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Titulacion;
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
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private GrupoInterface grupoEJB;
	
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
		grupoEJB = (GrupoInterface) ctx.lookup(ENCUESTA_EJB);
		BaseDatos.initBaseDatos();
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
	
	@AfterClass
	public static void tearDownAfterClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}
