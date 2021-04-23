package es.uma.informatica.sii.test.negocio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Centro;
import es.uma.informatica.sii.entidades.Clase;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Titulacion;

public class BaseDatos {

	private final static String PERSISTENCE_UNIT = "proyectog-jpa-test";
	
	public static void initBaseDatos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		// INICIO DE LA INICIALIZACION DE LA BASE DE DATOS
		
		Centro cent1 = new Centro();
		cent1.setId(1);
		cent1.setNombre("ETSII");
		cent1.setDireccion("C/Louis Pasteur");
		
		em.persist(cent1);
		
		Titulacion tit1 = new Titulacion();
		tit1.setCodigo(1);
		tit1.setNombre("Informatica");
		tit1.setCreditos(6);
		tit1.setCentros(new ArrayList<>());
		tit1.getCentros().add(cent1);
		
		em.persist(tit1);
		
		Alumno al1 = new Alumno();
		al1.setDni("12W");
		al1.setNombreCompleto("Antonio Miguel Sánchez");
		al1.setEmailInstitucional("anton@gmail.com");
		
		em.persist(al1);
		
		Alumno al2 = new Alumno();
		al2.setDni("24J");
		al2.setNombreCompleto("Juan Martin");
		al2.setEmailInstitucional("juan@hotmail.es");
		
		em.persist(al2);
		
		Expediente ex1 = new Expediente();
		ex1.setNumExpediente(8);
		ex1.setAlumno(al1);
		ex1.setTitulacion(tit1);
		ex1.setActivo(true);
		
		em.persist(ex1);
		
		
		Expediente ex2 = new Expediente();
		ex2.setNumExpediente(1);
		ex2.setAlumno(al2);
		ex2.setTitulacion(tit1);
		ex2.setActivo(false);
		
		em.persist(ex2);
		
		
		Matricula mat1 = new Matricula();
		mat1.setCursoAcademico("20/21");
		mat1.setExpediente(ex1);
		mat1.setEstado(true);
		mat1.setNumArchivo(10);
		mat1.setTurnoPreferente("mañana");
		mat1.setFechaMatricula(Timestamp.valueOf("2020-09-04 10:07:37"));
		
		em.persist(mat1);
		
		Matricula mat2 = new Matricula();
		mat2.setCursoAcademico("19/20");
		mat2.setExpediente(ex2);
		mat2.setEstado(true);
		mat2.setNumArchivo(13);
		mat2.setTurnoPreferente("tarde");
		mat2.setFechaMatricula(Timestamp.valueOf("2020-09-11 16:38:01"));
		
		em.persist(mat2);
		
		Asignatura asig1 = new Asignatura();
		asig1.setReferencia(1);
		asig1.setCodigo(340);
		asig1.setCreditos(6);
		asig1.setOfertada(true);
		asig1.setNombre("Calculo");
		asig1.setCurso(1);
		asig1.setTitulacion(tit1);
		asig1.setPlazas(80);
		
		em.persist(asig1);
		
		Asignatura asig2 = new Asignatura();
		asig2.setReferencia(2);
		asig2.setCodigo(976);
		asig2.setCreditos(6);
		asig2.setOfertada(true);
		asig2.setNombre("Algebra");
		asig2.setCurso(1);
		asig2.setTitulacion(tit1);
		asig2.setPlazas(60);
		
		em.persist(asig2);
		
		Asignatura asig3 = new Asignatura();
		asig3.setReferencia(3);
		asig3.setCodigo(122);
		asig3.setCreditos(6);
		asig3.setOfertada(true);
		asig3.setNombre("TDC");
		asig3.setCurso(2);
		asig3.setTitulacion(tit1);
		asig3.setPlazas(50);
		
		em.persist(asig3);
		
		Grupo grup1 = new Grupo();
		grup1.setId("id1");
		grup1.setCurso(1);
		grup1.setLetra("A");
		grup1.setTurno("mañana");
		grup1.setIngles(false);
		grup1.setTitulacion(tit1);
		
		em.persist(grup1);
		
