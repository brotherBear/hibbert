package hibbert.repository;

import hibbert.domain.Product;

import java.util.Collection;

import test.domain.BaseEntity;


public interface Repository {

	void save(Product entity);
	<T extends BaseEntity> Collection<T> findAll(Class<T> class1);
	void deleteAllProducts();
}
