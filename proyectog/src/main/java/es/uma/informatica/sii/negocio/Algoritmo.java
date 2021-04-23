package es.uma.informatica.sii.negocio;

import java.sql.Timestamp;
import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.exceptions.SecretariaException;

public class Algoritmo implements AlgoritmoInterfaz{

	private int metodo;
	private Encuesta encuesta;
	
	public Algoritmo(int m,Encuesta e, List<GruposPorAsignatura> g) {
		this.metodo = m;
		this.encuesta = e;
	}
	
	public int getMetodo() {
		return metodo;
	}
	
	public void setMetodo(int metodo) {
		this.metodo = metodo;
	}
	
	private Matricula obtenerMatriculaActual(Expediente exp) throws SecretariaException {
		
		Matricula m=exp.getMatriculas().get(exp.getMatriculas().size()-1);
		if(m == null)
			throw new SecretariaException();
		
		return m;
	}
	
	@Override
	public void aplicarAlgoritmo(int m, Encuesta en) throws SecretariaException {
		switch(m){
			case 0:
				//Algoritmo 1: 
				Expediente exp = en.getExpediente();
				boolean procesada= en.getEstadoProcesada();
			
				/*
				BUSCAR MATRICULA (HACE FALTA CURSO ACADEMICO Y EXPEDIENTE)
				CUANDO TENGAMOS LA MATRICULA BUSCAMOS LAS ASIGNATURASPORMATRICULAS  (HACE FALTA MATRICULA Y CADA ASIGNATURA)
				AHÍ EN TEORÍA PODEMOS ASIGNAR AL ALUMNO EL GRUPO DE ESA ASIGNATURA (PORQUE HEMOS LLEGADO DESDE SU EXPEDIENTE) 
				t
				*/
				
				if(!procesada){
					
					Matricula mat = obtenerMatriculaActual(exp);
					Asignatura asignatura;
					Grupo grupo;
									
					for(int i=0;i < en.getGruposPorAsignatura().size();i++){
					
						asignatura = en.getGruposPorAsignatura().get(i).getAsignatura();
						grupo = en.getGruposPorAsignatura().get(i).getGrupo();
						
					 
						mat.getAsignaturasPorMatriculas().get(i).setAsignatura(asignatura);
						mat.getAsignaturasPorMatriculas().get(i).setGrupo(grupo);
					}
					en.setEstadoProcesada(true);
				}	
				break;
			case 1: 
				//Algoritmo 2
				break;
			
			default:
				throw new SecretariaException();
		}
	}

	public boolean comprobarAforo(List<GruposPorAsignatura> gpa) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
