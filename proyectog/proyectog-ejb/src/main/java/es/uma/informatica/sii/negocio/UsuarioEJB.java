package es.uma.informatica.sii.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.entidades.Usuario;
import es.uma.informatica.sii.exceptions.ContrasenyaUsuarioIncorrecta;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.UsuarioInexistente;

@Stateless
public class UsuarioEJB implements UsuarioInterface {

	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;
	
	@Override
	public Usuario obtenerUsuarioByName(String userName) throws SecretariaException {
		TypedQuery<Usuario> query = em.createQuery("SELECT * FROM Usuario u WHERE u.name = :pname", Usuario.class);
		query.setParameter("pname", userName);
		
		Usuario queryUser;
		try {
			queryUser = query.getSingleResult();
		}
		catch(NoResultException e) {
			throw new UsuarioInexistente();
		}
		
		return queryUser;
	}

	@Override
	public void comprobarLogin(Usuario user) throws SecretariaException {
		Usuario realUser = obtenerUsuarioByName(user.getName());
		
		if(!realUser.getPassword().equals(user.getPassword())) {
			throw new ContrasenyaUsuarioIncorrecta();
		}
	}
	
	

}
