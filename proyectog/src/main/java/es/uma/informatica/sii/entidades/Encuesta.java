package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Encuesta
 *
 */
@Entity
@IdClass(Encuesta.EncuestaID.class)
public class Encuesta implements Serializable {
	
	public static class EncuestaID implements Serializable{
		private static final long serialVersionUID = 1L;
		private Timestamp fechaEnvio;
		private Integer expediente;
		
		public EncuestaID() {
			
		}
		
		public EncuestaID(Timestamp fechaEnvio, Integer expediente) {
			this.fechaEnvio = fechaEnvio;
			this.expediente = expediente;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
			result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
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
			EncuestaID other = (EncuestaID) obj;
			if (expediente == null) {
				if (other.expediente != null)
					return false;
			} else if (!expediente.equals(other.expediente))
				return false;
			if (fechaEnvio == null) {
				if (other.fechaEnvio != null)
					return false;
			} else if (!fechaEnvio.equals(other.fechaEnvio))
				return false;
			return true;
		}
		
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	private Timestamp fechaEnvio;

	@Id
	@ManyToOne
	@JoinColumn(nullable=false)
	private Expediente expediente;

	@ManyToMany
	private List<GruposPorAsignatura> gruposPorAsignatura;

	public Encuesta() {
		super();
	}
	  
	public Timestamp getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public List<GruposPorAsignatura> getGruposPorAsignatura() {
		return gruposPorAsignatura;
	}

	public void setGruposPorAsignatura(List<GruposPorAsignatura> gruposPorAsignatura) {
		this.gruposPorAsignatura = gruposPorAsignatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
		result = prime * result + ((gruposPorAsignatura == null) ? 0 : gruposPorAsignatura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Encuesta))
			return false;
		Encuesta other = (Encuesta) obj;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		if (gruposPorAsignatura == null) {
			if (other.gruposPorAsignatura != null)
				return false;
		} else if (!gruposPorAsignatura.equals(other.gruposPorAsignatura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Encuesta [fechaEnvio=" + fechaEnvio + ", expediente=" + expediente + ", gruposPorAsignatura="
				+ gruposPorAsignatura + "]";
	}
}
