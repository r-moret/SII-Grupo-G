package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
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
		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());
		
		if(e == null) {
			//Expediente no existe
			throw new ExpedienteInexistente();
		}
		
		List<Matricula> lm = new ArrayList<Matricula>();
		
		if(e.getMatriculas() != null) {
			lm.addAll(e.getMatriculas());
		}
		
		return lm;
	}

	@Override
	public Matricula consultarMatricula(Expediente alumno, String cursoAcademico) throws SecretariaException {
		Expediente e = em.find(Expediente.class, alumno.getNumExpediente());
		if(e == null) {
			//Expediente no existe
			throw new ExpedienteInexistente();
		}
		int cont = 0;
		boolean enc = false;
		while(cont < e.getMatriculas().size() && !enc) {
			if(e.getMatriculas().get(cont).getCursoAcademico().equals(cursoAcademico)) {
				enc = true;
			}
			cont++;
		}
		
		if(!enc) {
			throw new MatriculaInexistente();
		}else {
			return e.getMatriculas().get(cont);
		}
	}

	
	@Override
	public List<Matricula> consultarMatriculas() throws SecretariaException {
		List<Matricula> lm = new ArrayList<Matricula>();
		Query query = em.createQuery("SELECT * FROM MATRICULA");
		lm = query.getResultList();
		return lm;
	}

	@Override
	public void desmatricularAsignatura(Matricula matricula, Asignatura asignatura) throws SecretariaException {
		//Esto debería ser matriculaId (Matricula.MatriculaId.class)
		Matricula m = em.find(Matricula.class, matricula.getCursoAcademico());
		
		if(m == null) {
			throw new MatriculaInexistente();
		}
		Asignatura a = em.find(Asignatura.class, asignatura.getReferencia());
		
		if(a == null) {
			throw new AsignaturaInexistente();
		}
		
		int cont = 0;
		boolean enc = false;
		while(cont < m.getAsignaturasPorMatriculas().size() && !enc) {
			if(m.getAsignaturasPorMatriculas().get(cont).getAsignatura().equals(a)) {
				
				enc = true;
			}
			cont++;
		}
		
		em.remove(m.getAsignaturasPorMatriculas().get(cont).getAsignatura());
		matricula = em.merge(m);
	}

	
	
	
	
	
	
	
	
	
	
	
}