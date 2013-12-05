package test.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project extends BaseEntity {
	
	private static final long serialVersionUID = 4803770976805986497L;
	
	@OneToMany
	private Set<Employee> employees = new HashSet<Employee>();
	@ManyToOne
	private Employee manager;
	
	private String cust;

	protected Project() {
	}
	
	private Project(Employee mgr, String customer) {
		manager = mgr;
		cust = customer;
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
