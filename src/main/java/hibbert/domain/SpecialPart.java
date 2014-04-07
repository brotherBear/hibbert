package hibbert.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import test.util.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA, name = "parts", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "prop1",
				"prop2", "stock" }) })
@DiscriminatorValue(value = "c-obj")
public class C extends B {

	private static final long serialVersionUID = 1L;

	@Column
	private int stock;

	public C(String name) {
		super(name);
	}

	protected C() {
		// For hibernate
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int count) {
		this.stock = count;
	}

}
