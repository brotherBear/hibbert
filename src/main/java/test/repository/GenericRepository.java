package test.repository;

import java.util.Collection;

import test.domain.BaseEntity;


public interface GenericRepository<T extends BaseEntity, PK extends BaseEntity> {

	T create(T newInstance);
	T read (PK id);
	T update(T transientObject);
	void delete(T persistedObject);
	T findByName(String name);
	Collection<T> findAll();
}
