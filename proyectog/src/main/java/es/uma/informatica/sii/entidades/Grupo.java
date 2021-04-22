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
	
	@ManyToOne
	private Grupo grupoComun;
	
	@OneToMany(mappedBy="grupoComun")
	private List<Grupo> unionGrupos;
	
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
	public Grupo getGrupoComun() {
		return grupoComun;
	}
	public void setGrupoComun(Grupo grupoComun) {
		this.grupoComun = grupoComun;
	}
	public List<Grupo> getUnionGrupos() {
		return unionGrupos;
	}
	public void setUnionGrupos(List<Grupo> unionGrupos) {
		this.unionGrupos = unionGrupos;
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
		result = prime * result + ((asignar == null) ? 0 : asignar.hashCode());
		result = prime * result + ((asignaturasPorMatriculas == null) ? 0 : asignaturasPorMatriculas.hashCode());
		result = prime * result + ((clases == null) ? 0 : clases.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((grupoComun == null) ? 0 : grupoComun.hashCode());
		result = prime * result + ((gruposPorAsignatura == null) ? 0 : gruposPorAsignatura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingles == null) ? 0 : ingles.hashCode());
		result = prime * result + ((letra == null) ? 0 : letra.hashCode());
		result = prime * result + ((plazas == null) ? 0 : plazas.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
		result = prime * result + ((unionGrupos == null) ? 0 : unionGrupos.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Grupo))
			return false;
		Grupo other = (Grupo) obj;
		if (asignar == null) {
			if (other.asignar != null)
				return false;
		} else if (!asignar.equals(other.asignar))
			return false;
		if (asignaturasPorMatriculas == null) {
			if (other.asignaturasPorMatriculas != null)
				return false;
		} else if (!asignaturasPorMatriculas.equals(other.asignaturasPorMatriculas))
			return false;
		if (clases == null) {
			if (other.clases != null)
				return false;
		} else if (!clases.equals(other.clases))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (grupoComun == null) {
			if (other.grupoComun != null)
				return false;
		} else if (!grupoComun.equals(other.grupoComun))
			return false;
		if (gruposPorAsignatura == null) {
			if (other.gruposPorAsignatura != null)
				return false;
		} else if (!gruposPorAsignatura.equals(other.gruposPorAsignatura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingles == null) {
			if (other.ingles != null)
				return false;
		} else if (!ingles.equals(other.ingles))
			return false;
		if (letra == null) {
			if (other.letra != null)
				return false;
		} else if (!letra.equals(other.letra))
			return false;
		if (plazas == null) {
			if (other.plazas != null)
				return false;
		} else if (!plazas.equals(other.plazas))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		if (unionGrupos == null) {
			if (other.unionGrupos != null)
				return false;
		} else if (!unionGrupos.equals(other.unionGrupos))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turno=" + turno + ", ingles=" + ingles
				+ ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas + ", titulacion=" + titulacion
				+ ", gruposPorAsignatura=" + gruposPorAsignatura + ", clases=" + clases + ", grupoComun=" + grupoComun
				+ ", unionGrupos=" + unionGrupos + ", asignaturasPorMatriculas=" + asignaturasPorMatriculas + "]";
	}
   
}
