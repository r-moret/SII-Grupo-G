package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Expendientes
 *
 */
@Entity

public class Expediente implements Serializable {

	   
	@Id
	private Integer numExpediente;
	private Boolean activo;
	private Double notaMediaProvisional;


	@OneToMany(mappedBy="expediente")
	private List<Encuesta> encuestas;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulacion;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Alumno alumno;
	
	@OneToMany(mappedBy="expediente")
	@JoinColumn(nullable=false)
	private List<Matricula> matriculas;
	
	@OneToMany(mappedBy="expediente")
	private List<SolicitudCambioGrupo> solicitudesCambioGrupo;

	@OneToMany(mappedBy="expediente")
	private List<SolicitudCambioGrupo> solicitudesPasadas;
	
	private static final long serialVersionUID = 1L;

	public Expediente() {
		super();
		this.matriculas = new ArrayList<>();
		this.encuestas = new ArrayList<>();
	}   
	
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}   
	public Double getNotaMediaProvisional() {
		return this.notaMediaProvisional;
	}

	public void setNotaMediaProvisional(Double notaMediaProvisional) {
		this.notaMediaProvisional = notaMediaProvisional;
	}
	public Integer getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(Integer numExpediente) {
		this.numExpediente = numExpediente;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public List<SolicitudCambioGrupo> getSolicitudesPasadas() {
		return solicitudesPasadas;
	}
	public void setSolicitudesPasadas(List<SolicitudCambioGrupo> solicitudesPasadas) {
		this.solicitudesPasadas = solicitudesPasadas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numExpediente == null) ? 0 : numExpediente.hashCode());
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
		Expediente other = (Expediente) obj;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expediente [numExpediente=" + numExpediente + ", activo=" + activo + ", notaMediaProvisional="
				+ notaMediaProvisional + ", encuestas=" + encuestas + ", titulacion=" + titulacion + ", alumno="
				+ alumno + ", matriculas=" + matriculas + ", solicitudesCambioGrupo=" + solicitudesCambioGrupo + "]";
	}
	
}
