package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.exceptions.AsignaturaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class AsignaturaEJB implements AsignaturaInterface {
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	@Override
	public List<Asignatura> consultarAsignaturas() throws SecretariaException {
		List<Asignatura> la = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class).getResultList();
		return la;
	}
	
	@Override
	public List<Integer> obtenerCursos(List<Integer> codigos)throws SecretariaException {
		List<Asignatura> la = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class).getResultList();
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 0;i < codigos.size();i++){
			boolean enc = false;
			int j = 0;
			while(j < la.size() && !enc) {
				if(la.get(j).getCodigo().equals(codigos.get(i))) {
					enc = true;
					if(!res.contains(la.get(j).getCurso())){
						res.add(la.get(j).getCurso());
					}
				}
				j++;
			}
		}			
		return res;
	}
}