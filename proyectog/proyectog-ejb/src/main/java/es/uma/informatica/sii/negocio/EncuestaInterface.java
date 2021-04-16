package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Alumno;
import es.uma.informatica.sii.proyectog.entidades.Encuesta;
import es.uma.informatica.sii.proyectog.entidades.Expediente;

@Local
public interface EncuestaInterface {

	/**
	 * REQUISITO: RF-02
	 * Inserta la encuesta formalizada por el alumno en la base de datos
	 * @param alumno
	 * @param encuesta
	 * @throws SecretariaException
	 */
	public void registrarEncuesta(Expediente expediente, Encuesta encuesta) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-10
	 * 
	 * @param encuesta
	 * @return
	 * @throws SecretariaException
	 */
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException;

}
