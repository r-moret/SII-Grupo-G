package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface GrupoInterface {

	/**
	 * REQUISITO: RF-03
	 * Aplicar el algoritmo seleccionado a las encuestas realizadas por los alumnos
	 * 
	 * 
	 * @param selector
	 * @throws SecretariaException
	 */
	//duda: falta parámetro encuenta ??
	public void asignarGrupos(Algoritmo selector) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-04
	 * Actualización del grupo de alumno correspondiente al mismo curso académico que 
	 * grupo
	 * 
	 * TODO: Actualización JPA para poder almacenar toda la informacion que requiere 
	 * la solicitud de cambio de grupo que más tarde revisará Secretaría. Se necesita
	 * un método nuevo en GrupoInterface para almacenar estos nuevos datos en la base 
	 * de datos.
	 * 
	 * @param alumno
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void reasignarGrupo(Alumno alumno, Grupo grupo) throws SecretariaException;
	
	/**
	 * TODO
	 */
	public void aforoMaximo() throws SecretariaException;
	
	/** 
	 * REQUISITO: RF-09
	 * Actualizacion de los datos de grupo
 
	 * @param grupo
	 * @throws SecretariaException
	 */
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
