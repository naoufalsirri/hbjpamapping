package fr.naf.hbjpa.hbjpamapping.composant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testhbjpacomposant {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idPersonne;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1Enregistrer() {
		 Adresse adresse =new Adresse("25 rue lactos","Paris","75120");
         Personne personne =new Personne("Jowana",adresse);
         ts.begin();
         em.persist(personne);                 
         ts.commit();           
         idPersonne = personne.getId();         
         assertNotNull(personne.getId());
	}
	
	@Test
	public void test2RechercherParId() {		
		Personne personne =em.find(Personne.class, idPersonne);	 				 
		assertEquals(personne.getNom(), "Jowana");
		assertEquals(personne.getAdresse().getPostale(), "75120");
	}
	
	
}
