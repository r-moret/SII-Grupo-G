package es.informatica.uma.sii.proyectog.backing;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import es.uma.informatica.sii.entidades.Alumno;
import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.AlumnoInterface;
import es.uma.informatica.sii.negocio.AsignaturaInterface;
import es.uma.informatica.sii.negocio.ExpedienteInterface;
import es.uma.informatica.sii.negocio.GrupoInterface;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="managing")
@RequestScoped
public class Managing {
	
	@Inject
	private MatriculaInterface matriculaEJB;
	@Inject 
	private GrupoInterface grupoEJB;
	@Inject 
	private ExpedienteInterface expedienteEJB;
	@Inject
	private AlumnoInterface alumnoEJB;
	@Inject
	private AsignaturaInterface asignaturaEJB;
	
	
	private List<Matricula> matriculas;
	private List<Grupo> grupos;
	private List<Expediente> expedientes;
	private List<Alumno> alumnos;
	private List<Asignatura> asignaturas;
	
	
	public Managing() {
		matriculas = new ArrayList<>();
		grupos = new ArrayList<>();
		expedientes = new ArrayList<>();
		alumnos = new ArrayList<>();
		asignaturas = new ArrayList<>();
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

	public List<Grupo> getGrupos(){
		
		try {
			grupos = grupoEJB.consultarGrupos();
			return grupos;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}	

	public List<Expediente> getExpedientes(){
		
		try {
			expedientes = expedienteEJB.consultarExpedientes();
			return expedientes;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}	

	public List<Alumno> getAlumnos(){
		
		try {
			alumnos = alumnoEJB.consultarAlumnos();
			return alumnos;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}	

	public List<Asignatura> getAsignaturas(){
		
		try {
			asignaturas = asignaturaEJB.consultarAsignaturas();
			return asignaturas;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}	

}
	
