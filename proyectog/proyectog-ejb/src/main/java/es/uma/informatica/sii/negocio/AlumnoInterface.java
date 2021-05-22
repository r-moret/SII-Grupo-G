package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AlumnoInterface {

	/**
	 * REQUISITO: RF-09
	 * Actualizacion de los datos de alumno
 
	 * @param alumno
	 * @throws SecretariaException
	 */
	public void actualizarAlumno(Alumno alumno) throws SecretariaException;
	
	
	public List<Alumno> consultarAlumnos() throws SecretariaException;
}
