package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AlumnoInterface {

	public void actualizarAlumno(Alumno alumno) throws SecretariaException;
	
}
