package es.informatica.uma.sii.proyectog.backing;

import java.util.ArrayList;
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
	
	//private List<List<String>> grupos;
	private Expediente expediente;
	
	// Lista grande donde cada fila es un curso donde dentro contiene las asignaturas de ese curso
	private List<List<Asignatura>> listaAsignaturas;
	
	//private List<String> cursos;
	//private int indexCurso;
	
	private String grupoElegido;
	private List<String> gruposElegidos;
	
	private List<List<String>> grupos;
	private int indexGrupos;
	
	private String grupoElegidoIngles;
	private String grupoElegidoTarde;
	
	private List<String> gruposIngles;
	
	private List<String> gruposTardes;

	private boolean tardeElegida;
	private boolean idiomaElegido;
	
	public EncuestaAlum() {
		
		//grupos = new ArrayList<>();
		
		gruposIngles = new ArrayList<String>();
		gruposIngles.add("Grupo A");
		gruposIngles.add("Grupo B");
		
		//cursos = new ArrayList<String>();
		//cursos.add("1");
		//cursos.add("2");
		//indexCurso = 0;
		
		gruposElegidos = new ArrayList<String>();
		
		grupos = new ArrayList<List<String>>();
		/*indexGrupos = 0;
		List<String> grupo1 = new ArrayList<String>();
		grupo1.add("1A");
		grupo1.add("1B");
		
		List<String> grupo2 = new ArrayList<String>();
		grupo2.add("2A");
		grupo2.add("2B");
		
		grupos.add(grupo1);
		grupos.add(grupo2);*/
		
		
		
		
		expediente = new Expediente();
		listaAsignaturas = new ArrayList<List<Asignatura>>();
		
		
		
		Titulacion tit = new Titulacion();
		tit.setCodigo(1);
		tit.setNombre("informatica");
		tit.setCreditos(1);
		
		Centro cent1 = new Centro();
		cent1.setId(1);
		cent1.setNombre("inf");
		cent1.setDireccion("dir");
		
		List<Centro> listacentro = new ArrayList<Centro>();
		listacentro.add(cent1);
		tit.setCentros(listacentro);
		
		
		List<Asignatura> lista1 = new ArrayList<Asignatura>();
		Asignatura asig1 = new Asignatura();
		asig1.setReferencia(1);
		asig1.setCodigo(1);
		asig1.setCreditos(1);
		asig1.setOfertada(true);
		asig1.setNombre("Cálculo");
		asig1.setPlazas(1);
		asig1.setTitulacion(tit);
		asig1.setIdioma("ingles");
		
		lista1.add(asig1);
		
		
		Asignatura asig3 = new Asignatura();
		asig3.setReferencia(3);
		asig3.setCodigo(3);
		asig3.setCreditos(3);
		asig3.setOfertada(true);
		asig3.setNombre("Estadística");
		asig3.setPlazas(3);
		asig3.setTitulacion(tit);
		
		lista1.add(asig3);
		
		Asignatura asig4 = new Asignatura();
		asig4.setReferencia(4);
		asig4.setCodigo(4);
		asig4.setCreditos(4);
		asig4.setOfertada(true);
		asig4.setNombre("Discreta");
		asig4.setPlazas(4);
		asig4.setTitulacion(tit);
		asig4.setIdioma("ingles");
		
		lista1.add(asig4);
		
		listaAsignaturas.add(lista1);
		
		List<Asignatura> lista2 = new ArrayList<Asignatura>();
		Asignatura asig2 = new Asignatura();
		asig2.setReferencia(2);
		asig2.setCodigo(2);
		asig2.setCreditos(2);
		asig2.setOfertada(true);
		asig2.setNombre("Java");
		asig2.setPlazas(2);
		asig2.setTitulacion(tit);
		asig2.setIdioma("ingles");
		
		lista2.add(asig2);
		listaAsignaturas.add(lista2);
		
	}
	
	

	public boolean isTardeElegida() {
		return tardeElegida;
	}



	public void setTardeElegida(boolean tardeElegida) {
		this.tardeElegida = tardeElegida;
	}



	public List<String> getGruposIngles() {
		return gruposIngles;
	}



	public void setGruposIngles(List<String> gruposIngles) {
		this.gruposIngles = gruposIngles;
	}



	public String getGrupoElegidoIngles() {
		return grupoElegidoIngles;
	}



	public void setGrupoElegidoIngles(String grupoElegidoIngles) {
		this.grupoElegidoIngles = grupoElegidoIngles;
	}

	public boolean isIdiomaElegido() {
		return idiomaElegido;
	}



	public void setIdiomaElegido(boolean idiomaElegido) {
		this.idiomaElegido = idiomaElegido;
	}


	public List<String> getGruposTardes(){
		return gruposTardes;		
	}

	public List<List<String>> getGrupos(){
		
		
		//List<String> res= new ArrayList<>();
		List<Integer> cursos = getCursos();
		Integer numE = expediente.getNumExpediente();
		try {
			grupos = GrupoEJB.consultarGrupos(numE, cursos);
			
		
		//	for(int i=0; i<grupos.size();i++){
		//		res.add(grupos.get(i).getLetra());
		//	}
			
			return grupos;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;

	}
	/*

	public List<String> getGrupos() {
		List<String> res =  new ArrayList<String>();
		res = grupos.get(indexGrupos);
		indexGrupos++;
		return res;
	}
*/
	/* 
	public void setGrupos(List<List<Grupo>> grupos) {
		this.grupos = grupos;
	}
*/
	public String getGrupoElegido() {
		return grupoElegido;
	}
	
	public void setGruposTardes(List<String> grupos) {
		this.gruposTardes = grupos;
	}

	public void setGrupoElegido(String grupoElegido) {
		
		this.grupoElegido = grupoElegido;
		gruposElegidos.add(grupoElegido);
	}

	public List<Integer> getCursos() {
		List<Integer> codigos = new ArrayList<>();
		List<Integer> cursos = new ArrayList<>();
		try {
			codigos = MatriculaEJB.obtenerCodigosAsignaturasMatricula(expediente);
			cursos = AsignaturaEJB.obtenerCursos(codigos);
			return cursos;
		} catch (SecretariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/*
	public void setCursos(List<String> cursos) {
		this.cursos = cursos;
	}
	*/
	public List<List<Asignatura>> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<List<Asignatura>> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
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
