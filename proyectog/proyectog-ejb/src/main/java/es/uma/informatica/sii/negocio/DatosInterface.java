package es.uma.informatica.sii.negocio;

import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
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
	
	/**
	 * REQUISITO: RF-08
	 * Importa los expedientes desde un fichero y los almacena en una lista de Expediente
	 * @param fichero
	 * @return
	 * @throws SecretariaException
	 */
	public List<Expediente> importarDatosExpediente(String fichero)  throws SecretariaException;
	
	/**
	 * REQUISITO: RF-08
	 * Inserta en la base de datos los expedientes previamente importados
	 * @param expedientes
	 * @throws SecretariaException
	 */
	public void registrarDatosExpediente(List<Expediente> expedientes) throws SecretariaException;

	/**
	 * REQUISITO: RF-08
	 * Importa los grupos desde un fichero y los almacena en una lista de Grupo
	 * @param ficheroGrupos
	 * @return
	 */
	public List<Grupo> importarDatosGrupos(String ficheroGrupos) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-08
	 * @param grupos
	 * @throws SecretariaException
	 */
	public void registrarDatosGrupos(List<Grupo> grupos) throws SecretariaException;
	
}
