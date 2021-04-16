package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Alumno;

@Local
public interface AlumnoInterface {

	/*
	 * 
	 */
	public void actualizarAlumno(Alumno alumno) throws SecretariaException;
	
}
