package es.uma.informatica.sii.integration.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import es.uma.informatica.sii.entidades.Titulacion;

public class BaseDatos {

	private final static String PATH_TO_CSV = "src/test/resources/DATA/TITULACION_DATA_TABLE.csv";
	private final static String PERSISTENCE_UNIT = "proyectog-tests";
	
	public void init() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(PATH_TO_CSV));
			reader.skip(1);
			List<String[]> rows = reader.readAll();
			
			for(String[] titulacion : rows) {
				Titulacion tit = new Titulacion();
				tit.setCodigo(Integer.parseInt(titulacion[0]));
				tit.setCreditos(Integer.parseInt(titulacion[1]));
				tit.setNombre(titulacion[2]);
				
				em.persist(tit);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		
		em.close();
		emf.close();
	}
	
}
