package test.repository;

import test.domain.BaseEntity;


public interface GenericRepository<T, PK extends BaseEntity> {

	void create(T newInstance);
	T read (PK id);
	void update(T transientObject);
	void delete(T persistedObject);
	T findByName(String name);
}
