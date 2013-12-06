package test.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import test.util.SchemaGenerator;

@Entity
@Table(schema=SchemaGenerator.SCHEMA)
public class Project extends BaseEntity {
	
	private static final long serialVersionUID = 4803770976805986497L;
	
	@OneToMany
	private Set<Employee> employees = new HashSet<Employee>();
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Employee manager;
	
	protected Project() {
	}
	
	private Project(Employee mgr, String customer) {
		manager = mgr;
		setName(customer);
	}
	
	public static Project create(Employee manager, String client) {
		return new Project(manager, client);
	}

	public void setManager(Employee newManager) {
		manager = newManager;
	}
	
	public int addMinion(Employee newMinion) {
		employees.add(newMinion);
		return employees.size();
	}

	public int removeMinion(Employee exMinion) {
		employees.remove(exMinion);
		return employees.size();
	}
	
}
