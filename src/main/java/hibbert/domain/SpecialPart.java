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

}
