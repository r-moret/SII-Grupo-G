package es.uma.informatica.sii.negocio;

import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;

import java.util.Map;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Grupo;

public interface AlgoritmoSelector {
	
	public Map<Asignatura, Grupo> asignarGrupo(Matricula matricula) throws SecretariaException;
	
}