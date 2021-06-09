package es.informatica.uma.sii.proyectog.backing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.AsignaturasPorMatriculasInterface;
import es.uma.informatica.sii.negocio.ExpedienteInterface;
import es.uma.informatica.sii.negocio.GrupoInterface;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named(value="cambioGrupo")
@RequestScoped
public class CambioGrupo {
	
	@Inject
	private ExpedienteInterface ExpedienteEJB;
	
	@Inject 
	private GrupoInterface GrupoEJB;
	
	@Inject
	private AsignaturasPorMatriculasInterface ApmEJB;
	
	private List<String> listaGruposActual;
	private List<Grupo> listaGruposACambiar;
	private Expediente expediente;
	private String grupoActual;
	private String grupoElegido;
	private Part uploadedFile;
	private String folder = "/home/alumno/Documentos";
	
	public CambioGrupo() {
		expediente = new Expediente();
	}
	
	public List<String> getGruposActual(){
		try {
			listaGruposActual = ApmEJB.obtenerGruposMatriculados(expediente);
			return listaGruposActual;
			
		} catch (SecretariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getGruposACambiar(){
		List<String> res = new ArrayList<>();

		try {
			String grupo="1C";
			listaGruposACambiar = GrupoEJB.consultarGrupos();
			for(int i = 0; i < listaGruposACambiar.size(); i++) {
				if(Integer.parseInt(grupo.substring(0,1)) == (listaGruposACambiar.get(i).getCurso())){
					res.add(listaGruposACambiar.get(i).getCurso() + listaGruposACambiar.get(i).getLetra());
				}
				
			}
			return res;
			
		} catch (SecretariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
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

		try (InputStream input = uploadedFile.getInputStream()) {
			String fileName = uploadedFile.getSubmittedFileName();
	        Files.copy(input, new File(folder, fileName).toPath());
	    }
	    catch (IOException e) {
	        
	    }
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
