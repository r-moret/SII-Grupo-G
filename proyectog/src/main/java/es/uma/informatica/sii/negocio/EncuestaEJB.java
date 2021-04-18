package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.Clase;
import es.uma.informatica.sii.entidades.Encuesta;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.entidades.Encuesta.EncuestaID;
import es.uma.informatica.sii.exceptions.SecretariaException;

@Stateless
public class EncuestaEJB implements EncuestaInterface {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectog-jpa");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void registrarEncuesta(Encuesta encuesta) throws SecretariaException {

		Expediente exp = em.find(Expediente.class, encuesta.getExpediente().getNumExpediente());

		if (exp == null) {
			// El expediente no existe en la bbdd
			throw new SecretariaException();
		}

		PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
		EncuestaID idEncuesta = (EncuestaID) util.getIdentifier(encuesta);

		Encuesta enc = em.find(Encuesta.class, idEncuesta);

		if (enc == null) {
			// La encuesta no se encontraba en la bbdd
			em.persist(encuesta);
		} else if (enc != null) {
			// La encuesta estaba en la bbdd y se debe sobreescribir
			em.merge(enc);
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
