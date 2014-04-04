package hibbert.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import test.domain.BaseEntity;
import test.util.SchemaGenerator;

@Entity
@Table(schema = SchemaGenerator.SCHEMA)
public class A extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Collection<B> bs;

	public Collection<B> getBs() {
		return bs;
	}

	public void setBs(Collection<B> bs) {
		this.bs = bs;
	}
	
	public void addB(B b) {
		bs.add(b);
	}
}
