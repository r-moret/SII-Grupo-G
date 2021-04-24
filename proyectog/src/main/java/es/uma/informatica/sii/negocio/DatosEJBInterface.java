package es.uma.informatica.sii.negocio;

import java.io.File;
import java.util.List;

import es.uma.informatica.sii.entidades.DatosAlumnado;
import es.uma.informatica.sii.exceptions.SecretariaException;

public interface DatosEJBInterface {
	
	/**REQUISITO: RF-08
	 * Importa los datos desde un fichero excel y los 
	 * almacena DatosAlumnado
	 * @param excel
	 * @return
	 */
	public List<DatosAlumnado> importarDatosAlumnado(File excel) throws SecretariaException;
	
	/**REQUISITO: RF-08
	 * Inserta en la base de datos los DatosAlumnado previamente importados
	 * @param datos
	 */
	public void registrarDatos(List<DatosAlumnado> datos) throws SecretariaException; 

}
