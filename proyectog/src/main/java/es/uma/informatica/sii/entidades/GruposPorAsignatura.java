package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: GruposPorAsignatura
 *
 */
@Entity
@IdClass(GruposPorAsignatura.GruposPorAsignaturaId.class)
public class GruposPorAsignatura implements Serializable {

	public static class GruposPorAsignaturaId implements Serializable {
		private static final long serialVersionUID = 1L;
		private String cursoAcademico;
		private Integer asignatura;
		private String grupo;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
			result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
			result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
			GruposPorAsignaturaId other = (GruposPorAsignaturaId) obj;
			if (asignatura == null) {
				if (other.asignatura != null)
					return false;
			} else if (!asignatura.equals(other.asignatura))
				return false;
			if (cursoAcademico == null) {
				if (other.cursoAcademico != null)
					return false;
			} else if (!cursoAcademico.equals(other.cursoAcademico))
				return false;
			if (grupo == null) {
				if (other.grupo != null)
					return false;
			} else if (!grupo.equals(other.grupo))
				return false;
			return true;
		}
	}
	   
	@Id
	private String cursoAcademico;
	private Boolean ofertada;
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Asignatura asignatura;

	@Id
	@ManyToOne
	private Grupo grupo;

	@ManyToMany(mappedBy="gruposPorAsignatura")
	private List<Encuesta> encuesta;

	public GruposPorAsignatura() {
		super();
	}

	public String getCursoAcademico() {
		return this.cursoAcademico;
	}

	public void setCursoAcademico(String cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}
	   
	public Boolean getOfertada() {
		return this.ofertada;
	}

	public void setOfertada(Boolean ofertada) {
		this.ofertada = ofertada;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Encuesta> getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(List<Encuesta> encuesta) {
		this.encuesta = encuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + ((encuesta == null) ? 0 : encuesta.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((ofertada == null) ? 0 : ofertada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GruposPorAsignatura))
			return false;
		GruposPorAsignatura other = (GruposPorAsignatura) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (encuesta == null) {
			if (other.encuesta != null)
				return false;
		} else if (!encuesta.equals(other.encuesta))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (ofertada == null) {
			if (other.ofertada != null)
				return false;
		} else if (!ofertada.equals(other.ofertada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GruposPorAsignatura [cursoAcademico=" + cursoAcademico + ", ofertada=" + ofertada + ", asignatura="
				+ asignatura + ", grupo=" + grupo + ", encuesta=" + encuesta + "]";
	}
   
}
