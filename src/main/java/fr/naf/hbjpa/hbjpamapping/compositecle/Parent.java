package fr.naf.hbjpa.hbjpamapping.compositecle;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Parent {

	@EmbeddedId
	private ParentClePrimaire clecomposite;
	
	private int age;

	public Parent() {
		super();		
	}

	public Parent(ParentClePrimaire clecomposite,int age) {
		super();
		this.clecomposite = clecomposite;
		this.age=age;
	}

	public ParentClePrimaire getClecomposite() {
		return clecomposite;
	}

	public void setClecomposite(ParentClePrimaire clecomposite) {
		this.clecomposite = clecomposite;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	
}
