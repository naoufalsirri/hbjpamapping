package fr.naf.hbjpa.hbjpamapping.manytomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.naf.hbjpa.hbjpamapping.composant.Personne;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testhbjpamanytomany {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idActeur;
	private static Long idFilm;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1EnregistrerUnParUn() {
		 Film film =new Film("Terminator");
		 Acteur acteur =new Acteur("gibson");  
		 film.getActeurs().add(acteur);
		 acteur.getFilms().add(film);
         ts.begin();
         em.persist(film);
         em.persist(acteur); 
         ts.commit();           
         idActeur = acteur.getId();  
         idFilm = film.getId();
         assertNotNull(acteur.getId());
         assertNotNull(film.getId());
	}
	
	@Test
	public void test2RechercherUnParUn(){
		Film film =em.find(Film.class, idFilm);
		Acteur acteur =em.find(Acteur.class, idActeur);
		assertEquals(film.getNom(), "Terminator");
		assertEquals(acteur.getNom(), "gibson");
	}
	
	
	
	@Test
	public void test3EnregistrerFilmEnCascade() {
		 Film film =new Film("Breaveheart");
		 Acteur acteur =new Acteur("Tori"); 
		 film.getActeurs().add(acteur);
         ts.begin();        
         em.persist(film); 
         ts.commit();      
         idFilm = film.getId();           
         assertNotNull(film.getId());
         assertNotNull(acteur.getId());
	}
	
	@Test
	public void test4RechercherFilm(){
		Film film =em.find(Film.class, idFilm);
		int acteurSize = film.getActeurs().size();
		assertEquals(film.getNom(), "Breaveheart");
		assertEquals(acteurSize,1);
	}	
		
}
