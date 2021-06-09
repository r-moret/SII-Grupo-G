package es.informatica.uma.sii.proyectog.backing;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.negocio.GrupoInterface;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="inspectGrupo")
@RequestScoped
public class InspectGrupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoInterface grupoEJB;
	
	private Grupo grupo;
	private List<Asignatura> asignaturas;
	private Boolean editable = false;
	
	public String inspect(Grupo grup) {
		setGrupo(grup);
		return "grupo.xhtml";
	}
	
	public String delete() {
		// TODO: Falta implementar el método en EJB
		// grupoEJB.eliminarGrupo(grupo);
		return "managing.xhtml";
	}
	
	public String edit(Grupo grup) {
		// Internal Server Error al ejecutarlo
		setEditable(true);
		return "inspectGrupo.xhtml";
	}
	
	public String save(Grupo grup) {
		// Llamada al EJB para guardar los datos del formulario
		setEditable(false);
		return "inspectGrupo.xhtml";
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public List<Asignatura> getAsignaturas() {
		asignaturas = grupoEJB.asignaturasDeGrupo(grupo);
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
}