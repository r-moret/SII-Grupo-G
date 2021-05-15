package es.uma.informatica.sii.negocio;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;

import java.util.HashMap;
import java.util.Map;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.AsignacionIndebida;
import es.uma.informatica.sii.exceptions.SecretariaException;

public class PreferenciaSelector implements AlgoritmoSelector {

	@Override
	public Map<Asignatura, Grupo> asignarGrupo(Matricula matricula) throws SecretariaException {
		Expediente ex = matricula.getExpediente();
		String cursoActual = matricula.getCursoAcademico();
		
		Encuesta encuestaActual = null;
		for(Encuesta encuesta : ex.getEncuestas()) {
			Integer anyo = Integer.parseInt(encuesta.getFechaEnvio().toString().substring(2,4));
			String cursoEncuesta = anyo + "/" + (anyo + 1);
			
			if(cursoEncuesta.equals(cursoActual)) {
				encuestaActual = encuesta;
			}
		}
		
		Map<Asignatura, Grupo> asignacion = new HashMap<Asignatura, Grupo>();
		Map<Integer, Grupo> controlCurso = new HashMap<Integer, Grupo>();
		for(GruposPorAsignatura gpa : encuestaActual.getGruposPorAsignatura()) {
			
			/*  "controlCurso" controla que todas las asignaturas de un mismo curso
			 *  solo puedan estar asignadas al mismo grupo, de lo contraria lanza
			 *  la excepción AsignacionIndebida.
			 *  Es un diccionario que almacena para cada curso el grupo que le toca,
			 *  en el momento en el que una preferencia difiere de esto lanza error.
			 */
			if(!controlCurso.containsKey(gpa.getAsignatura().getCurso())) {
				controlCurso.put(gpa.getAsignatura().getCurso(), gpa.getGrupo());
			}
			else {
				if(controlCurso.get(gpa.getAsignatura().getCurso()) != gpa.getGrupo()) {
					throw new AsignacionIndebida();
				}
			}
			
			asignacion.put(gpa.getAsignatura(),	gpa.getGrupo());
		}
		
		return asignacion;
	}
}