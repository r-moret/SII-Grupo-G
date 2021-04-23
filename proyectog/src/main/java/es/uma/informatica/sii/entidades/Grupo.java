package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= {"Curso","Letra"})})
public class Grupo implements Serializable {
	
	@Id @GeneratedValue
	private String id;
	@Column(nullable=false)
	private Integer curso;
	@Column(nullable=false)
	private String letra;
	@Column(nullable=false)
	private String turno;
	@Column(nullable=false)
	private Boolean ingles;
	private Boolean visible;
	private Boolean asignar;
	private Integer plazas;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulacion;

	@OneToMany(mappedBy="grupo")
	private List<GruposPorAsignatura> gruposPorAsignatura;
	
	@OneToMany(mappedBy="grupo")
	private List<Clase> clases;
	
	@ManyToMany
	private List<Grupo> relacionados;
	
	@ManyToMany(mappedBy="relacionados")
	private List<Grupo> relacionadoCon;
	
	@OneToMany(mappedBy="grupo")
	private List<AsignaturasPorMatriculas> asignaturasPorMatriculas;
	
	@OneToMany(mappedBy="grupoActual")
	private List<SolicitudCambioGrupo> solicitudesSalida;
	
	@OneToMany(mappedBy="grupoSolicitado")
	private List<SolicitudCambioGrupo> solicitudesEntrada;
	
	private static final long serialVersionUID = 1L;

	public Grupo() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public Integer getCurso() {
		return this.curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}   
	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}   
	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}   
	public Boolean getIngles() {
		return this.ingles;
	}

	public void setIngles(Boolean ingles) {
		this.ingles = ingles;
	}   
	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}   
	public Boolean getAsignar() {
		return this.asignar;
	}

	public void setAsignar(Boolean asignar) {
		this.asignar = asignar;
	}   
	public Integer getPlazas() {
		return this.plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	public List<GruposPorAsignatura> getGruposPorAsignatura() {
		return gruposPorAsignatura;
	}
	public void setGruposPorAsignatura(List<GruposPorAsignatura> gruposPorAsignatura) {
		this.gruposPorAsignatura = gruposPorAsignatura;
	}
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	
	public List<SolicitudCambioGrupo> getSolicitudesSalida() {
		return solicitudesSalida;
	}
	public void setSolicitudesSalida(List<SolicitudCambioGrupo> solicitudesSalida) {
		this.solicitudesSalida = solicitudesSalida;
	}
	public List<SolicitudCambioGrupo> getSolicitudesEntrada() {
		return solicitudesEntrada;
	}
	public void setSolicitudesEntrada(List<SolicitudCambioGrupo> solicitudesEntrada) {
		this.solicitudesEntrada = solicitudesEntrada;
	}
	public List<Grupo> getRelacionados() {
		return relacionados;
	}
	public void setRelacionados(List<Grupo> relacionados) {
		this.relacionados = relacionados;
	}
	public List<Grupo> getRelacionadoCon() {
		return relacionadoCon;
	}
	public void setRelacionadoCon(List<Grupo> relacionadoCon) {
		this.relacionadoCon = relacionadoCon;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turno=" + turno + ", ingles=" + ingles
				+ ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas + ", titulacion=" + titulacion
				+ ", gruposPorAsignatura=" + gruposPorAsignatura + ", clases=" + clases + ", relacionados="
				+ relacionados + ", relacionadoCon=" + relacionadoCon + ", asignaturasPorMatriculas="
				+ asignaturasPorMatriculas + ", solicitudesSalida=" + solicitudesSalida + ", solicitudesEntrada="
				+ solicitudesEntrada + "]";
	}
}
