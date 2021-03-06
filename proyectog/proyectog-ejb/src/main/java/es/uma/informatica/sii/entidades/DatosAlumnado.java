package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DatosAlumnado
 *
 */
@Entity

public class DatosAlumnado implements Serializable {

	   
	@Id
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Integer numExpediente;
	private Integer numArchivo;
	private String emailInstitucional;
	private String emailPersonal;
	private String telefono;
	private String movil;
	private String direccionNotificacion;
	private String localidadNotificacion;
	private String provinciaNotificacion;
	private Integer cpNotificacion;
	private Timestamp fechaMatricula;
	private String turnoPreferente;
	private String gruposAsignados;
	private Double notaMedia;
	private Double creditosSuperados;
	private Double creditosFB;
	private Double creditosOB;
	private Double creditosOP;
	private Double creditosCF;
	private Double creditosPE;
	private Double creditosTF;
	private static final long serialVersionUID = 1L;

	public DatosAlumnado() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Integer getNumExpediente() {
		return numExpediente;
	}

	public void setNumExpediente(Integer numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getEmailInstitucional() {
		return emailInstitucional;
	}

	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDireccionNotificacion() {
		return direccionNotificacion;
	}

	public void setDireccionNotificacion(String direccionNotificacion) {
		this.direccionNotificacion = direccionNotificacion;
	}

	public String getLocalidadNotificacion() {
		return localidadNotificacion;
	}

	public void setLocalidadNotificacion(String localidadNotificacion) {
		this.localidadNotificacion = localidadNotificacion;
	}

	public String getProvinciaNotificacion() {
		return provinciaNotificacion;
	}

	public void setProvinciaNotificacion(String provinciaNotificacion) {
		this.provinciaNotificacion = provinciaNotificacion;
	}

	public Integer getCpNotificacion() {
		return cpNotificacion;
	}

	public void setCpNotificacion(Integer cpNotificacion) {
		this.cpNotificacion = cpNotificacion;
	}

	public Timestamp getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(Timestamp fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	public String getTurnoPreferente() {
		return turnoPreferente;
	}

	public void setTurnoPreferente(String turnoPreferente) {
		this.turnoPreferente = turnoPreferente;
	}

	public String getGruposAsignados() {
		return gruposAsignados;
	}

	public void setGruposAsignados(String gruposAsignados) {
		this.gruposAsignados = gruposAsignados;
	}

	public Double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(Double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public Double getCreditosSuperados() {
		return creditosSuperados;
	}

	public void setCreditosSuperados(Double creditosSuperados) {
		this.creditosSuperados = creditosSuperados;
	}

	public Double getCreditosFB() {
		return creditosFB;
	}

	public void setCreditosFB(Double creditosFB) {
		this.creditosFB = creditosFB;
	}

	public Double getCreditosOB() {
		return creditosOB;
	}

	public void setCreditosOB(Double creditosOB) {
		this.creditosOB = creditosOB;
	}

	public Double getCreditosOP() {
		return creditosOP;
	}

	public void setCreditosOP(Double creditosOP) {
		this.creditosOP = creditosOP;
	}

	public Double getCreditosCF() {
		return creditosCF;
	}

	public void setCreditosCF(Double creditosCF) {
		this.creditosCF = creditosCF;
	}

	public Double getCreditosPE() {
		return creditosPE;
	}

	public void setCreditosPE(Double creditosPE) {
		this.creditosPE = creditosPE;
	}

	public Double getCreditosTF() {
		return creditosTF;
	}

	public void setCreditosTF(Double creditosTF) {
		this.creditosTF = creditosTF;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getNumArchivo() {
		return numArchivo;
	}

	public void setNumArchivo(Integer numArchivo) {
		this.numArchivo = numArchivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result + ((apellido2 == null) ? 0 : apellido2.hashCode());
		result = prime * result + ((cpNotificacion == null) ? 0 : cpNotificacion.hashCode());
		result = prime * result + ((creditosCF == null) ? 0 : creditosCF.hashCode());
		result = prime * result + ((creditosFB == null) ? 0 : creditosFB.hashCode());
		result = prime * result + ((creditosOB == null) ? 0 : creditosOB.hashCode());
		result = prime * result + ((creditosOP == null) ? 0 : creditosOP.hashCode());
		result = prime * result + ((creditosPE == null) ? 0 : creditosPE.hashCode());
		result = prime * result + ((creditosSuperados == null) ? 0 : creditosSuperados.hashCode());
		result = prime * result + ((creditosTF == null) ? 0 : creditosTF.hashCode());
		result = prime * result + ((direccionNotificacion == null) ? 0 : direccionNotificacion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((emailInstitucional == null) ? 0 : emailInstitucional.hashCode());
		result = prime * result + ((emailPersonal == null) ? 0 : emailPersonal.hashCode());
		result = prime * result + ((fechaMatricula == null) ? 0 : fechaMatricula.hashCode());
		result = prime * result + ((gruposAsignados == null) ? 0 : gruposAsignados.hashCode());
		result = prime * result + ((localidadNotificacion == null) ? 0 : localidadNotificacion.hashCode());
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((notaMedia == null) ? 0 : notaMedia.hashCode());
		result = prime * result + ((numArchivo == null) ? 0 : numArchivo.hashCode());
		result = prime * result + ((numExpediente == null) ? 0 : numExpediente.hashCode());
		result = prime * result + ((provinciaNotificacion == null) ? 0 : provinciaNotificacion.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((turnoPreferente == null) ? 0 : turnoPreferente.hashCode());
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
		DatosAlumnado other = (DatosAlumnado) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		if (cpNotificacion == null) {
			if (other.cpNotificacion != null)
				return false;
		} else if (!cpNotificacion.equals(other.cpNotificacion))
			return false;
		if (creditosCF == null) {
			if (other.creditosCF != null)
				return false;
		} else if (!creditosCF.equals(other.creditosCF))
			return false;
		if (creditosFB == null) {
			if (other.creditosFB != null)
				return false;
		} else if (!creditosFB.equals(other.creditosFB))
			return false;
		if (creditosOB == null) {
			if (other.creditosOB != null)
				return false;
		} else if (!creditosOB.equals(other.creditosOB))
			return false;
		if (creditosOP == null) {
			if (other.creditosOP != null)
				return false;
		} else if (!creditosOP.equals(other.creditosOP))
			return false;
		if (creditosPE == null) {
			if (other.creditosPE != null)
				return false;
		} else if (!creditosPE.equals(other.creditosPE))
			return false;
		if (creditosSuperados == null) {
			if (other.creditosSuperados != null)
				return false;
		} else if (!creditosSuperados.equals(other.creditosSuperados))
			return false;
		if (creditosTF == null) {
			if (other.creditosTF != null)
				return false;
		} else if (!creditosTF.equals(other.creditosTF))
			return false;
		if (direccionNotificacion == null) {
			if (other.direccionNotificacion != null)
				return false;
		} else if (!direccionNotificacion.equals(other.direccionNotificacion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (emailInstitucional == null) {
			if (other.emailInstitucional != null)
				return false;
		} else if (!emailInstitucional.equals(other.emailInstitucional))
			return false;
		if (emailPersonal == null) {
			if (other.emailPersonal != null)
				return false;
		} else if (!emailPersonal.equals(other.emailPersonal))
			return false;
		if (fechaMatricula == null) {
			if (other.fechaMatricula != null)
				return false;
		} else if (!fechaMatricula.equals(other.fechaMatricula))
			return false;
		if (gruposAsignados == null) {
			if (other.gruposAsignados != null)
				return false;
		} else if (!gruposAsignados.equals(other.gruposAsignados))
			return false;
		if (localidadNotificacion == null) {
			if (other.localidadNotificacion != null)
				return false;
		} else if (!localidadNotificacion.equals(other.localidadNotificacion))
			return false;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (notaMedia == null) {
			if (other.notaMedia != null)
				return false;
		} else if (!notaMedia.equals(other.notaMedia))
			return false;
		if (numArchivo == null) {
			if (other.numArchivo != null)
				return false;
		} else if (!numArchivo.equals(other.numArchivo))
			return false;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		if (provinciaNotificacion == null) {
			if (other.provinciaNotificacion != null)
				return false;
		} else if (!provinciaNotificacion.equals(other.provinciaNotificacion))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
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
		return "DatosAlumnado [dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", numExpediente=" + numExpediente + ", numArchivo=" + numArchivo
				+ ", emailInstitucional=" + emailInstitucional + ", emailPersonal=" + emailPersonal + ", telefono="
				+ telefono + ", movil=" + movil + ", direccionNotificacion=" + direccionNotificacion
				+ ", localidadNotificacion=" + localidadNotificacion + ", provinciaNotificacion="
				+ provinciaNotificacion + ", cpNotificacion=" + cpNotificacion + ", fechaMatricula=" + fechaMatricula
				+ ", turnoPreferente=" + turnoPreferente + ", gruposAsignados=" + gruposAsignados + ", notaMedia="
				+ notaMedia + ", creditosSuperados=" + creditosSuperados + ", creditosFB=" + creditosFB
				+ ", creditosOB=" + creditosOB + ", creditosOP=" + creditosOP + ", creditosCF=" + creditosCF
				+ ", creditosPE=" + creditosPE + ", creditosTF=" + creditosTF + "]";
	}

	

   
}
