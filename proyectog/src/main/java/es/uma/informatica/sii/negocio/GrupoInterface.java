package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface GrupoInterface {

	/**
	 * REQUISITO: RF-03
	 * Aplicar el algoritmo seleccionado a las encuestas realizadas por los alumnos
	 * 
	 * 
	 * @param selector
	 * @throws SecretariaException
	 */
	public void asignarGrupos(Algoritmo selector) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Guarda una solicitud de cambio de grupo en la base de datos para poder ser 
	 * consultada más tarde por Secretaria
	 * 
	 * @param solicitud
	 * @throws SecretariaException
	 */
	public void registrarSolicitudCambioGrupo(SolicitudCambioGrupo solicitud) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Actualización del grupo de alumno correspondiente al mismo curso académico que 
	 * grupo
	 * 
	 * @param alumno
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void reasignarGrupo(Expediente expediente, Grupo grupo) throws SecretariaException;
	
	/**
	 * TODO
	 */
	public void aforoMaximo() throws SecretariaException;
	
	/** 
	 * REQUISITO: RF-09
	 * Actualización de los datos de grupo
 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
