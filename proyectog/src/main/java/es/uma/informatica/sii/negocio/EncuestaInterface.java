package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface EncuestaInterface {

	/**
	 * REQUISITO: RF-02
	 * Inserta la encuesta formalizada por el alumno con el expediente "expediente"
	 *  en la base de datos
	 * @param alumno
	 * @param encuesta
	 * @throws SecretariaException
	 */
	public void registrarEncuesta(Expediente expediente, Encuesta encuesta) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-10
	 * 
	 * Lanza un mensaje si tras comprobar la compatibilidad de horarios insertada en la encuesta, 
	 * hay horarios que se solapan
	 * @param encuesta
	 * @return
	 * @throws SecretariaException
	 */
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException;

}
