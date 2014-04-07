package hibbert.repository;

import hibbert.domain.Product;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;

@Transactional(propagation = Propagation.MANDATORY)
public class RepositoryImpl implements Repository {
	
	@PersistenceContext
	private EntityManager em;


	public void save(Product entity) {
		em.merge(entity);
	}

	@Override
	public <T extends BaseEntity> Collection<T> findAll(Class<T> class1) {
		TypedQuery<T> query = em.createQuery("from " + class1.getSimpleName(), class1);
		return query.getResultList();
	}

	@Override
	public void deleteAllProducts() {
		TypedQuery<Product> q = em.createQuery("from Product", Product.class);
		List<Product> entities = q.getResultList();
		for (Product product : entities) {
			em.remove(product);
		}
	}

}
