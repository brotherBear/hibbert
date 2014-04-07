package hibbert.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import test.domain.BaseEntity;
import test.util.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA, name="component")
public class A extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	private Collection<B> bs = new HashSet<B>();

	public A(String name) {
		super();
		this.setName(name);
	}

	protected A() {
		// For hibernate
	}
	
	public void addB(B b) {
		bs.add(b);
	}
	
	public Collection<B> getBs() {
		return bs;
	}
	
	public void setBs(Collection<B> bs) {
		this.bs = bs;
	}
}
