package es.uma.informatica.sii.proyectog.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AsignaturasPorMatriculas
 *
 */
@Entity
@IdClass(AsignaturasPorMatriculas.AsignaturasPorMatriculasId.class)
public class AsignaturasPorMatriculas implements Serializable {

	public static class AsignaturasPorMatriculasId implements Serializable{
		private static final long serialVersionUID = 1L;
		private Matricula.MatriculaId matricula;
		private Integer asignatura;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
			result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
			AsignaturasPorMatriculasId other = (AsignaturasPorMatriculasId) obj;
			if (asignatura == null) {
				if (other.asignatura != null)
					return false;
			} else if (!asignatura.equals(other.asignatura))
				return false;
			if (matricula == null) {
				if (other.matricula != null)
					return false;
			} else if (!matricula.equals(other.matricula))
				return false;
			return true;
		}
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Matricula matricula;
	
	@Id
	@ManyToOne
	private Asignatura asignatura;
	
	@ManyToOne
	private Grupo grupo;
	
	public AsignaturasPorMatriculas() {
		super();
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AsignaturasPorMatriculas))
			return false;
		AsignaturasPorMatriculas other = (AsignaturasPorMatriculas) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AsignaturasPorMatriculas [matricula=" + matricula + ", asignatura=" + asignatura + ", grupo=" + grupo
				+ "]";
	}
   
}
