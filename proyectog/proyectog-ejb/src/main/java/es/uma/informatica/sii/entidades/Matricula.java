package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Matricula
 *
 */

@NamedQuery(name = "Matricula.todos", query="select m from Matricula m")
@Entity
@IdClass(Matricula.MatriculaId.class)
public class Matricula implements Serializable {

	public static class MatriculaId implements Serializable{
		
		private static final long serialVersionUID = 1L;
		private String cursoAcademico;
		private Integer expediente;
		
		public MatriculaId() {
			
		}
		
		public MatriculaId(String cursoAcademico, Integer expediente) {
			this.cursoAcademico = cursoAcademico;
			this.expediente = expediente;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
			result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MatriculaId other = (MatriculaId) obj;
			if (cursoAcademico == null) {
				if (other.cursoAcademico != null)
					return false;
			} else if (!cursoAcademico.equals(other.cursoAcademico))
				return false;
			if (expediente == null) {
				if (other.expediente != null)
					return false;
			} else if (!expediente.equals(other.expediente))
				return false;
			return true;
		}
		
		
	}
	   
	@Id
	private String cursoAcademico;
	@Column(nullable=false)
	private Boolean estado;
	@Column(nullable=false)
	private Integer numArchivo;
	@Column(nullable=false)
	private String turnoPreferente;
	@Column(nullable=false)
	private Timestamp fechaMatricula;
	private Boolean nuevoIngreso;
	private String listadoAsignaturas;
	
	@Id
	@ManyToOne
	private Expediente expediente;
	
	@OneToMany(mappedBy="matricula")
	private List<AsignaturasPorMatriculas> asignaturasPorMatriculas = new ArrayList<AsignaturasPorMatriculas>();
	
	private static final long serialVersionUID = 1L;

	public Matricula() {
		super();
		asignaturasPorMatriculas = new ArrayList<>();
	}   
	public String getCursoAcademico() {
		return this.cursoAcademico;
	}

	public void setCursoAcademico(String cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}   
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}   
	public Integer getnumArchivo() {
		return this.numArchivo;
	}

	public void setnumArchivo(Integer numArchivo) {
		this.numArchivo = numArchivo;
	}   
	public String getTurnoPreferente() {
		return this.turnoPreferente;
	}

	public void setTurnoPreferente(String turnoPreferente) {
		this.turnoPreferente = turnoPreferente;
	}   
	public Timestamp getFechaMatricula() {
		return this.fechaMatricula;
	}

	public void setFechaMatricula(Timestamp fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}   
	public Boolean getNuevoIngreso() {
		return this.nuevoIngreso;
	}

	public void setNuevoIngreso(Boolean nuevoIngreso) {
		this.nuevoIngreso = nuevoIngreso;
	}   
	public String getListadoAsignaturas() {
		return this.listadoAsignaturas;
	}

	public void setListadoAsignaturas(String listadoAsignaturas) {
		this.listadoAsignaturas = listadoAsignaturas;
	}
	public Integer getNumArchivo() {
		return numArchivo;
	}
	public void setNumArchivo(Integer numArchivo) {
		this.numArchivo = numArchivo;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public List<AsignaturasPorMatriculas> getAsignaturasPorMatriculas() {
		return asignaturasPorMatriculas;
	}
	public void setAsignaturasPorMatriculas(List<AsignaturasPorMatriculas> asignaturasPorMatriculas) {
		this.asignaturasPorMatriculas = asignaturasPorMatriculas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Matricula [cursoAcademico=" + cursoAcademico + ", expediente=" + expediente + "]";
	}

	
	
}
