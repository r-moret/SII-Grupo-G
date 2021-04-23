package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
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
	public void asignarGrupos(AlgoritmoSelector selector, Matricula matricula) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Guarda una solicitud de cambio de grupo en la base de datos para poder ser 
	 * consultada mï¿½s tarde por Secretaria
	 * 
	 * @param solicitud
	 * @throws SecretariaException
	 */
	public void registrarSolicitudCambioGrupo(SolicitudCambioGrupo solicitud) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Actualizaciï¿½n del grupo de alumno correspondiente al mismo curso acadï¿½mico que 
	 * grupo
	 * 
	 * @param alumno
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void reasignarGrupo(Expediente expediente, Grupo grupo) throws SecretariaException;
	
	/**
	 * Calcula el número de plazas de un determinado grupo (contando los
	 * grupos que están relacionados con él porque forman un mismo grupo)
	 * 
	 * TODO: Cambio JPA en Asignatura
	 * 		 Corresponde un cambio en el modelo JPA para pasar el atributo plazas
	 * 		 de Optativa a su padre Asignatura. Es necesario para poder comprobar
	 * 		 que una asignación de varios grupos a una asignatura no excede el
	 * 		 aforo máximo
	 * 
	 * @param grupo
	 * @return numPlazas
	 * @throws SecretariaException
	 */
	public Integer plazasTotales(Grupo grupo) throws SecretariaException;
	
	/** 
	 * REQUISITO: RF-09
	 * Actualizaciï¿½n de los datos de grupo
 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
