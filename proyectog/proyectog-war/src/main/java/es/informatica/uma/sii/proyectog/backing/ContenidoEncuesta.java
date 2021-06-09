package es.informatica.uma.sii.proyectog.backing;

import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;

public class ContenidoEncuesta {

	private Integer curso;
	private List<String> listaGrupos;
	private List<Asignatura> listaAsignaturas;
	private List<String> listaGruposIngles;
	private List<String> listaGruposTarde;
	
	public ContenidoEncuesta(Integer curso, List<String> listagrupos, List<Asignatura> listaasignaturas, List<String> listagruposingles, List<String> listagrupostarde) {
		this.curso = curso;
		this.listaGrupos = listagrupos;
		this.listaAsignaturas = listaasignaturas;
		this.listaGruposIngles = listagruposingles;
		this.listaGruposTarde = listagrupostarde;
	}
	
	
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public List<String> getListaGrupos() {
		return listaGrupos;
	}
	public void setListaGrupos(List<String> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}
	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}
	public List<String> getListaGruposIngles() {
		return listaGruposIngles;
	}
	public void setListaGruposIngles(List<String> listaGruposIngles) {
		this.listaGruposIngles = listaGruposIngles;
	}
	public List<String> getListaGruposTarde() {
		return listaGruposTarde;
	}
	public void setListaGruposTarde(List<String> listaGruposTarde) {
		this.listaGruposTarde = listaGruposTarde;
	}
	
	
	
}
