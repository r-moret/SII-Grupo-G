package es.uma.informatica.sii.proyectog.entidades;
import java.util.List;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Centro
 *
 */
@Entity
public class Centro implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique = true, nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String direccion;
	private Integer tlfConsejeria;
	
	@ManyToMany(mappedBy = "centros")
	private List<Titulacion> titulaciones;
	
	
	private static final long serialVersionUID = 1L;

	public Centro() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTlfConsejeria() {
		return this.tlfConsejeria;
	}

	public void setTlfConsejeria(Integer tlfConsejeria) {
		this.tlfConsejeria = tlfConsejeria;
	}

	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}

	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((titulaciones == null) ? 0 : titulaciones.hashCode());
		result = prime * result + ((tlfConsejeria == null) ? 0 : tlfConsejeria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Centro))
			return false;
		Centro other = (Centro) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (titulaciones == null) {
			if (other.titulaciones != null)
				return false;
		} else if (!titulaciones.equals(other.titulaciones))
			return false;
		if (tlfConsejeria == null) {
			if (other.tlfConsejeria != null)
				return false;
		} else if (!tlfConsejeria.equals(other.tlfConsejeria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", tlfConsejeria="
				+ tlfConsejeria + ", titulaciones=" + titulaciones + "]";
	}
}
