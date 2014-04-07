package hibbert.service;

import hibbert.domain.Product;
import hibbert.domain.Part;
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
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			Part b = new Part("B"+i);
			b.setProp1("prop1" + (count-i));
			b.setProp2("prop2" + (count-i));
			entity.addPart(b);
		}
		return entity;
	}

	public void save(Product entity) {
		repo.save(entity);
	}

	@Override
	public Product generateWithSubtype() {
		Product entity = new Product("test2");
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			SpecialPart b = new SpecialPart("C"+i);
			b.setProp1("prop1" + (count-i));
			b.setProp2("prop2" + (count-i));
			b.setStock(count-1);
			entity.addPart(b);
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
	
	

}
