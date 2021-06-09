package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AsignaturaInterface {

	/**
	 * Devuelve una lista de asignaturas de la tabla Asignatura.
	 * @return List<Asignatura>
	 * @throws SecretariaException
	 */
	List<Asignatura> consultarAsignaturas() throws SecretariaException;

	/**
	 * Devuelve la lista de cursos pertenecientes a una lista de codigos de asignaturas.
	 * @param codigos
	 * @return List<Integer>
	 * @throws SecretariaException
	 */
	public List<Integer> obtenerCursos(List<Integer> codigos) throws SecretariaException;

	/**
	 * Devuelve la lista de listas de asignaturas pertenecientes a la lista de codigos de asignaturas.
	 * @param codigos
	 * @return List<List<Asignatura>>
	 * @throws SecretariaException
	 */
	public List<List<Asignatura>> obtenerListaAsignaturas(List<Integer> codigos) throws SecretariaException;

	/**
	 * Devuelve la lista de asignaturas pertenecientes a la lista de referencias.
	 * @param referencias
	 * @return List<Asignatura>
	 * @throws SecretariaException
	 */
	List<Asignatura> obtenerAsignaturasPorReferencia(List<Integer> referencias) throws SecretariaException;
}

