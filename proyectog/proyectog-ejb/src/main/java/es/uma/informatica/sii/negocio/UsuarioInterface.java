package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Usuario;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface UsuarioInterface {
	
	public Usuario obtenerUsuarioByName(String userName) throws SecretariaException;
	public void comprobarLogin(Usuario user) throws SecretariaException;
	
}
