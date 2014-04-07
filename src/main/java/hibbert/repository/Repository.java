package hibbert.repository;

import hibbert.domain.BaseEntity;
import hibbert.domain.Product;

import java.util.Collection;


public interface Repository {

	void save(Product entity);
	<T extends BaseEntity> Collection<T> findAll(Class<T> class1);
	void deleteAllProducts();
}
