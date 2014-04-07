package hibbert.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import common.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA, name = "parts", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id", "name", "stock" }) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@DiscriminatorValue(value = "B-obj")
public class Part extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String prop1;
	@Column
	private String prop2;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product component;

	public Part(String name) {
		setName(name);
	}

	protected Part() {
		// For hibernate
	}

	public String getProp1() {
		return prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
}
