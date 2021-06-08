package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AsignaturaInterface {

	List<Asignatura> consultarAsignaturas() throws SecretariaException;

	//documentar
	public List<Integer> obtenerCursos(List<Integer> codigo) throws SecretariaException;

	//documentar
	public List<List<Asignatura>> obtenerListaAsignaturas(List<Integer> codigos) throws SecretariaException;

}
