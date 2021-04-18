package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface GrupoInterface {
	
	
	
	public void asignarGrupos(Algoritmo selector) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-4
	 * Actualizaci�n del grupo de alumno correspondiente al mismo curso acad�mico que 
	 * grupo
	 * 
	 * TODO: Actualizaci�n JPA para poder almacenar toda la informaci�n que requiere 
	 * la solicitud de cambio de grupo que m�s tarde revisar� Secretar�a. Se necesita
	 * un m�todo nuevo en GrupoInterface para almacenar estos nuevos datos en la base 
	 * de datos.
	 * 
	 * @param alumno
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void reasignarGrupo(Alumno alumno, Grupo grupo) throws SecretariaException;
	
	// TODO public void aforoMaximo()
	
	/** 
	 * REQUISITO: RF-09
	 * 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
