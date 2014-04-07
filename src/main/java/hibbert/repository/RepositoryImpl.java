package hibbert.repository;

import hibbert.domain.A;

import java.util.Collection;

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


	public void save(A entity) {
		em.merge(entity);
	}

	@Override
	public <T extends BaseEntity> Collection<T> findAll(Class<T> class1) {
		TypedQuery<T> query = em.createQuery("from " + class1.getSimpleName(), class1);
		return query.getResultList();
	}

}
