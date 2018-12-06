package fr.naf.hbjpa.hbjpamapping.collection;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;



@Entity
public class Ami {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String email;
	
	@ElementCollection
	@CollectionTable(name ="ami_autrenom",joinColumns=@JoinColumn(name="ami_id"))
	@Column(name="autrenom")
	private Collection<String> autrenoms=new ArrayList<String>();

	
	public Ami() {
		super();	
	}


	public Ami(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Collection<String> getAutrenoms() {
		return autrenoms;
	}


	public void setAutrenoms(Collection<String> autrenoms) {
		this.autrenoms = autrenoms;
	}
	
	
	
}
