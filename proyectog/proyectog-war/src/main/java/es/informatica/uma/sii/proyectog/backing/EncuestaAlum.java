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
import es.uma.informatica.sii.entidades.Titulacion;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.ExpedienteInterface;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="encuestaAlum")
@RequestScoped
public class EncuestaAlum {
	
	@Inject
	private ExpedienteInterface ExpedienteEJB;
	
	private Expediente expediente;
	
	private List<List<Asignatura>> listaAsignaturas;
	
	public EncuestaAlum() {
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
		
		lista1.add(asig1);
		listaAsignaturas.add(lista1);
		
	}
	
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

}
