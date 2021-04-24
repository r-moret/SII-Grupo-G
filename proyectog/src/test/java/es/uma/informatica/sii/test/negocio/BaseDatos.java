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
		
		Titulacion tit2 = new Titulacion();
		tit2.setCodigo(1041);
		tit2.setNombre("Informatica");
		tit2.setCreditos(6);
		tit2.setCentros(new ArrayList<>());
		tit2.getCentros().add(cent1);
		
		em.persist(tit2);
		
		Titulacion tit3 = new Titulacion();
		tit3.setCodigo(1042);
		tit3.setNombre("Software");
		tit3.setCreditos(6);
		tit3.setCentros(new ArrayList<>());
		tit3.getCentros().add(cent1);
		
		em.persist(tit3);
		
		Titulacion tit4 = new Titulacion();
		tit4.setCodigo(1043);
		tit4.setNombre("Computadores");
		tit4.setCreditos(6);
		tit4.setCentros(new ArrayList<>());
		tit4.getCentros().add(cent1);
		
		em.persist(tit4);
		
		Titulacion tit5 = new Titulacion();
		tit5.setCodigo(1056);
		tit5.setNombre("Salud");
		tit5.setCreditos(6);
		tit5.setCentros(new ArrayList<>());
		tit5.getCentros().add(cent1);
		
		em.persist(tit5);

		Titulacion tit6 = new Titulacion();
		tit6.setCodigo(1073);
		tit6.setNombre("DobleGrado");
		tit6.setCreditos(6);
		tit6.setCentros(new ArrayList<>());
		tit6.getCentros().add(cent1);
		
		em.persist(tit6);
		
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
		
		Alumno al3 = new Alumno();
		al3.setDni("89LJ");
		al3.setNombreCompleto("Alfonso Roca");
		al3.setEmailInstitucional("roca@hotmail.es");
		
		em.persist(al3);
		
		Alumno al4 = new Alumno();
		al4.setDni("999T");
		al4.setNombreCompleto("Diana Perez");
		al4.setEmailInstitucional("di@gmail.es");
		
		em.persist(al4);
		
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
		
		Expediente ex3 = new Expediente();
		ex3.setNumExpediente(999);
		ex3.setAlumno(al3);
		ex3.setTitulacion(tit1);
		ex3.setActivo(false);
		
		em.persist(ex3);
		
		Expediente ex4 = new Expediente();
		ex4.setNumExpediente(1001);
		ex4.setAlumno(al4);
		ex4.setTitulacion(tit1);
		ex4.setActivo(false);
		
		em.persist(ex4);
		
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
		
		Matricula mat3 = new Matricula();
		mat3.setCursoAcademico("20/21");
		mat3.setExpediente(ex3);
		mat3.setEstado(true);
		mat3.setNumArchivo(22);
		mat3.setTurnoPreferente("tarde");
		mat3.setFechaMatricula(Timestamp.valueOf("2020-09-21 11:35:01"));
		
		em.persist(mat3);
		
		Matricula mat4 = new Matricula();
		mat4.setCursoAcademico("20/21");
		mat4.setExpediente(ex4);
		mat4.setEstado(true);
		mat4.setNumArchivo(34);
		mat4.setTurnoPreferente("mañana");
		mat4.setFechaMatricula(Timestamp.valueOf("2020-09-03 12:38:01"));
		
		em.persist(mat4);
		
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
		grup1.setPlazas(50);
		List<Grupo> rel1 = new ArrayList<Grupo>();
		
		Grupo grup2 = new Grupo();
		grup2.setId("id2");
		grup2.setCurso(1);
		grup2.setLetra("B");
		grup2.setTurno("mañana");
		grup2.setIngles(false);
		grup2.setTitulacion(tit1);
		grup2.setPlazas(30);
		List<Grupo> rel2 = new ArrayList<Grupo>();
		
		Grupo grup3 = new Grupo();
		grup3.setId("id3");
		grup3.setCurso(2);
		grup3.setLetra("B");
		grup3.setTurno("mañana");
		grup3.setIngles(false);
		grup3.setTitulacion(tit1);
		grup3.setPlazas(20);
		List<Grupo> rel3 = new ArrayList<Grupo>();
		
		Grupo grup4 = new Grupo();
		grup4.setId("id4");
		grup4.setCurso(2);
		grup4.setLetra("A");
		grup4.setTurno("mañana");
		grup4.setIngles(false);
		grup4.setTitulacion(tit1);
		grup4.setPlazas(20);
		List<Grupo> rel4 = new ArrayList<Grupo>();
		
		rel1.add(grup2);
		rel2.add(grup1);
		
		grup1.setRelacionados(rel1);
		grup2.setRelacionados(rel2);
		
		grup3.setRelacionados(rel3);
		grup4.setRelacionados(rel4);
		
		em.persist(grup1);
		em.persist(grup2);
		em.persist(grup3);
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
		
		// Seleccion con conflicto horario
		// 20/21 - Calculo - 1A
		List<GruposPorAsignatura> gpa1 = new ArrayList<>();
		GruposPorAsignatura gp1 = new GruposPorAsignatura();
		gp1.setCursoAcademico("20/21");
		gp1.setAsignatura(asig1);
		gp1.setGrupo(grup1);
		
		em.persist(gp1);
		
		GruposPorAsignatura gp2 = new GruposPorAsignatura();
		// 20/21 - TDC - 2B
		gp2.setCursoAcademico("20/21");
		gp2.setAsignatura(asig3);
		gp2.setGrupo(grup3);
		
		gpa1.add(gp1);
		gpa1.add(gp2);
		
		em.persist(gp2);
		
		// Seleccion sin conflicto horario
		// 20/21 - TDC - 2A
		List<GruposPorAsignatura> gpa2 = new ArrayList<>();
		GruposPorAsignatura gp3 = new GruposPorAsignatura();
		gp3.setCursoAcademico("20/21");
		gp3.setAsignatura(asig3);
		gp3.setGrupo(grup4);
		
		gpa2.add(gp1);
		gpa2.add(gp3);
		
		em.persist(gp3);
		
		Encuesta enc1 = new Encuesta();
		// Calculo - 1A
		// TDC     - 2B
		enc1.setExpediente(ex1);
		enc1.setFechaEnvio(Timestamp.valueOf("2020-08-21 10:00:00"));
		enc1.setGruposPorAsignatura(gpa1);
		
		em.persist(enc1);
		
		Encuesta enc2 = new Encuesta();
		// Calculo - 1A
		// TDC     - 2A
		enc2.setExpediente(ex2);
		enc2.setFechaEnvio(Timestamp.valueOf("2020-09-21 16:00:00"));
		enc2.setGruposPorAsignatura(gpa2);
		
		em.persist(enc2);
		
		Encuesta enc3 = new Encuesta();
		// Calculo - 1A
		// TDC     - 2A
		enc3.setExpediente(ex3);
		enc3.setFechaEnvio(Timestamp.valueOf("2020-09-31 19:00:00"));
		enc3.setGruposPorAsignatura(gpa2);
		
		em.persist(enc3);
		
		Encuesta enc4 = new Encuesta();
		// Calculo - 1A
		// TDC     - 2B
		enc4.setExpediente(ex4);
		enc4.setFechaEnvio(Timestamp.valueOf("2020-09-26 03:00:00"));
		enc4.setGruposPorAsignatura(gpa1);
		
		em.persist(enc4);
		
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
		
		// Matricula pendiente de asignar grupos
		AsignaturasPorMatriculas am3 = new AsignaturasPorMatriculas();
		am3.setAsignatura(asig1);
		am3.setMatricula(mat3);
		
		em.persist(am3);
		
		AsignaturasPorMatriculas am4 = new AsignaturasPorMatriculas();
		am4.setAsignatura(asig3);
		am4.setMatricula(mat3);
		
		em.persist(am4);
		
		// Matricula pendiente de asignar grupos
		AsignaturasPorMatriculas am5 = new AsignaturasPorMatriculas();
		am5.setAsignatura(asig1);
		am5.setMatricula(mat4);
		
		em.persist(am5);
		
		AsignaturasPorMatriculas am6 = new AsignaturasPorMatriculas();
		am6.setAsignatura(asig3);
		am6.setMatricula(mat4);
		
		em.persist(am6);
		
		// FIN DE LA INICIALIZACION DE LA BASE DE DATOS
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
}
