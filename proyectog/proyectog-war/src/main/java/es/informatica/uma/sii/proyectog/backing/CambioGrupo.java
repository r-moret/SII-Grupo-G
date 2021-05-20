package es.informatica.uma.sii.proyectog.backing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.ExpedienteInterface;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="cambioGrupo")
@RequestScoped
public class CambioGrupo {
	
	@Inject
	private ExpedienteInterface ExpedienteEJB;
	
	private List<SelectItem> listaGrupoUsuario;
	private List<SelectItem> listaGruposCurso;
	
	private Expediente expediente;
	
	private String grupoActual;
	private String grupoElegido;
	private File uploadedFile;
	
	public CambioGrupo() {
		listaGrupoUsuario = new ArrayList<SelectItem>();
		listaGrupoUsuario.add(new SelectItem(1, "3C"));
		listaGrupoUsuario.add(new SelectItem(2, "2B"));
		
		listaGruposCurso = new ArrayList<SelectItem>();
		listaGruposCurso.add(new SelectItem(1, "1A"));
		listaGruposCurso.add(new SelectItem(2, "3B"));
		
		expediente = new Expediente();

	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public List<SelectItem> getListaGrupoUsuario() {
		return listaGrupoUsuario;
	}
	public void setListaGrupoUsuario(List<SelectItem> listaGrupoUsuario) {
		this.listaGrupoUsuario = listaGrupoUsuario;
	}
	public List<SelectItem> getListaGruposCurso() {
		return listaGruposCurso;
	}
	public void setListaGruposCurso(List<SelectItem> listaGruposCurso) {
		this.listaGruposCurso = listaGruposCurso;
	}
	public String getGrupoActual() {
		return grupoActual;
	}
	public void setGrupoActual(String grupoActual) {
		this.grupoActual = grupoActual;
	}
	public String getGrupoElegido() {
		return grupoElegido;
	}
	public void setGrupoElegido(String grupoElegido) {
		this.grupoElegido = grupoElegido;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void upload() {
	  // subir archivo
	}
	
	public String enviar() {
		// Faltaria hacer comprobaciones etc
		return "welcome.xhtml";
	}
	
	public String entrar() {
		
		try {
			ExpedienteEJB.comprobarExpediente(expediente);
			return "changeRequest.xhtml";
		}
		catch(ExpedienteInexistente e) {
			FacesMessage fm = new FacesMessage("El expediente no existe");
            FacesContext.getCurrentInstance().addMessage("login:expediente", fm);
		}

		catch(SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		catch(Exception e) {}
		
		return null;
		
	}
	
}
