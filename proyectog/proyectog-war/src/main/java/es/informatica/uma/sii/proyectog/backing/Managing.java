package es.informatica.uma.sii.proyectog.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.negocio.MatriculaInterface;

@Named(value="managing")
@SessionScoped
public class Managing implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static class Row<T>{
		private T object;
		private boolean editable;
		
		public Row(T obj){
			object = obj;
			editable = false;
		}
		
		public T getObject() {
			return object;
		}
		
		public void setObject(T obj) {
			object = obj;
		}
		
		public boolean isEditable() {
			return editable;
		}
		
		public void setEditable(boolean edit) {
			editable = edit;
		}
	}
	
	@Inject
	private MatriculaInterface matriculaEJB;
	
	private List<Row<Matricula>> matriculas;
	private Row<Matricula> matricula;
	
	public Managing() {
		matriculas = new ArrayList<>();
	}
	
	public List<Row<Matricula>> getMatriculas(){
		try {
			List<Matricula> ms = matriculaEJB.consultarMatriculas();
			List<Row<Matricula>> rows = new ArrayList<>();
			
			for(Matricula m : ms) {
				rows.add(new Row<Matricula>(m));
			}
			matriculas = rows;
			
			return matriculas;
		} catch (SecretariaException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return null;
	}
	
	public String editRow() {
		matricula.setEditable(true);
		return null;
	}
	
	public String saveRows() {
		for(Row<Matricula> row : matriculas) {
			row.setEditable(false);
		}
		return null;
	}
	
	public Row<Matricula> getMatricula(){
		return matricula;
	}
	
	public void setMatricula(Row<Matricula> matricula) {
		this.matricula = matricula; 
	}
	
}
