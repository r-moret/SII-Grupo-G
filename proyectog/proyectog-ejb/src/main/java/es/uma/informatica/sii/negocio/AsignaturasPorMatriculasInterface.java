package es.uma.informatica.sii.negocio;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Local
public interface AsignaturasPorMatriculasInterface {

	//Documentar
	List<String> obtenerGruposMatriculados(Expediente expediente) throws SecretariaException;

}
