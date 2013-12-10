package test.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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

	@OneToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	private Project project;
	
	public void manage(Project proj) {
		project = proj;
	}
	
	public Project managerOf() {
		return project;
	}
	
	public boolean isManager() {
		return project != null ? true: false;
	}
	
	
}
