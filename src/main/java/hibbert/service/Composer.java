package hibbert.service;

import hibbert.domain.Product;
import hibbert.domain.Part;
import hibbert.domain.SpecialPart;

import java.util.Collection;


public interface Composer {

	Product generate();
	Product generateWithSubtype();
	Collection<Product> fetchAll();
	Collection<Part> findAllParts();
	Collection<SpecialPart> findAllSpecialParts();
	void save(Product entity);
	void clear();
}
