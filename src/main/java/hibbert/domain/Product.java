package hibbert.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import test.domain.BaseEntity;
import test.util.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA, name="product")
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Collection<Part> parts = new HashSet<Part>();

	public Product(String name) {
		super();
		this.setName(name);
	}

	protected Product() {
		// For hibernate
	}
	
	public void addPart(Part b) {
		parts.add(b);
	}
	
	public Collection<Part> getParts() {
		return parts;
	}
	
}
