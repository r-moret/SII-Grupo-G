package es.uma.informatica.sii.negocio;

import java.io.File;

import es.uma.informatica.sii.entidades.DatosAlumnado;

public interface DatosEJBInterface {
	
	/**REQUISITO: RF-08
	 * Importa los datos desde un fichero excel y los 
	 * almacena DatosAlumnado
	 * @param excel
	 * @return
	 */
	public DatosAlumnado importarDatosAlumnado(File excel);
	
	/**REQUISITO: RF-08
	 * Inserta en la base de datos los DatosAlumnado previamente importados
	 * @param datos
	 */
	public void registrarDatos(DatosAlumnado datos);

}
