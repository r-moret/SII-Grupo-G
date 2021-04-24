package es.uma.informatica.sii.negocio;

import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.exceptions.SecretariaException;

public interface DatosInterface {
	
	/**
	 * REQUISITO: RF-08
	 * Importa los datos desde un fichero y los almacena en una lista de DatosAlumnado
	 * @param fichero
	 * @return
	 */
	public List<DatosAlumnado> importarDatosAlumnado(String fichero) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-08
	 * Inserta en la base de datos los DatosAlumnado previamente importados
	 * @param datos
	 */
	public void registrarDatosAlumnado(List<DatosAlumnado> datos) throws SecretariaException; 
	
	/**
	 * REQUISITO: RF-08
	 * Importa las asignaturas desde un fichero y las almacena en una lista de Asignatura
	 * @param fichero
	 * @return
	 * @throws SecretariaException
	 */
	public List<Asignatura> importarDatosAsignaturas(String fichero) throws SecretariaException;

	/**
	 * REQUISITO: RF-08
	 * Inserta en la base de datos las Asignaturas previamente importadas
	 * @param asignaturas
	 * @throws SecretariaException
	 */
	public void registrarDatosAsignaturas(List<Asignatura> asignaturas) throws SecretariaException;
}
