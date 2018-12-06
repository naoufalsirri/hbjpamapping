package fr.naf.hbjpa.hbjpamapping.collection;

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
public class Testhbjpacollection {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idAmi;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1Enregistrer() {
		 Ami ami =new Ami("Fany","fany@gmail.com");
         ami.getAutrenoms().add("fafa");
         ami.getAutrenoms().add("foli");
         ts.begin();
         em.persist(ami);                 
         ts.commit();           
         idAmi = ami.getId();         
         assertNotNull(ami.getId());
	}
	
	@Test
	public void test2RechercherParId() {		
		Ami ami =em.find(Ami.class, idAmi);	 
		int autrenoms = ami.getAutrenoms().size();
		assertEquals(ami.getNom(), "Fany");
		assertEquals(autrenoms, 2);
	}
	
	
}
