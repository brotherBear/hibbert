package test.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import test.util.SchemaGenerator;


@Entity
@Table(schema=SchemaGenerator.SCHEMA)
public class Address extends BaseEntity {
	
	private static final long serialVersionUID = 9220898125966778038L;
	@Column
	private String street;
	@ManyToOne(cascade=CascadeType.ALL)
	private Country country;
	
	public Address() {
		super();
	}
	
	public Address(String street, Country country) {
		this.setName(street);
		this.street = street;
		this.country = country;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

}
