package es.uma.informatica.sii.negocio;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Clase;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.entidades.Encuesta.EncuestaID;
import es.uma.informatica.sii.exceptions.EncuestaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class EncuestaEJB implements EncuestaInterface {

	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	@Override
	public Encuesta obtenerEncuesta(Timestamp fechaRealizada, Expediente expediente) throws SecretariaException {
		if(fechaRealizada == null || expediente == null) {
			throw new SecretariaException();
		}
		EncuestaID id = new EncuestaID(fechaRealizada, expediente.getNumExpediente());
		Encuesta encEntity = em.find(Encuesta.class, id);
		if(encEntity == null) {
			throw new EncuestaInexistente();
		}
		return encEntity;
	}
	
	private int obtenerAnyos(Timestamp fecha) {
		String anyoString = fecha.toString().substring(0, 4);
		int anyo = Integer.parseInt(anyoString);
		return anyo;
	}
	
	@Override
	public void registrarEncuesta(Encuesta encuesta) throws SecretariaException {
		
		if(encuesta == null) {
			throw new SecretariaException();
		}

		Expediente exp = em.find(Expediente.class, encuesta.getExpediente().getNumExpediente());
		if (exp == null) {
			throw new SecretariaException();
		}
		
		int anyo1 = obtenerAnyos(encuesta.getFechaEnvio());
		Encuesta encuestaCorrecta = null;
		
		List<Encuesta> encuestas = exp.getEncuestas();
		for (Encuesta encu : encuestas) {
			int anyo2 = obtenerAnyos(encu.getFechaEnvio());
		
			if(anyo1 == anyo2) {
				encuestaCorrecta = encu;
			}
		}
		
		if(encuestaCorrecta == null) {
			em.persist(encuesta);
		} 
		else {
			em.remove(encuestaCorrecta);
			em.persist(encuesta);
		}
	}

	@Override
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException {
		boolean res = false;

		if (encuesta == null) {
			throw new SecretariaException();
		}
		// Me quedo con la lista de grupos por asignatura elegida
		List<GruposPorAsignatura> gruposPorAsignatura = encuesta.getGruposPorAsignatura();
		List<String> compatibilidad = new ArrayList<String>();

		for (GruposPorAsignatura gruposPorAsig : gruposPorAsignatura) {
			Asignatura asig = gruposPorAsig.getAsignatura();
			Grupo grup = gruposPorAsig.getGrupo();

			List<Clase> clases = asig.getClases();
			for (Clase clase : clases) {
				if (grup.equals(clase.getGrupo())) {
					String dia = clase.getDia();
					String hora = clase.getHoraInicio().toString();
					String valor = dia + hora;
					if (compatibilidad.contains(valor)) {
						res = true;
					} else {
						compatibilidad.add(valor);
					}
				}
			}
		}

		return res;
	}

}
