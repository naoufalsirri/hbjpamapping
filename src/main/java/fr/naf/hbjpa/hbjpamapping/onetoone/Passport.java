package fr.naf.hbjpa.hbjpamapping.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String numeroPassport;
	
	@OneToOne(mappedBy="passport",cascade= {CascadeType.PERSIST})
	private Client client;

	
	public Passport() {
		super();		
	}

	public Passport(String numeroPassport, Client client) {
		super();
		this.numeroPassport = numeroPassport;
		this.client = client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroPassport() {
		return numeroPassport;
	}

	public void setNumeroPassport(String numeroPassport) {
		this.numeroPassport = numeroPassport;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
