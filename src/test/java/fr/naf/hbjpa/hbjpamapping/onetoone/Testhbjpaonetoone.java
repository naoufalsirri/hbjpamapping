package fr.naf.hbjpa.hbjpamapping.onetoone;

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
public class Testhbjpaonetoone {

	private static EntityManager em;
	private static EntityTransaction ts;
	private static Long idPassport;
	private static Long idClient;
	
	@BeforeClass
	public static void initialisation() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");    	          
		 em = emf.createEntityManager();
		 ts= em.getTransaction();
	}
	
	@Test
	public void test1EnregistrerUnParUn() {
		 Passport passport =new Passport("9876545",null);
		 Client client =new Client("client sam",passport);      
         ts.begin();
         em.persist(passport);
         em.persist(client); 
         ts.commit();           
         idClient = client.getId();  
         idPassport = passport.getId();
         assertNotNull(client.getId());
         assertNotNull(passport.getId());
	}
	
	@Test
	public void test2RechercherUnParUn(){
		Client client =em.find(Client.class, idClient);
		Passport passport =em.find(Passport.class, idPassport);
		assertEquals(client.getNom(), "client sam");
		assertEquals(passport.getNumeroPassport(), "9876545");
	}
	
	
	
	@Test
	public void test3EnregistrerClientEnCascade() {
		 Passport passport =new Passport("5546453",null);
		 Client client =new Client("client Gabriel",passport);         
         ts.begin();        
         em.persist(client); 
         ts.commit();      
         idClient = client.getId();           
         assertNotNull(passport.getId());
         assertNotNull(client.getId());
	}
	
	@Test
	public void test4RechercherCLient(){
		Client client =em.find(Client.class, idClient);
		Passport passport = client.getPassport();
		assertEquals(client.getNom(), "client Gabriel");
		assertEquals(passport.getNumeroPassport(), "5546453");
	}
	
	@Test
	public void test5EnregistrerPassportEnCascade() {		 
		 Client client =new Client("client dem",null);
		 Passport passport =new Passport("09876",client);
		 client.setPassport(passport);
         ts.begin();        
         em.persist(passport); 
         ts.commit();      
         idPassport = passport.getId();           
         assertNotNull(passport.getId());
         assertNotNull(client.getId());
	}
	
	@Test
	public void test6RechercherPassport(){
		Passport passport =em.find(Passport.class, idPassport);
		Client client = passport.getClient();
		assertEquals(client.getNom(), "client dem");
		assertEquals(passport.getNumeroPassport(), "09876");
	}
		
}
