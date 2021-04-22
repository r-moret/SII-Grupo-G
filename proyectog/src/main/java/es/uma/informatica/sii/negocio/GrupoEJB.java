package es.uma.informatica.sii.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.exceptions.AlumnoInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class GrupoEJB implements GrupoInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;


	@Override
	public void asignarGrupos(Algoritmo selector) throws SecretariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reasignarGrupo(Alumno alumno, Grupo grupo) throws SecretariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aforoMaximo() throws SecretariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarGrupo(Grupo grupo) throws SecretariaException {
		if(grupo == null) {
			//Grupo no existe
			throw new AlumnoInexistente();
		}
		
		Grupo g = em.find(Grupo.class, grupo.getId());
		
		if(g == null) {
			//Grupo no existe en la BBDD
			throw new GrupoInexistente();
		}

		em.merge(grupo);
		
	}

}
