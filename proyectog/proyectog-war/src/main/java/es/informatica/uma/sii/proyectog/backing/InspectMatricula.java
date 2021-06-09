package es.informatica.uma.sii.proyectog.backing;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.AsignaturaInterface;
import es.uma.informatica.sii.negocio.AsignaturasPorMatriculasInterface;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="inspectMatricula")
@RequestScoped
public class InspectMatricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MatriculaInterface MatriculaEJB;
	
	@Inject
	private AsignaturaInterface AsignaturaEJB;
	
	@Inject
	private AsignaturasPorMatriculasInterface ApmEJB;


	
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
		MatriculaEJB.eliminarMatricula(matricula);
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
	
	public List<Asignatura> getAsignaturas() {
		List<Asignatura> listaAsignaturas;
		List<Integer> listaReferencias;
		try {
			listaReferencias = ApmEJB.obtenerReferenciaMatriculados(getMatricula().getExpediente());
			listaAsignaturas = AsignaturaEJB.obtenerAsignaturasPorReferencia(listaReferencias);
			return listaAsignaturas;
		} catch (SecretariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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