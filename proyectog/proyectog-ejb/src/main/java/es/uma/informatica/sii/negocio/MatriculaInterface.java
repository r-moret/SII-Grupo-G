package es.uma.informatica.sii.negocio;

import es.uma.informatica.sii.proyectog.entidades.Matricula;
import es.uma.informatica.sii.proyectog.entidades.Alumno;
import es.uma.informatica.sii.proyectog.entidades.Asignatura;
import es.uma.informatica.sii.exceptions.SecretariaException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MatriculaInterface {

	public List<Matricula> consultarMatricula(Alumno alumno) throws SecretariaException;
	
	public List<Matricula> consultarMatricula(Asignatura asignatura) throws SecretariaException;
	
	public List<Matricula> consultarMatriculas() throws SecretariaException;
	
	public void desmatricularAsignatura(Matricula matricula, Asignatura asignatura) throws SecretariaException;
}
