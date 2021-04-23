package es.uma.informatica.sii.negocio;



import es.uma.informatica.sii.entidades.Encuesta;


public interface AlgoritmoInterfaz {

	/**
	 * Aplica el algoritmo usando el método pasado como parámetro,
	 * a partir de los datos de encuesta y asigna a los alumnos (por expendiente) 
	 * @param metodo
	 * @param encuesta
	 */
	public void aplicarAlgoritmo(int metodo, Encuesta encuesta);
	
}
