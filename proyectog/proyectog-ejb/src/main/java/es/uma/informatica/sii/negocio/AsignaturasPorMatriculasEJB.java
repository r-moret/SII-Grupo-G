package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class AsignaturasPorMatriculasEJB implements AsignaturasPorMatriculasInterface{

    private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

    
    @Override
    public List<String> obtenerGruposMatriculados(Expediente expediente) throws SecretariaException{
        
		if(expediente == null) {
			//Expediente no existe
			throw new ExpedienteInexistente();
		}
		
		Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
		
        
		List<String> res = new ArrayList<>();
		List<AsignaturasPorMatriculas> lapm = em.createQuery("SELECT apm FROM AsignaturasPorMatriculas apm", AsignaturasPorMatriculas.class).getResultList();

		for(int i = 0;i < lapm.size();i++){
			if(lapm.get(i).getMatricula().getExpediente().equals(e) && !res.contains(lapm.get(i).getGrupo().getId().substring(2,4))){
                res.add(lapm.get(i).getGrupo().getId().substring(2,4));
            }
		}
		
		return res;
    }
}
