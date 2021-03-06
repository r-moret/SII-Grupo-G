package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Matricula.MatriculaId;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
import es.uma.informatica.sii.exceptions.CursoInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.MatriculaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class MatriculaEJB implements MatriculaInterface {

	private static final String PERSISTENCE_UNIT = "proyectog-jpa";

	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	@Override
	public List<Matricula> consultarMatricula(Expediente alumno) throws SecretariaException {

		if(alumno == null) {
			throw new SecretariaException();
		}
				
		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());

		if(e == null) {
			//Alumno no est? en la bbdd
			throw new ExpedienteInexistente();
		}		
		return e.getMatriculas();	
	}

	@Override
	public Matricula consultarMatricula(Expediente alumno, String cursoAcademico) throws SecretariaException {
		if (alumno == null) {
			throw new SecretariaException();
		}

		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());
		if (e == null) {
			// Alumno no existe
			throw new ExpedienteInexistente();
		}
		
		if (cursoAcademico == null) {
			throw new SecretariaException();
		}
			
		int cont = 0;
		boolean enc = false;
		while (cont < e.getMatriculas().size() && !enc) {
			if (e.getMatriculas().get(cont).getCursoAcademico().equals(cursoAcademico)) {
				enc = true;
			}else {
				cont++;
			}
		}

		if(cont == e.getMatriculas().size() && !enc) {
			throw new CursoInexistente();
		}else if(!enc) {
			throw new MatriculaInexistente();
		} else {
			return e.getMatriculas().get(cont);
		}
	}

	@Override
	public List<Matricula> consultarMatriculas() throws SecretariaException {
		List<Matricula> lm = em.createQuery("SELECT m FROM Matricula m", Matricula.class).getResultList();
		
		return lm;
	}
	
	@Override
	public List<Asignatura> asignaturasDeMatricula(Matricula mat){	
		TypedQuery<AsignaturasPorMatriculas> query = em.createQuery("SELECT a FROM AsignaturasPorMatriculas a", AsignaturasPorMatriculas.class);
		
		List<AsignaturasPorMatriculas> lm = query.getResultList();
		List<Asignatura> asignaturas = new ArrayList<>();
		
		for(AsignaturasPorMatriculas asig : lm) {
			if(asig.getMatricula().getCursoAcademico().equals(mat.getCursoAcademico()) &&
			   asig.getMatricula().getExpediente().getNumExpediente().equals(mat.getExpediente().getNumExpediente())) {
				
				asignaturas.add(asig.getAsignatura());
			}
		}	
		return asignaturas;
	}

	@Override
	public void desmatricularAsignatura(Matricula matricula, Asignatura asignatura) throws SecretariaException {	
		if(matricula == null || asignatura == null) {
			throw new SecretariaException();
		}
		
		MatriculaId mId = new MatriculaId(matricula.getCursoAcademico(), matricula.getExpediente().getNumExpediente());
		Matricula m = em.find(Matricula.class, mId);

		if (m == null) {
			throw new MatriculaInexistente();
		}
		Asignatura a = em.find(Asignatura.class, asignatura.getReferencia());

		if (a == null) {
			throw new AsignaturaInexistente();
		}
		
		int cont = 0;
		boolean enc = false;
		while (cont < m.getAsignaturasPorMatriculas().size() && !enc) {		
			if (m.getAsignaturasPorMatriculas().get(cont).getAsignatura().equals(a)) {		
				enc = true;
			}else {
				cont++;
			}
		}
		
		if(!enc) {
			throw new SecretariaException();
		} else {
			em.remove(em.merge(m.getAsignaturasPorMatriculas().get(cont)));
		}
	}
	
	public void eliminarMatricula(Matricula matricula) {
		em.remove(em.merge(matricula));
	}
	
	@Override
	public String consultarTurnoMatricula(Expediente alumno) throws SecretariaException {
		if(alumno == null) {
			throw new SecretariaException();
		}
				
		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());

		if(e == null) {
			//Alumno no est? en la bbdd
			throw new ExpedienteInexistente();
		}			
		return consultarMatricula(e, obtenerCursoActual()).getTurnoPreferente();		
	}
	
	@Override
	public List<Integer> obtenerCodigosAsignaturasMatricula(Expediente alumno) throws SecretariaException {
		String listadoAsignaturas;
		List<Integer> res = new ArrayList<>();
		if(alumno == null) {
			throw new SecretariaException();
		}
				
		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());

		if(e == null) {
			//Alumno no est? en la bbdd
			throw new ExpedienteInexistente();
		}
		
		listadoAsignaturas = consultarMatricula(e, obtenerCursoActual()).getListadoAsignaturas();
		
		String[] asignaturas = listadoAsignaturas.split(",");
		for(int i = 0; i < asignaturas.length; i++) {
			if(!res.contains(Integer.parseInt(asignaturas[i].substring(0,3)))){
				res.add(Integer.parseInt(asignaturas[i].substring(0,3)));
			}
		}
		return res;
	}

	@Override
	public String obtenerCursoActual() {
		Date fecha = new Date();
		Integer anio1;
        SimpleDateFormat getAnio = new SimpleDateFormat("yy");
        String anio = getAnio.format(fecha);
		anio1 = Integer.parseInt(anio) - 1;
        return anio = anio1.toString() + "/" + anio;
	}
}
