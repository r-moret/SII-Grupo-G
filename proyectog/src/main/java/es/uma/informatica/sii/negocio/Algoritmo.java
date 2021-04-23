package es.uma.informatica.sii.negocio;

import java.sql.Timestamp;
import java.util.List;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;

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
	
	@Override
	public void aplicarAlgoritmo(int m, Encuesta en) {
		switch(m){
			case 0:
				//Algoritmo 1: 
				Expediente exp = en.getExpediente();
				Timestamp fecha = en.getFechaEnvio();
				boolean procesada= en.getEstadoProcesada();
				
				/*
				buscarMatricula(){
					Matricula mat = exp.getMatriculas()...
				}
				
				BUSCAR MATRICULA (HACE FALTA CURSO ACADEMICO Y EXPEDIENTE)
				CUANDO TENGAMOS LA MATRICULA BUSCAMOS LAS ASIGNATURASPORMATRICULAS  (HACE FALTA MATRICULA Y CADA ASIGNATURA)
				AHÍ EN TEORÍA PODEMOS ASIGNAR AL ALUMNO EL GRUPO DE ESA ASIGNATURA (PORQUE HEMOS LLEGADO DESDE SU EXPEDIENTE) 
				*/
				
				
				if(!procesada){
					
					String cursoAca;
					Asignatura asig;
					Grupo gru;
					
					for(int i=0;i < en.getGruposPorAsignatura().size();i++){
						
						cursoAca = en.getGruposPorAsignatura().get(i).getCursoAcademico();
						asig = en.getGruposPorAsignatura().get(i).getAsignatura();
						gru = en.getGruposPorAsignatura().get(i).getGrupo();
						
					
						
						/*
						if(comprobarAforo()){
		
						}
						*/
					}
					
				}
				
				en.setEstadoProcesada(true);
				break;
			case 1: 
				//Algoritmo 2
				break;
			
			default:
				//
				break;
		}
	}

	public boolean comprobarAforo(List<GruposPorAsignatura> gpa) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
