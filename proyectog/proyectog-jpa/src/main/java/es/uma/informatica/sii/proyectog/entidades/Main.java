package es.uma.informatica.sii.proyectog.entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectog-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();
	}

}
