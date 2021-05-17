package es.informatica.uma.sii.proyectog.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Usuario;
import es.uma.informatica.sii.exceptions.ContrasenyaUsuarioIncorrecta;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.UsuarioInexistente;
import es.uma.informatica.sii.negocio.UsuarioInterface;

@Named(value="login")
@RequestScoped
public class Login {

	@Inject
	private UsuarioInterface usuarioEJB;
	
	private Usuario user;
	
	public Login() {
		user = new Usuario();
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public String entrar() {
		
		try {
			usuarioEJB.comprobarLogin(user);
			return "faces/welcome.xhtml";
		}
		catch(UsuarioInexistente e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
		}
		catch(ContrasenyaUsuarioIncorrecta e) {
			FacesMessage fm = new FacesMessage("Contraseña incorrecta");
			FacesContext.getCurrentInstance().addMessage("login:password", fm);
		}
		catch(SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
		
	}
	
}
