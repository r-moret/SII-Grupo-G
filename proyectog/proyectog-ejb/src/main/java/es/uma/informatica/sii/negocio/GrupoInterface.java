package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Asignatura;
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
	 * @param grupo
	 * @return numPlazas
	 * @throws SecretariaException
	 */
	public Integer plazasTotales(Grupo grupo) throws SecretariaException;
	
	/** 
	 * REQUISITO: RF-09
	 * Actualización de los datos de grupo
 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
	/**
	 * Devuelve la lista de grupos pertenecientes a la tabla Grupo
	 * @return List<Grupo>
	 * @throws SecretariaException
	 */
	public List<Grupo> consultarGrupos() throws SecretariaException;

	/**
	 * Devuelve la lista de listas de grupos (letra) dados un alumno identificado por su nº de expediente y una lista de cursos de asignaturas.
	 * @param expediente
	 * @param curso
	 * @return List<List<String>>
	 * @throws SecretariaException
	 */
	public List<List<String>> consultarGrupos(Integer expediente, List<Integer> curso) throws SecretariaException;

	/**
	 * Devuelve la lista de grupos en inglés pertenecientes a un expediente y un curso.
	 * @param expediente
	 * @param curso
	 * @param ingles
	 * @return List<Grupo>
	 * @throws SecretariaException
	 */
	public List<Grupo> consultarGrupos(Expediente expediente, int curso, boolean ingles) throws SecretariaException;

	/**
	 * Devuelve la lista de grupos de tarde pertenecientes a un expediente y un curso.
	 * @param expediente
	 * @param curso
	 * @param ingles
	 * @param tarde
	 * @return List<Grupo>
	 * @throws SecretariaException
	 */
	public List<Grupo> consultarGrupos(Expediente expediente, int curso, boolean ingles, String tarde) throws SecretariaException;
	
	public List<Asignatura> asignaturasDeGrupo(Grupo grupo);
}
