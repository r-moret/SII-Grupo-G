package es.informatica.uma.sii.proyectog.backing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Centro;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.ExpedienteInterface;
import es.uma.informatica.sii.negocio.GrupoInterface;
import es.uma.informatica.sii.negocio.MatriculaInterface;
import es.uma.informatica.sii.negocio.AsignaturaInterface;


@Named(value="encuestaAlum")
@RequestScoped
public class EncuestaAlum {
	
	@Inject
	private ExpedienteInterface ExpedienteEJB;
	@Inject 
	private GrupoInterface GrupoEJB;
	@Inject 
	private MatriculaInterface MatriculaEJB;
	@Inject 
	private AsignaturaInterface AsignaturaEJB;

	private Expediente expediente;
	
	private List<ContenidoEncuesta> contenidos;

	private String grupoElegido;
	private List<String> gruposElegidos;
	
	private String grupoElegidoIngles;
	private String grupoElegidoTarde;
	

	private boolean tardeElegida;
	private boolean idiomaElegido;
	
	public EncuestaAlum() {
		
		expediente = new Expediente();
		
		/*
		 * // Grupos
		 * 
		 * List<String> grupo1 = new ArrayList<String>(); grupo1.add("1A");
		 * grupo1.add("1B");
		 * 
		 * List<String> grupo2 = new ArrayList<String>(); grupo2.add("2A");
		 * grupo2.add("2B");
		 * 
		 * // Asignaturas
		 * 
		 * Titulacion tit = new Titulacion(); tit.setCodigo(1);
		 * tit.setNombre("informatica"); tit.setCreditos(1);
		 * 
		 * Centro cent1 = new Centro(); cent1.setId(1); cent1.setNombre("inf");
		 * cent1.setDireccion("dir");
		 * 
		 * List<Centro> listacentro = new ArrayList<Centro>(); listacentro.add(cent1);
		 * tit.setCentros(listacentro);
		 * 
		 * 
		 * List<Asignatura> lista1 = new ArrayList<Asignatura>(); Asignatura asig1 = new
		 * Asignatura(); asig1.setReferencia(1); asig1.setCodigo(1);
		 * asig1.setCreditos(1); asig1.setOfertada(true); asig1.setNombre("Cálculo");
		 * asig1.setPlazas(1); asig1.setTitulacion(tit); asig1.setIdioma("ingles");
		 * 
		 * lista1.add(asig1);
		 * 
		 * 
		 * Asignatura asig3 = new Asignatura(); asig3.setReferencia(3);
		 * asig3.setCodigo(3); asig3.setCreditos(3); asig3.setOfertada(true);
		 * asig3.setNombre("Estadística"); asig3.setPlazas(3); asig3.setTitulacion(tit);
		 * 
		 * lista1.add(asig3);
		 * 
		 * Asignatura asig4 = new Asignatura(); asig4.setReferencia(4);
		 * asig4.setCodigo(4); asig4.setCreditos(4); asig4.setOfertada(true);
		 * asig4.setNombre("Discreta"); asig4.setPlazas(4); asig4.setTitulacion(tit);
		 * asig4.setIdioma("ingles");
		 * 
		 * lista1.add(asig4);
		 * 
		 * 
		 * List<Asignatura> lista2 = new ArrayList<Asignatura>(); Asignatura asig2 = new
		 * Asignatura(); asig2.setReferencia(2); asig2.setCodigo(2);
		 * asig2.setCreditos(2); asig2.setOfertada(true); asig2.setNombre("Java");
		 * asig2.setPlazas(2); asig2.setTitulacion(tit); asig2.setIdioma("ingles");
		 * 
		 * lista2.add(asig2);
		 * 
		 * // GruposIngles
		 * 
		 * List<String> gruposIngles = new ArrayList<String>();
		 * gruposIngles.add("Grupo A"); gruposIngles.add("Grupo B");
		 * 
		 * List<String> gruposTarde = new ArrayList<String>();
		 * gruposTarde.add("Grupo A"); gruposTarde.add("Grupo B");
		 * 
		 * contenidos = new ArrayList<ContenidoEncuesta>(); contenidos.add(new
		 * ContenidoEncuesta(1, grupo1, lista1, gruposIngles, gruposTarde));
		 * contenidos.add(new ContenidoEncuesta(2, grupo2, lista2, gruposIngles,
		 * gruposTarde));
		 */
		
		contenidos = new ArrayList<ContenidoEncuesta>();
		gruposElegidos = new ArrayList<String>();
	
	}
	
	

	public boolean isTardeElegida() {
		return tardeElegida;
	}



	public void setTardeElegida(boolean tardeElegida) {
		this.tardeElegida = tardeElegida;
	}



	public String getGrupoElegidoIngles() {
		return grupoElegidoIngles;
	}
	
	public void setGrupoElegidoIngles(String grupoElegidoIngles) {
		this.grupoElegidoIngles = grupoElegidoIngles;
	}

	
	public List<ContenidoEncuesta> getContenidos() {
		
		try {
			Matricula m = MatriculaEJB.consultarMatricula(expediente, MatriculaEJB.obtenerCursoActual());
			List<Asignatura> todasAsignaturas = MatriculaEJB.asignaturasDeMatricula(m);
			List<Integer> cursos = AsignaturaEJB.obtenerCursos(todasAsignaturas);
			
			List<Asignatura> asigPorCurso = new ArrayList<Asignatura>();
			List<String> grupPorCurso = new ArrayList<String>();
			List<String> grupoIngles = new ArrayList<String>();
			List<String> grupoTarde = new ArrayList<String>();
			
			for (Integer curso : cursos) {
				
				for(Asignatura asig : todasAsignaturas){
					if(asig.getCurso().equals(curso)) asigPorCurso.add(asig);
				}
				
				contenidos.add(new ContenidoEncuesta(curso, grupPorCurso, asigPorCurso, grupoIngles, grupoTarde));
				asigPorCurso.removeAll(asigPorCurso);
			}
			
		} catch (SecretariaException e) {
			
			e.printStackTrace();
		}
		
		return contenidos;
	}



	public void setContenidos(List<ContenidoEncuesta> contenidos) {
		this.contenidos = contenidos;
	}





	public boolean isIdiomaElegido() {
		return idiomaElegido;
	}



	public void setIdiomaElegido(boolean idiomaElegido) {
		this.idiomaElegido = idiomaElegido;
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


	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	
	
	public String entrar() {
		
		try {
			ExpedienteEJB.comprobarExpediente(expediente);
			return "survey.xhtml";
		}
		catch(ExpedienteInexistente e) {
			FacesMessage fm = new FacesMessage("El expediente no existe");
            FacesContext.getCurrentInstance().addMessage("loginSurvey:expedienteSurvey", fm);
		}

		catch(SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		catch(Exception e) {}
		return null;
		
	}



	public String getGrupoElegidoTarde() {
		return grupoElegidoTarde;
	}



	public void setGrupoElegidoTarde(String grupoElegidoTarde) {
		this.grupoElegidoTarde = grupoElegidoTarde;
	}

}