		Grupo grup2 = new Grupo();
		grup2.setId("id2");
		grup2.setCurso(1);
		grup2.setLetra("B");
		grup2.setTurno("mañana");
		grup2.setIngles(false);
		grup2.setTitulacion(tit1);
		
		em.persist(grup2);
		
		Grupo grup3 = new Grupo();
		grup3.setId("id3");
		grup3.setCurso(2);
		grup3.setLetra("B");
		grup3.setTurno("mañana");
		grup3.setIngles(false);
		grup3.setTitulacion(tit1);
		
		em.persist(grup3);
		
		Grupo grup4 = new Grupo();
		grup4.setId("id4");
		grup4.setCurso(2);
		grup4.setLetra("A");
		grup4.setTurno("mañana");
		grup4.setIngles(false);
		grup4.setTitulacion(tit1);
		
		em.persist(grup4);
		
		Clase c1 = new Clase();
		c1.setDia("Lunes");
		c1.setHoraInicio("10:45");
		c1.setAsignatura(asig1);
		c1.setGrupo(grup1);
		
		em.persist(c1);
		
		Clase c2 = new Clase();
		c2.setDia("Jueves");
		c2.setHoraInicio("10:45");
		c2.setAsignatura(asig3);
		c2.setGrupo(grup4);
		
		em.persist(c2);
		
		Clase c3 = new Clase();
		c3.setDia("Miercoles");
		c3.setHoraInicio("12:45");
		c3.setAsignatura(asig1);
		c3.setGrupo(grup1);
		
		em.persist(c3);
		
		Clase c4 = new Clase();
		c4.setDia("Miercoles");
		c4.setHoraInicio("12:45");
		c4.setAsignatura(asig3);
		c4.setGrupo(grup3);
		
		em.persist(c4);
		
		// Seleccion con conflicto
		List<GruposPorAsignatura> gpa1 = new ArrayList<>();
		GruposPorAsignatura gp1 = new GruposPorAsignatura();
		gp1.setCursoAcademico("20/21");
		gp1.setAsignatura(asig1);
		gp1.setGrupo(grup1);
		
		em.persist(gp1);
		
		GruposPorAsignatura gp2 = new GruposPorAsignatura();
		gp2.setCursoAcademico("20/21");
		gp2.setAsignatura(asig3);
		gp2.setGrupo(grup3);
		
		gpa1.add(gp1);
		gpa1.add(gp2);
		
		em.persist(gp2);
		
		// Seleccion sin conflicto
		List<GruposPorAsignatura> gpa2 = new ArrayList<>();
		GruposPorAsignatura gp3 = new GruposPorAsignatura();
		gp3.setCursoAcademico("20/21");
		gp3.setAsignatura(asig3);
		gp3.setGrupo(grup4);
		
		gpa2.add(gp1);
		gpa2.add(gp3);
		
		em.persist(gp3);
		
		Encuesta enc1 = new Encuesta();
		enc1.setExpediente(ex1);
		enc1.setFechaEnvio(Timestamp.valueOf("2020-08-21 10:00:00"));
		enc1.setGruposPorAsignatura(gpa1);
		
		em.persist(enc1);
		
		Encuesta enc2 = new Encuesta();
		enc2.setExpediente(ex2);
		enc2.setFechaEnvio(Timestamp.valueOf("2020-09-21 16:00:00"));
		enc2.setGruposPorAsignatura(gpa2);
		
		em.persist(enc2);
		
		AsignaturasPorMatriculas am1 = new AsignaturasPorMatriculas();
		am1.setAsignatura(asig1);
		am1.setGrupo(grup2);
		am1.setMatricula(mat1);
		
		em.persist(am1);
		
		AsignaturasPorMatriculas am2 = new AsignaturasPorMatriculas();
		am2.setAsignatura(asig2);
		am2.setGrupo(grup2);
		am2.setMatricula(mat1);
		
		em.persist(am2);
		
		// FIN DE LA INICIALIZACION DE LA BASE DE DATOS
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
}
