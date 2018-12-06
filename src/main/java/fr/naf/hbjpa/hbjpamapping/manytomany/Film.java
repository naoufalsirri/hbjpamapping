package fr.naf.hbjpa.hbjpamapping.manytomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Film {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(name="film_acteur",joinColumns= {@JoinColumn(name="film_id")},inverseJoinColumns= {@JoinColumn(name="acteur_id")})
	private Set<Acteur> acteurs =new HashSet<Acteur>();

	
	
	public Film() {
		super();		
	}

	public Film(String nom) {
		super();
		this.nom = nom;
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

	public Set<Acteur> getActeurs() {
		return acteurs;
	}

	public void setActeurs(Set<Acteur> acteurs) {
		this.acteurs = acteurs;
	}
	
	
}
