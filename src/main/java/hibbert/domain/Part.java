package hibbert.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import test.domain.BaseEntity;
import test.util.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA, name = "parts", uniqueConstraints = { @UniqueConstraint(
				columnNames = { "name", "prop1", "prop2" }) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@DiscriminatorValue(value = "B-obj")
public class B extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String prop1;
	@Column
	private String prop2;
//	@OneToMany(mappedBy="bs")
//	private A component;

	public B(String name) {
		setName(name);
	}

	protected B() {
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
