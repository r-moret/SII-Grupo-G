package es.informatica.uma.sii.proyectog.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Matricula;

@Named(value="inspectMatricula")
@RequestScoped
public class InspectMatricula {

	private Matricula matricula;
	
	public String inspect(Matricula mat) {
		matricula = mat;
		return "inspectMatricula.xhtml";
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
}