package hibbert.repository;

import hibbert.domain.A;

import java.util.Collection;

import test.domain.BaseEntity;


public interface Repository {

	void save(A entity);
	<T extends BaseEntity> Collection<T> findAll(Class<T> class1);
}
