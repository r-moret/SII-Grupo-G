package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class AlumnoEJB implements AlumnoInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	@Override
	public void actualizarAlumno(Alumno alumno) throws AlumnoInexistente {	
		if(alumno == null) {
			//Alumno no existe
			throw new AlumnoInexistente();
		}
		
		Alumno a = em.find(Alumno.class, alumno.getId());
		
		if(a == null) {
			//Alumno no existe en la BBDD
			throw new AlumnoInexistente();
		}

		em.merge(alumno);
	}
	
	@Override
	public List<Alumno> consultarAlumnos() throws SecretariaException {
		List<Alumno> la = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
		return la;	
	}
}