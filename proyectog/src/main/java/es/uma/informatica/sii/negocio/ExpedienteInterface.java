package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface ExpedienteInterface {
	
	public void actualizarExpediente(Expediente expediente) throws SecretariaException; 
	
}
