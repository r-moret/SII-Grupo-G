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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.informatica.sii.entidades.Titulacion;
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
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private ExpedienteInterface expedienteEJB;
	
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
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

	@Before
	public void setUp() throws Exception {
		expedienteEJB = (ExpedienteInterface) ctx.lookup(ALUMNO_EJB);
		BaseDatos.initBaseDatos();
	}

	@Test
	public void testActualizarExpediente() {
		
		try {
			expedienteEJB.actualizarExpediente(null);
			fail("Permite actualizar un expediente nulo");
		}
		catch(SecretariaException exc1) {
			try {
				Expediente exp = new Expediente();
				exp.setNumExpediente(4);
				exp.setTitulacion(em.find(Titulacion.class, 1));
				exp.setAlumno(em.find(Alumno.class, "12W"));
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
				fail("Lanza la excepción incorrecta");
			}
		}
		catch(Exception exc1) {
			fail("Lanza la excepción incorrecta");
		}
		
		Expediente exp = em.find(Expediente.class, 1);
		
		exp.setActivo(true);
		
		try {
			expedienteEJB.actualizarExpediente(exp);
		} catch (Exception e) {
			fail("Lanza una excepción en la actualización de un alumno correcto");
		}
		
		assertEquals("El campo actualizado no se ha guardado en la base de datos", exp.getActivo(), em.find(Expediente.class, 1).getActivo());	
	}

}
