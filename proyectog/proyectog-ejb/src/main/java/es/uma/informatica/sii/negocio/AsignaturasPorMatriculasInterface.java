package es.uma.informatica.sii.negocio;

import java.util.List;
import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AsignaturasPorMatriculasInterface {

	/**
	 * Devuelve la lista de grupos (curso y letra) en las que está matriculado el alumno identificado por el expediente.
	 * @param expediente
	 * @return List<String>
	 * @throws SecretariaException
	 */
	List<String> obtenerGruposMatriculados(Expediente expediente) throws SecretariaException;

	/**
	 * Devuelve la lista de referencias de asignaturas en las que está matriculado el alumno identificado por el expediente.
	 * @param expediente
	 * @return List<Integer>
	 * @throws SecretariaException
	 */
	public List<Integer> obtenerReferenciaMatriculados(Expediente expediente) throws SecretariaException;
}
