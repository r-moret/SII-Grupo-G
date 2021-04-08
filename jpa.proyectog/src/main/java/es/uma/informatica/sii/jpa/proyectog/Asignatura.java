package es.uma.informatica.sii.jpa.proyectog;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Asignatura
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Asignatura implements Serializable {

	@Id
	private Integer referencia;
	@Column(nullable = false)
	private Integer codigo;
	@Column(nullable = false)
	private Integer creditos;
	@Column(nullable = false)
	private Boolean ofertada;
	@Column(nullable = false)
	private String nombre;
	private Integer curso;
	private String caracter;
	private Integer duracion;
	private Integer cuatrimestre;
	private String idioma;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion titulacion;

	@OneToMany(mappedBy="asignatura")
	private List<Clase> clases;

	@OneToMany(mappedBy="asignatura")
	private List<GruposPorAsignatura> gruposPorAsignaturas;
	
	@OneToMany(mappedBy="asignatura")
	private List<AsignaturasPorMatriculas> asignaturasPorMatriculas;
	
	private static final long serialVersionUID = 1L;

	public Asignatura() {
		super();
	}

	public Integer getReferencia() {
		return this.referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCreditos() {
		return this.creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Boolean getOfertada() {
		return this.ofertada;
	}

	public void setOfertada(Boolean ofertada) {
		this.ofertada = ofertada;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCurso() {
		return this.curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getCaracter() {
		return this.caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getCuatrimestre() {
		return this.cuatrimestre;
	}

	public void setCuatrimestre(Integer cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public List<GruposPorAsignatura> getGruposPorAsignaturas() {
		return gruposPorAsignaturas;
	}

	public void setGruposPorAsignaturas(List<GruposPorAsignatura> gruposPorAsignaturas) {
		this.gruposPorAsignaturas = gruposPorAsignaturas;
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
		result = prime * result + ((caracter == null) ? 0 : caracter.hashCode());
		result = prime * result + ((clases == null) ? 0 : clases.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((creditos == null) ? 0 : creditos.hashCode());
		result = prime * result + ((cuatrimestre == null) ? 0 : cuatrimestre.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((gruposPorAsignaturas == null) ? 0 : gruposPorAsignaturas.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ofertada == null) ? 0 : ofertada.hashCode());
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asignatura))
			return false;
		Asignatura other = (Asignatura) obj;
		if (asignaturasPorMatriculas == null) {
			if (other.asignaturasPorMatriculas != null)
				return false;
		} else if (!asignaturasPorMatriculas.equals(other.asignaturasPorMatriculas))
			return false;
		if (caracter == null) {
			if (other.caracter != null)
				return false;
		} else if (!caracter.equals(other.caracter))
			return false;
		if (clases == null) {
			if (other.clases != null)
				return false;
		} else if (!clases.equals(other.clases))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (creditos == null) {
			if (other.creditos != null)
				return false;
		} else if (!creditos.equals(other.creditos))
			return false;
		if (cuatrimestre == null) {
			if (other.cuatrimestre != null)
				return false;
		} else if (!cuatrimestre.equals(other.cuatrimestre))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (gruposPorAsignaturas == null) {
			if (other.gruposPorAsignaturas != null)
				return false;
		} else if (!gruposPorAsignaturas.equals(other.gruposPorAsignaturas))
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ofertada == null) {
			if (other.ofertada != null)
				return false;
		} else if (!ofertada.equals(other.ofertada))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
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
		return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos=" + creditos + ", ofertada="
				+ ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", duracion="
				+ duracion + ", cuatrimestre=" + cuatrimestre + ", idioma=" + idioma + ", titulacion=" + titulacion
				+ ", clases=" + clases + ", gruposPorAsignaturas=" + gruposPorAsignaturas
				+ ", asignaturasPorMatriculas=" + asignaturasPorMatriculas + "]";
	}
}
