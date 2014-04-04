package hibbert.service;

import java.util.Collection;

import hibbert.domain.A;


public interface Composer {

	A generate();
	A generateWithSuptype();
	Collection<A> fetchAll();
	void save(A entity);
}
