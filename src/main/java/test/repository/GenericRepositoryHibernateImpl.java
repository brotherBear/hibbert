package test.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public void delete(T persistedObject) {
		T e = persistedObject;
		if (persistedObject.isPersisted())
			e = em.merge(persistedObject);
			
		em.remove(e); 
	}

	public T findByName(String name) {
		String hql = "from " + type.getSimpleName() + " where name = :name";
		TypedQuery<T> q = em.createQuery(hql, type);
		q.setParameter("name", name);
		List<T> resultList = q.getResultList();
		if (resultList.iterator().hasNext())
			return resultList.iterator().next();
		else return null;
	}

	public Collection<T> findAll() {
		String hql = "From " + type.getSimpleName();
		TypedQuery<T> q = em.createQuery(hql, type);
		
		return q.getResultList();
	}

}
