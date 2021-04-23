package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface GrupoInterface {

	/**
	 * REQUISITO: RF-03
	 * Aplicar el algoritmo seleccionado a cada encuesta realizada por los alumnos
	 * 
	 * 
	 * @param selector
	 * @param encuesta
	 * @throws SecretariaException
	 */
	public void asignarGrupos(Algoritmo selector, Encuesta encuesta) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Guarda una solicitud de cambio de grupo en la base de datos para poder ser 
	 * consultada m�s tarde por Secretaria
	 * 
	 * @param solicitud
	 * @throws SecretariaException
	 */
	public void registrarSolicitudCambioGrupo(SolicitudCambioGrupo solicitud) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Actualizaci�n del grupo de alumno correspondiente al mismo curso acad�mico que 
	 * grupo
	 * 
	 * @param alumno
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void reasignarGrupo(Expediente expediente, Grupo grupo) throws SecretariaException;
	
	/**
	 * Calcula el n�mero de plazas de un determinado grupo (contando los
	 * grupos que est�n relacionados con �l porque forman un mismo grupo)
	 * 
	 * TODO: Cambio JPA en Asignatura
	 * 		 Corresponde un cambio en el modelo JPA para pasar el atributo plazas
	 * 		 de Optativa a su padre Asignatura. Es necesario para poder comprobar
	 * 		 que una asignaci�n de varios grupos a una asignatura no excede el
	 * 		 aforo m�ximo
	 * 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void plazasTotales(Grupo grupo) throws SecretariaException;
	
	/** 
	 * REQUISITO: RF-09
	 * Actualizaci�n de los datos de grupo
 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
