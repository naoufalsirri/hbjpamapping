package fr.naf.hbjpa.hbjpamapping.composant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	private String rue;
	private String ville;
	@Column(name="codepostale")
	private String postale;
	
	public Adresse() {}
	
	
	
	public Adresse(String rue, String ville, String codepostale) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.postale = codepostale;
	}



	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getPostale() {
		return postale;
	}



	public void setPostale(String postale) {
		this.postale = postale;
	}
	
}
