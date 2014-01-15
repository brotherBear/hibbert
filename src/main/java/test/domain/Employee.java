package test.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Address> addressSet;
	
	public void manage(Project proj) {
		project = proj;
	}
	
	public Project managerOf() {
		return project;
	}
	
	public boolean isManager() {
		return project != null ? true: false;
	}

	
	public Collection<Address> getAddresses() {
		return addressSet;
	}

	
	public void setAddress(Set<Address> addresses) {
		this.addressSet = addresses;
	}
	
	public void addAddress(Address address) {
		if (this.addressSet == null)
			this.addressSet = new HashSet<Address>();
		this.addressSet.add(address);
	}
	
	
}
