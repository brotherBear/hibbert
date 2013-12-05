package test.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static Employee create(String name) {
		Employee e = new Employee();
		e.setName(name);
		return e;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
