package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Matricula
 *
 */
@Entity
@IdClass(Matricula.MatriculaId.class)
public class Matricula implements Serializable {

	public static class MatriculaId implements Serializable{
		private static final long serialVersionUID = 1L;
		private String cursoAcademico;
		private Integer expediente;
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
	private List<AsignaturasPorMatriculas> asignaturasPorMatriculas;
	
	private static final long serialVersionUID = 1L;

	public Matricula() {
		super();
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
		result = prime * result + ((asignaturasPorMatriculas == null) ? 0 : asignaturasPorMatriculas.hashCode());
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((fechaMatricula == null) ? 0 : fechaMatricula.hashCode());
		result = prime * result + ((listadoAsignaturas == null) ? 0 : listadoAsignaturas.hashCode());
		result = prime * result + ((nuevoIngreso == null) ? 0 : nuevoIngreso.hashCode());
		result = prime * result + ((numArchivo == null) ? 0 : numArchivo.hashCode());
		result = prime * result + ((turnoPreferente == null) ? 0 : turnoPreferente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Matricula))
			return false;
		Matricula other = (Matricula) obj;
		if (asignaturasPorMatriculas == null) {
			if (other.asignaturasPorMatriculas != null)
				return false;
		} else if (!asignaturasPorMatriculas.equals(other.asignaturasPorMatriculas))
			return false;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (fechaMatricula == null) {
			if (other.fechaMatricula != null)
				return false;
		} else if (!fechaMatricula.equals(other.fechaMatricula))
			return false;
		if (listadoAsignaturas == null) {
			if (other.listadoAsignaturas != null)
				return false;
		} else if (!listadoAsignaturas.equals(other.listadoAsignaturas))
			return false;
		if (nuevoIngreso == null) {
			if (other.nuevoIngreso != null)
				return false;
		} else if (!nuevoIngreso.equals(other.nuevoIngreso))
			return false;
		if (numArchivo == null) {
			if (other.numArchivo != null)
				return false;
		} else if (!numArchivo.equals(other.numArchivo))
			return false;
		if (turnoPreferente == null) {
			if (other.turnoPreferente != null)
				return false;
		} else if (!turnoPreferente.equals(other.turnoPreferente))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Matricula [cursoAcademico=" + cursoAcademico + ", estado=" + estado + ", numArchivo=" + numArchivo
				+ ", turnoPreferente=" + turnoPreferente + ", fechaMatricula=" + fechaMatricula + ", nuevoIngreso="
				+ nuevoIngreso + ", listadoAsignaturas=" + listadoAsignaturas + ", expediente=" + expediente
				+ ", asignaturasPorMatriculas=" + asignaturasPorMatriculas + "]";
	}
   
}
