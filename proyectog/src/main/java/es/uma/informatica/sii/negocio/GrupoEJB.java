package es.uma.informatica.sii.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo.SolicitudCambioGrupoID;
import es.uma.informatica.sii.exceptions.EncuestaInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.SolicitudCambioGrupoInexistente;

@Stateless
public class GrupoEJB implements GrupoInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;


	@Override
	public void asignarGrupos(Algoritmo selector, Encuesta encuesta) throws SecretariaException {
		if(encuesta==null){
			throw new SecretariaException();
		}
		
		Encuesta e = em.find(Encuesta.class, encuesta.getExpediente());
		
		if(e == null) {
			throw new EncuestaInexistente();
		}
	
		selector.aplicarAlgoritmo(0, encuesta);
		em.merge(encuesta);
	}
	
	@Override
	public void registrarSolicitudCambioGrupo(SolicitudCambioGrupo solicitud) throws SecretariaException {
		if(solicitud == null) {
			throw new SecretariaException();
		}
		
		SolicitudCambioGrupo s = em.find(SolicitudCambioGrupo.class, solicitud.getExpediente());
	
		if(s == null) {
			throw new SolicitudCambioGrupoInexistente();
		}
		
		Expediente exp = em.find(Expediente.class, s.getExpediente().getNumExpediente());
		
		if(exp==null){
			throw new ExpedienteInexistente();
		}
		
		em.persist(s);
		
	}

	@Override
	public void reasignarGrupo(Expediente expediente, Grupo grupo) throws SecretariaException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void aforoMaximo() throws SecretariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarGrupo(Grupo grupo) throws SecretariaException {
		if(grupo == null) {
			//Grupo no existe
			throw new GrupoInexistente();
		}
		
		Grupo g = em.find(Grupo.class, grupo.getId());
		
		if(g == null) {
			//Grupo no existe en la BBDD
			throw new GrupoInexistente();
		}

		em.merge(grupo);	
	}
}
