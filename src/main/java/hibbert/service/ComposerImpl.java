package hibbert.service;

import hibbert.domain.A;
import hibbert.domain.B;
import hibbert.domain.C;
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

	public A generate() {
		A entity = new A("test");
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			B b = new B("B"+i);
			b.setProp1("prop1" + (count-i));
			b.setProp2("prop2" + (count-i));
			entity.addB(b);
		}
		return entity;
	}

	public void save(A entity) {
		repo.save(entity);
	}

	@Override
	public A generateWithSubtype() {
		A entity = new A("test2");
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			C b = new C("C"+i);
			b.setProp1("prop1" + (count-i));
			b.setProp2("prop2" + (count-i));
			b.setStock(count-1);
			entity.addB(b);
		}
		return entity;
	}

	@Override
	public Collection<A> fetchAll() {
		return repo.findAll();
	}

	@Override
	public Collection<B> findAllBs() {
		return repo.findAll(B.class);
	}

	@Override
	public Collection<C> findAllCs() {
		return repo.findAll(C.class);
	}
	
	

}
