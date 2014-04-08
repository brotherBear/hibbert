package hibbert.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="special_product")
public class ProductWithSpecialParts extends Product {

	private static final long serialVersionUID = 1L;

	public ProductWithSpecialParts(String name) {
		super(name);
	}
	
	protected ProductWithSpecialParts() {
		// for hibernate
	}

	public void addSpecialPart(SpecialPart part) {
		super.addPart(part);
	}

}
