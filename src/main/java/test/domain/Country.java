package test.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import test.util.SchemaGenerator;


@Entity
@Table(schema=SchemaGenerator.SCHEMA)
public class Country extends BaseEntity{

	public Country() {
		
	}
	
	public Country(String countryName) {
		super();
		this.setName(countryName);
	}

	private static final long serialVersionUID = -2627441258990965037L;
	

}
