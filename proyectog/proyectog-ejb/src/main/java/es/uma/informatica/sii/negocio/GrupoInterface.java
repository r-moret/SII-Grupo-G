package es.uma.informatica.sii.negocio;

import javax.ejb.Local;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Alumno;
import es.uma.informatica.sii.proyectog.entidades.Grupo;

@Local
public interface GrupoInterface {
	
	public void asignarGrupos(Algoritmo selector) throws SecretariaException;
	
	public void reasignarGrupo(Alumno alumno, Grupo grupo) throws SecretariaException;
	
	// TODO public void aforoMaximo()
	
	public void actualizarGrupo(Grupo grupo) throws SecretariaException;
	
}
