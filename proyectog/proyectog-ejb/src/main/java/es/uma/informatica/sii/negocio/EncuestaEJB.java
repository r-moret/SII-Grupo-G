package es.uma.informatica.sii.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.proyectog.entidades.Encuesta;
import es.uma.informatica.sii.proyectog.entidades.Encuesta.EncuestaID;
import es.uma.informatica.sii.proyectog.entidades.Expediente;

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
		// TODO Auto-generated method stub
		return false;
	}

}
