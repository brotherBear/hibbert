package hibbert.repository;

import hibbert.domain.A;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public class RepositoryImpl implements Repository {
	
	@PersistenceContext
	private EntityManager em;


	public void save(A entity) {
		em.persist(entity);
	}

	public Collection<A> findAll() {
		TypedQuery<A> query = em.createQuery("from A", A.class);
		return query.getResultList();
	}

}
