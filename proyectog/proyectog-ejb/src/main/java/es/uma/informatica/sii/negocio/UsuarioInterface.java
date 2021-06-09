package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Usuario;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface UsuarioInterface {
	
	/**
	 * Devuelve el usuario identificado por su nombre.
	 * @param userName
	 * @return
	 * @throws SecretariaException
	 */
	public Usuario obtenerUsuarioByName(String userName) throws SecretariaException;
	
	/**
	 * Comprueba el acceso de un usuario.
	 * @param user
	 * @throws SecretariaException
	 */
	public void comprobarLogin(Usuario user) throws SecretariaException;
	
}
