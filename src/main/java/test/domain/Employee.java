package test.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import test.util.SchemaGenerator;

@Entity
@Table(schema=SchemaGenerator.SCHEMA)
public class Employee extends BaseEntity{

	private static final long serialVersionUID = 2L;
	
	public static Employee create(String name) {
		Employee e = new Employee();
		e.setName(name);
		return e;
	}
	
	
}
