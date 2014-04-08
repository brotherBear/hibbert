package hibbert.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
