package es.uma.informatica.sii.negocio;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;
import java.util.List;

import javax.ejb.Local;

@Local
public interface MatriculaInterface {

	/**  
	 *  REQUISITO: RF-01
	 *  Lista el conjunto de matriculas de alumno. Si el alumno no tiene matriculas, devuelve una
	 *  lista vací­a
	 *  @param alumno 
	 *  @throws SecretariaException
	 */ 
	public List<Matricula> consultarMatricula(Expediente alumno) throws SecretariaException;
	
	/**  
	 *  REQUISITO: RF-01 
	 *  Devuelve la matricula de alumno correspondiente al curso cursoAcademico
	 *  @param alumno 
	 *  @param cursoAcademico 
	 *  @throws SecretariaException
	 */ 
	public Matricula consultarMatricula(Expediente alumno, String cursoAcademico) throws SecretariaException;
	
	/**  
	 * 	REQUISITO: RF-01 
	 *  Lista todo el conjunto de matriculas
	 *  @throws SecretariaException
	 */ 
	public List<Matricula> consultarMatriculas() throws SecretariaException;
	
	/** 
	 *  REQUISITO: RF-10 
	 *  @throws SecretariaException
	 */ 
	public void desmatricularAsignatura(Matricula matricula, Asignatura asignatura) throws SecretariaException;
	
	/**
	 * Elimina de la base de datos la matricula.
	 * @param matricula
	 */
	public void eliminarMatricula(Matricula matricula);
	
	/**
	 * Devuelve la lista de asignaturas de una matricula.
	 * @param mat
	 * @return List<Asignatura>
	 */
	public List<Asignatura> asignaturasDeMatricula(Matricula mat);

	/**
	 * Devuelve el turnoPreferente especificado en Matricula perteneciente a un alumno
	 * @param alumno
	 * @return String
	 * @throws SecretariaException
	 */
	public String consultarTurnoMatricula(Expediente alumno) throws SecretariaException;

	/**
	 * Devuelve la lista de codigos de asignaturas pertenecientes a la matricula del curso actual de un alumno.
	 * @param alumno
	 * @return List<Integer>
	 * @throws SecretariaException
	 */
	public List<Integer> obtenerCodigosAsignaturasMatricula(Expediente alumno) throws SecretariaException;

}
