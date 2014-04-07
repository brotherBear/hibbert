package hibbert.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "c-obj")
public class SpecialPart extends Part {

	private static final long serialVersionUID = 1L;

	@Column
	private int stock;

	@Column
	private String prop2;

	public SpecialPart(String name) {
		super(name);
	}

	protected SpecialPart() {
		// For hibernate
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int count) {
		this.stock = count;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

}
