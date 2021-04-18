package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Clase
 *
 */
@Entity
@IdClass(Clase.ClaseId.class)
public class Clase implements Serializable {

	public static class ClaseId implements Serializable{
		private static final long serialVersionUID = 1L;
		private String dia;      
		private String horaInicio;
		private String grupo;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dia == null) ? 0 : dia.hashCode());
			result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
			result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
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
			ClaseId other = (ClaseId) obj;
			if (dia == null) {
				if (other.dia != null)
					return false;
			} else if (!dia.equals(other.dia))
				return false;
			if (grupo == null) {
				if (other.grupo != null)
					return false;
			} else if (!grupo.equals(other.grupo))
				return false;
			if (horaInicio == null) {
				if (other.horaInicio != null)
					return false;
			} else if (!horaInicio.equals(other.horaInicio))
				return false;
			return true;
		}
	}
	
	@Id
	private String dia;
	@Id
	private String horaInicio;
	private String horaFin;
	
	@Id
	@ManyToOne
	private Grupo grupo;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Asignatura asignatura;
	private static final long serialVersionUID = 1L;

	public Clase() {
		super();
	}

	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Clase))
			return false;
		Clase other = (Clase) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (horaFin == null) {
			if (other.horaFin != null)
				return false;
		} else if (!horaFin.equals(other.horaFin))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clase [dia=" + dia + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", grupo=" + grupo
				+ ", asignatura=" + asignatura + "]";
	}

}
