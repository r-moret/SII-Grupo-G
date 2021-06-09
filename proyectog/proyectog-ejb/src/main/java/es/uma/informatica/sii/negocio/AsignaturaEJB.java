package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Asignatura;
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
	
	@Override
	public List<List<Asignatura>> obtenerListaAsignaturas(List<Integer> codigos) throws SecretariaException{
		//no funciona
		List<List<Asignatura>> res = new ArrayList<List<Asignatura>>();
		List<Integer> cursos = obtenerCursos(codigos); //1, 3, 4
		List<Asignatura> la = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class).getResultList();
		List<Asignatura> aux= new ArrayList<>();
		
		for(int i = 1; i < 5; i++) {
			for(int j = 0; j < codigos.size();j++) {
				boolean enc = false;
				int k = 0;
				if(i == Integer.parseInt(codigos.get(j).toString().substring(0,1))) {
					while(k < la.size() && !enc) {
						if(la.get(k).getCodigo().equals(codigos.get(j))) {
							aux.add(la.get(k));	
							enc = true;
						}
						k++;
					}
				}else {
					res.add(aux);
			}
		}
	
			boolean enc = false;;
			int j = 0;
			while(j < la.size() && !enc) {
				if(la.get(j).getCodigo().equals(codigos.get(i))) {
					aux.add(la.get(j));		
					enc = true;
				}
				j++;
			}
			if(i < codigos.size() - 1) {
				if(!cursos.get(i).equals(Integer.parseInt(codigos.get(i+1).toString().substring(0,1)))) {
					res.add(aux);
				}
			}
		}	
		return res;
	}
	
	@Override
	public List<Asignatura> obtenerAsignaturasPorReferencia(List<Integer> referencias) throws SecretariaException{
		List<Asignatura> la = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class).getResultList();
		List<Asignatura> res = new ArrayList<>();
		for(int i = 0; i < referencias.size(); i++) {
			int j = 0;
			boolean enc = false;
			while(j < la.size() && !enc) {
				if(referencias.get(i).equals(la.get(j).getReferencia())) {
					res.add(la.get(j));
					enc = true;
				}
				j++;
			}
		}
		return res;
	}
}