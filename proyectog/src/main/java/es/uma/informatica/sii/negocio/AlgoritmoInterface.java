package es.uma.informatica.sii.negocio;



import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.exceptions.SecretariaException;


public interface AlgoritmoInterface {

	/**
	 * Aplica el algoritmo usando el m�todo pasado como par�metro,
	 * a partir de los datos de encuesta y asigna a los alumnos (por expendiente) 
	 * @param metodo
	 * @param encuesta
	 */
	public void aplicarAlgoritmo(int metodo, Encuesta encuesta) throws SecretariaException ;
	
}
