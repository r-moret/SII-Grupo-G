package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
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
		asignaturasPorMatriculas = new ArrayList<>();
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
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
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
		Asignatura other = (Asignatura) obj;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
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
