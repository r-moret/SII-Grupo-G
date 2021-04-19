package es.uma.informatica.sii.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;

public class AlumnoEJB implements AlumnoInterface{
	

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectog-jpa");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void actualizarAlumno(Alumno alumno) throws AlumnoInexistente {
				
		Alumno a = em.find(Alumno.class, alumno.getId());
		if(a == null) {
			//Alumno no existe
			throw new AlumnoInexistente();
		}
		alumno = em.merge(a);
		em.persist(alumno);
	}
}