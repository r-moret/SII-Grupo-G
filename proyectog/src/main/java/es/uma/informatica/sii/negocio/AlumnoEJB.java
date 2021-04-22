package es.uma.informatica.sii.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;

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
}