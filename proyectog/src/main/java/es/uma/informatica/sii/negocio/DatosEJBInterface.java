package es.uma.informatica.sii.negocio;

import java.io.File;
import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.exceptions.SecretariaException;

public interface DatosEJBInterface {
	
	/**
	 * REQUISITO: RF-08
	 * Importa los datos desde un fichero excel y los almacena en una lista de DatosAlumnado
	 * @param excel
	 * @return
	 */
	public List<DatosAlumnado> importarDatosAlumnado(File excel) throws SecretariaException;
	
	/**
	 * REQUISITO: RF-08
	 * Inserta en la base de datos los DatosAlumnado previamente importados
	 * @param datos
	 */
	public void registrarDatosAlumnado(List<DatosAlumnado> datos) throws SecretariaException; 
	
	/**
	 * REQUISITO: RF-08
	 * Importa las asignaturas desde un fichero excel y las almacena en una lista de Asignatura
	 * @param excel
	 * @return
	 * @throws SecretariaException
	 */
	public List<Asignatura> importarDatosAsignaturas(File excel) throws SecretariaException;

	/**
	 * REQUISITO: RF-08
	 * Inserta en la base de datos las Asignaturas previamente importadas
	 * @param datos
	 * @throws SecretariaException
	 */
	public void registrarDatosAsignaturas(List<Asignatura> asignaturas) throws SecretariaException;
}
