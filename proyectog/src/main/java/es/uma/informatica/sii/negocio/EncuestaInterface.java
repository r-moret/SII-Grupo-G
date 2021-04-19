package es.uma.informatica.sii.negocio;

import java.sql.Timestamp;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.EncuestaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface EncuestaInterface {

	/**
	 * Busca en la base de datos una encuesta que coincida con los par�metros 
	 * pasados {@code fechaRealizada} y {@code expediente}.
	 * @param fechaRealizada
	 * @param expediente
	 * @throws EncuestaInexistente
	 * @return
	 */
	public Encuesta obtenerEncuesta(Timestamp fechaRealizada, Expediente expediente) throws EncuestaInexistente;
	
	/**
	 * REQUISITO: RF-02
	 * Inserta la encuesta en la base de datos formalizada por el alumno con el expediente pasado 
	 * por parámetro
	 * @param alumno
	 * @param encuesta
	 * @throws SecretariaException
	 */
	public void registrarEncuesta(Encuesta encuesta) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-06
	 * 
	 * Devuelve True si la encuesta pasada por parámetro presenta alguna incompatibilidad 
	 * entre los horarios de los gruposPorAsignaturas seleccionados
	 * @param encuesta
	 * @return
	 * @throws SecretariaException
	 */
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException;

}
