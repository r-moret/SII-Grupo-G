package es.uma.informatica.sii.jpa.proyectog;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
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
	private Float notaMediaProvisional;

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
	
	private static final long serialVersionUID = 1L;

	public Expediente() {
		super();
	}   
	
	public Integer getNumExpendiente() {
		return this.numExpediente;
	}

	public void setNumExpendiente(Integer numExpediente) {
		this.numExpediente = numExpediente;
	}   
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}   
	public Float getNotaMediaProvisional() {
		return this.notaMediaProvisional;
	}

	public void setNotaMediaProvisional(Float notaMediaProvisional) {
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
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + ((encuestas == null) ? 0 : encuestas.hashCode());
		result = prime * result + ((matriculas == null) ? 0 : matriculas.hashCode());
		result = prime * result + ((notaMediaProvisional == null) ? 0 : notaMediaProvisional.hashCode());
		result = prime * result + ((numExpediente == null) ? 0 : numExpediente.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Expediente))
			return false;
		Expediente other = (Expediente) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (encuestas == null) {
			if (other.encuestas != null)
				return false;
		} else if (!encuestas.equals(other.encuestas))
			return false;
		if (matriculas == null) {
			if (other.matriculas != null)
				return false;
		} else if (!matriculas.equals(other.matriculas))
			return false;
		if (notaMediaProvisional == null) {
			if (other.notaMediaProvisional != null)
				return false;
		} else if (!notaMediaProvisional.equals(other.notaMediaProvisional))
			return false;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expediente [numExpediente=" + numExpediente + ", activo=" + activo + ", notaMediaProvisional="
				+ notaMediaProvisional + ", encuestas=" + encuestas + ", titulacion=" + titulacion + ", alumno="
				+ alumno + ", matriculas=" + matriculas + "]";
	}
}
