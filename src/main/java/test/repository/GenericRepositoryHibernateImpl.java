package test.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;

@Transactional(propagation = Propagation.MANDATORY)
public class GenericRepositoryHibernateImpl<T extends BaseEntity, PK extends BaseEntity> implements GenericRepository<T, PK> {

	private Class<T> type;

	@PersistenceContext
	private EntityManager em;

	public GenericRepositoryHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public T create(final T newInstance) {
		T e = newInstance;
		if (newInstance.isPersisted())
			e = em.merge(newInstance);
		em.persist(e);
		return e;
	}

	public T read(PK id) {
		return em.find(type, id);
	}

	public T update(final T transientObject) {
		T e = em.merge(transientObject);
		return e;
	}
	
	public void refresh(final T entity) {
		if (entity.isPersisted())
		em.refresh(entity);
	}

	public void delete(T persistedObject) {
		T e = persistedObject;
		if (persistedObject.isPersisted())
			e = em.merge(persistedObject);
			
		em.remove(e); 
	}

	public T findByName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root = criteria.from(type);
		criteria.select(root);
		criteria.where(builder.equal(root.get("name"), name));
		List<T> res = em.createQuery(criteria).getResultList();
		if (res.iterator().hasNext()) return res.iterator().next();
		else return null;
	}

	public Collection<T> findAll() {
		String hql = "From " + type.getSimpleName();
		TypedQuery<T> q = em.createQuery(hql, type);
		
		return q.getResultList();
	}

}
