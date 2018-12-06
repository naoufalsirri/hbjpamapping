package fr.naf.hbjpa.hbjpamapping.enums;

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
public class Testhbjpaenum {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idEmploye;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1Enregistrer() {
		 Employe employe =new Employe("Marcos",EmployeStatus.TEMPS_PARTIEL);        
         ts.begin();
         em.persist(employe);                 
         ts.commit();           
         idEmploye = employe.getId();         
         assertNotNull(employe.getId());
	}
	
	@Test
	public void test2RechercherParId() {		
		Employe employe  =em.find(Employe.class, idEmploye);	 				 
		assertEquals(employe.getNom(), "Marcos");
		assertEquals(employe.getEmployeStatus(), EmployeStatus.TEMPS_PARTIEL);
	}
	
	
}
