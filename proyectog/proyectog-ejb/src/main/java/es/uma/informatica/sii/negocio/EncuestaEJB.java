package es.uma.informatica.sii.negocio;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Asignatura;
import es.uma.informatica.sii.proyectog.entidades.Clase;
import es.uma.informatica.sii.proyectog.entidades.Encuesta;
import es.uma.informatica.sii.proyectog.entidades.Encuesta.EncuestaID;
import es.uma.informatica.sii.proyectog.entidades.Expediente;
import es.uma.informatica.sii.proyectog.entidades.Grupo;
import es.uma.informatica.sii.proyectog.entidades.GruposPorAsignatura;

public class EncuestaEJB implements EncuestaInterface {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectog-jpa");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void registrarEncuesta(Expediente expediente, Encuesta encuesta) throws SecretariaException {

		Expediente exp = em.find(Expediente.class, expediente.getNumExpediente());

		if (exp == null) {
			// El expediente no existe en la bbdd
			throw new SecretariaException();
		}

		PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
		Object idEncuesta = util.getIdentifier(encuesta);

		Encuesta enc = em.find(Encuesta.class, (EncuestaID) idEncuesta);

		if (enc == null) {
			// La encuesta no se encontraba en la bbdd
			encuesta.setExpediente(exp);
			em.persist(encuesta);
		} else if (enc != null) {
			// La encuesta estaba en la bbdd y se debe sobreescribir
			enc.setExpediente(expediente);
			em.merge(enc);
		}

	}

	@Override
	public boolean incompatibilidadHoraria(Encuesta encuesta) throws SecretariaException {
		boolean res = false;

		// Busco la encuesta en la bbdd
		PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
		Object idEncuesta = util.getIdentifier(encuesta);

		Encuesta enc = em.find(Encuesta.class, (EncuestaID) idEncuesta);

		if (enc == null) {
			throw new SecretariaException();
		}
		// Me quedo con la lista de grupos por asignatura elegida
		List<GruposPorAsignatura> gruposPorAsignatura = enc.getGruposPorAsignatura();
		HashMap<Integer, String> mapaCompatibilidad = new HashMap<Integer, String>();
		Integer clave = 0;

		for (GruposPorAsignatura gruposPorAsig : gruposPorAsignatura) {
			Asignatura asig = gruposPorAsig.getAsignatura();
			Grupo grup = gruposPorAsig.getGrupo();

			List<Clase> clases = asig.getClases();
			for (Clase clase : clases) {
				if (grup.equals(clase.getGrupo())) {
					String dia = clase.getDia();
					String hora = clase.getHoraInicio().toString();
					String valor = dia + hora;
					if (mapaCompatibilidad.containsValue(valor)) {
						res = true;
					} else {
						clave++;
						mapaCompatibilidad.put(clave, valor);
					}
				}
			}
		}

		return res;
	}

}
