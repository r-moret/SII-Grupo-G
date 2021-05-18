package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface ExpedienteInterface {
	
	/**
	 * REQUISITO: RF-09
	 * Actualizacion de los datos de expediente
 
	 * @param expediente
	 * @throws SecretariaException
	 */
	public void actualizarExpediente(Expediente expediente) throws SecretariaException;

	public void comprobarExpediente(Expediente expediente) throws SecretariaException;
	
}
