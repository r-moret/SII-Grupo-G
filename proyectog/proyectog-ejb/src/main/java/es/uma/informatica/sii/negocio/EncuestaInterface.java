package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Alumno;
import es.uma.informatica.sii.proyectog.entidades.Encuesta;

@Local
public interface EncuestaInterface {

	/**
	 * REQUISITO: RF-02
	 * 
	 * @param alumno
	 * @param encuesta
	 * @throws SecretariaException
	 */
	public void registrarEncuesta(Alumno alumno, Encuesta encuesta) throws SecretariaException;
	
	public boolean incompatibilidadHoraria(Encuesta encuesta);

}
