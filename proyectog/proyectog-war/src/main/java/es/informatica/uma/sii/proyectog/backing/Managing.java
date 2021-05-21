package es.informatica.uma.sii.proyectog.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="managing")
@RequestScoped
public class Managing {
	
	@Inject
	private MatriculaInterface matriculaEJB;
	
	private List<Matricula> matriculas;
	
	public Managing() {
		matriculas = new ArrayList<>();
	}
	
	public List<Matricula> getMatriculas(){
		try {
			matriculas = matriculaEJB.consultarMatriculas();
			return matriculas;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}
	
}
