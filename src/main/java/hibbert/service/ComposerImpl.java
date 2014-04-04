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
		A entity = new A();
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			B b = new B();
			b.setProp1("prop1" + count);
			b.setProp2("prop2" + count);
			entity.addB(b);
		}
		return entity;
	}

	public void save(A entity) {
		repo.save(entity);
	}

	@Override
	public A generateWithSuptype() {
		A entity = new A();
		Random r = new Random();
		int count = r.nextInt(20);
		for (int i = 0; i < count; i++) {
			C b = new C();
			b.setProp1("prop1" + count);
			b.setProp2("prop2" + count);
			b.setCount(count);
			entity.addB(b);
		}
		return entity;
	}

	@Override
	public Collection<A> fetchAll() {
		return repo.findAll();
	}

}
