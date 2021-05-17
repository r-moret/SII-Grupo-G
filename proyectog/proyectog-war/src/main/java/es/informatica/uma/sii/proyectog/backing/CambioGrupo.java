package es.informatica.uma.sii.proyectog.backing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@RequestScoped
public class CambioGrupo {
	private List<SelectItem> listaGrupoUsuario;
	private List<SelectItem> listaGruposCurso;
	
	private String grupoActual;
	private String grupoElegido;
	
	@PostConstruct
	public void init() {
		listaGrupoUsuario = new ArrayList<SelectItem>();
		listaGrupoUsuario.add(new SelectItem(1, "3C"));
		listaGrupoUsuario.add(new SelectItem(2, "2B"));
		
		listaGruposCurso = new ArrayList<SelectItem>();
		listaGruposCurso.add(new SelectItem(1, "1A"));
		listaGruposCurso.add(new SelectItem(2, "3B"));
		
		
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
}
