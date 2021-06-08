package es.informatica.uma.sii.proyectog.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="inspectMatricula")
@RequestScoped
public class InspectMatricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MatriculaInterface matriculaEJB;
	
	private Matricula matricula;
	private List<Asignatura> asignaturas;
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
		// Internal Server Error al ejecutarlo
		setEditable(true);
		return "inspectMatricula.xhtml";
	}
	
	public String save(Matricula mat) {
		// Llamada al EJB para guardar los datos del formulario
		setEditable(false);
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

	public List<Asignatura> getAsignaturas() {
		asignaturas = matriculaEJB.asignaturasDeMatricula(matricula);
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
}