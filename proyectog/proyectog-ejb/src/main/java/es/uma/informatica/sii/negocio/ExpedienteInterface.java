package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Expediente;

@Local
public interface ExpedienteInterface {
	
	public void actualizarExpediente(Expediente expediente) throws SecretariaException; 
	
}
