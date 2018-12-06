package fr.naf.hbjpa.hbjpamapping.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employe {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	@Enumerated(EnumType.STRING)
	@Column(name="employe_status")
	private EmployeStatus employeStatus;


	public Employe() {
		super();		
	}
	
	
	public Employe(String nom, EmployeStatus employeStatus) {
		super();
		this.nom = nom;
		this.employeStatus = employeStatus;
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

	public EmployeStatus getEmployeStatus() {
		return employeStatus;
	}

	public void setEmployeStatus(EmployeStatus employeStatus) {
		this.employeStatus = employeStatus;
	}
	
	
	
}
