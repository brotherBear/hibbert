package hibbert.repository;

import hibbert.domain.A;

import java.util.Collection;


public interface Repository {

	void save(A entity);
	Collection<A> findAll();
}
