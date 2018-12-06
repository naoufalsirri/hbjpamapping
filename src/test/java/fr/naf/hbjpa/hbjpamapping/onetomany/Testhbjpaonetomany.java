package fr.naf.hbjpa.hbjpamapping.onetomany;

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
public class Testhbjpaonetomany {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idEtudiant;
	private static Long idProfesseur;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1EnregistrerUnParUn() {
		 Professeur professeur =new Professeur("prof David",3000);
         Etudiant etudiant =new Etudiant("etudiant Tatyana","licence",professeur);
         ts.begin();
         em.persist(professeur);
         em.persist(etudiant); 
         ts.commit();           
         idEtudiant = etudiant.getId();  
         idProfesseur = professeur.getId();
         assertNotNull(etudiant.getId());
         assertNotNull(professeur.getId());
	}
	
	@Test
	public void test2RechercherUnParUn(){
		Etudiant etudiant =em.find(Etudiant.class, idEtudiant);
		Professeur professeur =em.find(Professeur.class, idProfesseur);
		assertEquals(etudiant.getNom(), "etudiant Tatyana");
		assertEquals(professeur.getNom(), "prof David");
	}
	
	@Test
	public void test3supprimerUnParUn() {
		Etudiant etudiant =em.find(Etudiant.class, idEtudiant);
		Professeur professeur =em.find(Professeur.class, idProfesseur);
		ts.begin();
		em.remove(etudiant);
		em.remove(professeur);
		ts.commit();
		etudiant =em.find(Etudiant.class, idEtudiant);
		assertNull(etudiant);
	}
	
	@Test
	public void test4EnregistrerEtudiantEnCascade() {
		 Professeur professeur =new Professeur("prof Edna",4000);
         Etudiant etudiant =new Etudiant("etudiant Joyel","master",professeur);
         ts.begin();        
         em.persist(etudiant); 
         ts.commit();           
         idEtudiant = etudiant.getId();           
         assertNotNull(etudiant.getId());
         assertNotNull(professeur.getId());
	}
	
	@Test
	public void test5RechercherEtudiant(){
		Etudiant etudiant =em.find(Etudiant.class, idEtudiant);
		Professeur professeur = etudiant.getProfesseur();
		assertEquals(etudiant.getNom(), "etudiant Joyel");
		assertEquals(professeur.getNom(), "prof Edna");
	}
	
	@Test
	public void test6supprimerEtudiant() {
		Etudiant etudiant =em.find(Etudiant.class, idEtudiant);		
		ts.begin();
		em.remove(etudiant);		
		ts.commit();		
	}
	
	@Test
	public void test7EnregistrerProfesseurEnCascade() {
		 Professeur professeur =new Professeur("prof Gabriel",4000);
         Etudiant etudiant =new Etudiant("etudiant Lona","master",null);
         Etudiant etudiant1 =new Etudiant("etudiant Daniel","master",null);
         professeur.ajouterEtudiant(etudiant);
         professeur.ajouterEtudiant(etudiant1);
         ts.begin();        
         em.persist(professeur); 
         ts.commit();           
         idProfesseur = professeur.getId();  
         assertNotNull(professeur.getId());
         assertNotNull(etudiant.getId());
         assertNotNull(etudiant1.getId());
         
	}
	
	@Test
	public void test8RechercherProfesseur() {					 
		  
		Professeur professeur =em.find(Professeur.class, idProfesseur);
		int nbrEtudiant = professeur.getEtudiants().size();
		assertEquals(professeur.getNom(), "prof Gabriel");
		assertEquals(nbrEtudiant, 2);
		
	}
	
	@Test
	public void test9supprimerProfesseur() {
		Professeur professeur =em.find(Professeur.class, idProfesseur);	
		ts.begin();
		em.remove(professeur);		
		ts.commit();	
		professeur =em.find(Professeur.class, idProfesseur);	
		assertNull(professeur);		
	}
	
}
