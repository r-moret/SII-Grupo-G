package es.uma.informatica.sii.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Optativa
 *
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="referencia")
public class Optativa extends Asignatura implements Serializable {
	
	private String mencion;
	private static final long serialVersionUID = 1L;

	public Optativa() {
		super();
	}   
  
	public String getMencion() {
		return this.mencion;
	}

	public void setMencion(String mencion) {
		this.mencion = mencion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mencion == null) ? 0 : mencion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Optativa other = (Optativa) obj;
		if (mencion == null) {
			if (other.mencion != null)
				return false;
		} else if (!mencion.equals(other.mencion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Optativa [mencion=" + mencion + "]";
	}
	
	
	
}
