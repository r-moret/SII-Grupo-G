package es.informatica.uma.sii.proyectog.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="inspectMatricula")
@RequestScoped
public class InspectMatricula {

	@Inject
	private MatriculaInterface matriculaEJB;
	
	private Matricula matricula;
	private String nombre;
	private Boolean editable = false;
	
	public String inspect(Matricula mat) {
		matricula = mat;
		setNombre(mat.getExpediente().getAlumno().getNombreCompleto());
		return "inspectMatricula.xhtml";
	}
	
	public String delete() {
		// Internal Server Error al ejecutarlo
		matriculaEJB.eliminarMatricula(matricula);
		return "managing.xhtml";
	}
	
	public String edit(Matricula mat) {
		// Se borran los datos al recargar la página con los campos editables
		setEditable(true);
		matricula = mat;
		return "inspectMatricula.xhtml";
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	
}