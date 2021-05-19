package es.uma.informatica.sii.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class ExpedienteEJB implements ExpedienteInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;


	@Override
	public void actualizarExpediente(Expediente expediente) throws SecretariaException {
		if(expediente == null) {
			//Expediente no existe
			throw new SecretariaException();
		}
		
		Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
		
		if(e == null) {
			//Epediente no existe en la BBDD
			throw new ExpedienteInexistente();
		}

		em.merge(expediente);	
		
	}
	
	@Override
	public void comprobarExpediente(Expediente expediente) throws SecretariaException{
		if(expediente == null) {
			//Expediente no existe
			throw new SecretariaException();
		}
		Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
		
		if(e == null) {
			//Epediente no existe en la BBDD
			throw new ExpedienteInexistente();
		}
		
	}

}
