package hibbert.service;

import hibbert.domain.Product;
import hibbert.domain.Part;
import hibbert.domain.ProductWithSpecialParts;
import hibbert.domain.SpecialPart;
import hibbert.repository.Repository;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class ComposerImpl implements Composer {
	
	@Autowired
	private Repository repo; 

	public Product generate() {
		Product entity = new Product("test");
		int count = numberOfParts();
		for (int i = 0; i < count; i++) {
			Part part = new Part(""+i);
			part.setProp1("prop1" + (count-i));
			entity.addPart(part);
		}
		return entity;
	}

	public void save(Product entity) {
		repo.save(entity);
	}

	@Override
	public Product generateWithSubtype() {
		Product entity = new Product("test2");
		int count = numberOfParts();
		for (int i = 0; i < count; i++) {
			SpecialPart part = new SpecialPart(""+i);
			part.setProp1("prop1" + (count-i));
			part.setProp2("prop2" + (count-i));
			part.setStock(count-1);
			entity.addPart(part);
		}
		return entity;
	}

	@Override
	public Collection<Product> fetchAll() {
		return repo.findAll(Product.class);
	}

	@Override
	public Collection<Part> findAllParts() {
		return repo.findAll(Part.class);
	}

	@Override
	public Collection<SpecialPart> findAllSpecialParts() {
		return repo.findAll(SpecialPart.class);
	}

	@Override
	public void clear() {
		repo.deleteAllProducts();
	}

	@Override
	public ProductWithSpecialParts generateSpecialProduct() {
		ProductWithSpecialParts entity = new ProductWithSpecialParts("special");
		int count = numberOfParts();
		for (int i = 0; i < count; i++) {
			SpecialPart part = new SpecialPart(""+i);
			part.setProp1("prop1" + (count-i));
			part.setProp2("prop2" + (count-i));
			part.setStock(count-1);
			entity.addPart(part);
		}
		return entity;
	}

	private int numberOfParts() {
		Random r = new Random();
		return r.nextInt(20) + 1;
	}
	
	

}
