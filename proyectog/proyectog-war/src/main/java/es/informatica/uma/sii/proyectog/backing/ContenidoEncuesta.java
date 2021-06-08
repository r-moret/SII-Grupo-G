package es.informatica.uma.sii.proyectog.backing;

import java.util.List;

public class ContenidoEncuesta {

	private Integer Curso;
	private List<String> listaGrupos;
	private List<String> listaAsignaturas;
	private List<String> listaGruposIngles;
	private List<String> listaGruposTarde;
	
	public ContenidoEncuesta(Integer curso, List<String> listagrupos, List<String> listaasignaturas, List<String> listagruposingles, List<String> listagrupostarde) {
		this.Curso = curso;
		this.listaGrupos = listagrupos;
		this.listaAsignaturas = listaasignaturas;
		this.listaGruposIngles = listagruposingles;
		this.listaGruposTarde = listagrupostarde;
	}
	
	
	public Integer getCurso() {
		return Curso;
	}
	public void setCurso(Integer curso) {
		Curso = curso;
	}
	public List<String> getListaGrupos() {
		return listaGrupos;
	}
	public void setListaGrupos(List<String> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	public List<String> getListaAsignaturas() {
		return listaAsignaturas;
	}
	public void setListaAsignaturas(List<String> listaAsignaturas) {
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
