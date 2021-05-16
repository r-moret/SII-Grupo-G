package es.uma.informatica.sii.entidades;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@IdClass(SolicitudCambioGrupo.SolicitudCambioGrupoID.class)
public class SolicitudCambioGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static class SolicitudCambioGrupoID implements Serializable {

		private static final long serialVersionUID = 1L;
		private Integer expediente;
		private String grupoActual;
		
		public SolicitudCambioGrupoID() {
			super();
		}
		
		public SolicitudCambioGrupoID(Integer expediente, String grupoActual) {
			this.expediente = expediente;
			this.grupoActual = grupoActual;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
			result = prime * result + ((grupoActual == null) ? 0 : grupoActual.hashCode());
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
			SolicitudCambioGrupoID other = (SolicitudCambioGrupoID) obj;
			if (expediente == null) {
				if (other.expediente != null)
					return false;
			} else if (!expediente.equals(other.expediente))
				return false;
			if (grupoActual == null) {
				if (other.grupoActual != null)
					return false;
			} else if (!grupoActual.equals(other.grupoActual))
				return false;
			return true;
		}
	}

	public SolicitudCambioGrupo() {
		super();
		documentacionAportada = new ArrayList<>();
	}
	
	private Timestamp fechaRealizada;
	@ElementCollection(targetClass=File.class)
	private List<File> documentacionAportada;
	
	@Id
	@ManyToOne
	private Expediente expediente;
	
	@Id
	@ManyToOne
	private Grupo grupoActual;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Grupo grupoSolicitado;
	
	
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public Grupo getGrupoActual() {
		return grupoActual;
	}
	public void setGrupoActual(Grupo grupoActual) {
		this.grupoActual = grupoActual;
	}
	public Grupo getGrupoSolicitado() {
		return grupoSolicitado;
	}
	public void setGrupoSolicitado(Grupo grupoSolicitado) {
		this.grupoSolicitado = grupoSolicitado;
	}
	public Timestamp getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(Timestamp fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public List<File> getDocumentacionAportada() {
		return documentacionAportada;
	}
	public void setDocumentacionAportada(List<File> documentacionAportada) {
		this.documentacionAportada = documentacionAportada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((grupoActual == null) ? 0 : grupoActual.hashCode());
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
		SolicitudCambioGrupo other = (SolicitudCambioGrupo) obj;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (grupoActual == null) {
			if (other.grupoActual != null)
				return false;
		} else if (!grupoActual.equals(other.grupoActual))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SolicitudCambioGrupo [fechaRealizada=" + fechaRealizada + ", documentacionAportada="
				+ documentacionAportada + ", expediente=" + expediente + ", grupoActual=" + grupoActual
				+ ", grupoSolicitado=" + grupoSolicitado + "]";
	}	
}
