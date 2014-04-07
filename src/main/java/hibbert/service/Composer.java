package hibbert.service;

import hibbert.domain.A;
import hibbert.domain.B;
import hibbert.domain.C;

import java.util.Collection;


public interface Composer {

	A generate();
	A generateWithSubtype();
	Collection<A> fetchAll();
	Collection<B> findAllBs();
	Collection<C> findAllCs();
	void save(A entity);
}
