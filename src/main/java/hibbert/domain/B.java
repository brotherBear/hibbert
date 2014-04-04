package hibbert.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import test.domain.BaseEntity;
import test.util.SchemaGenerator;


@Entity
@Table(schema=SchemaGenerator.SCHEMA)
public class B extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String prop1;
	@Column
	private String prop2;
	
	
	public String getProp1() {
		return prop1;
	}
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	public String getProp2() {
		return prop2;
	}
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
}
