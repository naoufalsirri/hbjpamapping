package fr.naf.hbjpa.hbjpamapping.compositecle;

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
public class Testhbjpacompositecle {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static ParentClePrimaire idParent;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1Enregistrer() {
		 ParentClePrimaire clecomposite =new ParentClePrimaire("nom","prenom");
         Parent parent= new Parent(clecomposite,32);
         ts.begin();
         em.persist(parent);                 
         ts.commit();           
         idParent = parent.getClecomposite();         
         assertNotNull(parent.getClecomposite());
	}
	
	@Test
	public void test2RechercherParId() {		
		Parent parent =em.find(Parent.class, idParent);	 				 
		assertEquals(parent.getAge(), 32);		
	}
	
	
}
