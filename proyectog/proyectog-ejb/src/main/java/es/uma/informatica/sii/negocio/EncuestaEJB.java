package es.uma.informatica.sii.negocio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Encuesta;
import es.uma.informatica.sii.proyectog.entidades.Expediente;

public class EncuestaEJB implements EncuestaInterface{

	@PersistenceContext(unitName = "ejb-demo")
	private EntityManager em;
	
	@Override
	public void registrarEncuesta(Expediente expediente, Encuesta encuesta) throws SecretariaException {
		Expediente exp = em.find(Expediente.class, expediente.getNumExpediente());
		if(exp == null) {
			// El expediente no existe en la base de datos
			throw new SecretariaException();
		}
		
		/*
		 * Encuesta enc = em.find(Encuesta.class, encuesta.); if(enc == null) {
		 * enc.setExpediente(exp); em.persist(enc); } else if (enc != null) {
		 * 
		 * }
		 */
		
	}

	@Override
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException {
		// TODO Auto-generated method stub
		return false;
	}

}
