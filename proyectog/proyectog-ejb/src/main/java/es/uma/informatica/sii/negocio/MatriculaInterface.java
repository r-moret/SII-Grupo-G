package es.uma.informatica.sii.negocio;

import es.uma.informatica.sii.proyectog.entidades.Matricula;
import es.uma.informatica.sii.proyectog.entidades.Alumno;
import es.uma.informatica.sii.proyectog.entidades.Asignatura;
import es.uma.informatica.sii.exceptions.SecretariaException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MatriculaInterface {

	/**  
	 *  REQUISITO: RF-01
	 *  Lista el conjunto de matriculas de alumno
	 *  @param alumno 
	 *  @throws SecretariaException
	 */ 
	public List<Matricula> consultarMatricula(Alumno alumno) throws SecretariaException;
	
	/**  
	 *  REQUISITO: RF-01 
	 *  Devuelve la matricula de alumno correspondiente al curso cursoAcademico
	 *  @param alumno 
	 *  @param cursoAcademico 
	 *  @throws SecretariaException
	 */ 
	public Matricula consultarMatricula(Alumno alumno, String cursoAcademico) throws SecretariaException;
	
	/**  
	 * 	REQUISITO: RF-01 
	 *  Lista todo el conjunto de matriculas del curso cursoAcademico
	 *  @param cursoAcademico
	 *  @throws SecretariaException
	 */ 
	public List<Matricula> consultarMatriculas(String cursoAcademico) throws SecretariaException;
	
	/**  
	 * 	REQUISITO: RF-01 
	 *  Lista todo el conjunto de matriculas
	 *  @throws SecretariaException
	 */ 
	public List<Matricula> consultarMatriculas() throws SecretariaException;
	
	/** REQUISITO: RF-09 
	 *  TODO: Actualizar lista de requisitos incluyendolo
	 *  @throws SecretariaException
	 */ 
	public void desmatricularAsignatura(Matricula matricula, Asignatura asignatura) throws SecretariaException;
}
