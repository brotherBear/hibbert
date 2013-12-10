package test.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;
import test.util.HibernateUtil;

@Transactional(propagation = Propagation.MANDATORY)
public class GenericRepositoryHibernateImpl<T extends BaseEntity, PK extends BaseEntity> implements GenericRepository<T, PK> {

	private Class<T> type;

	@Autowired
	HibernateUtil util;

	public GenericRepositoryHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public T create(final T newInstance) {
		// Would like to avoid this transaction stuff... should be managed by the @Transactional
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		T e = newInstance;
		if (newInstance.isPersisted())
			e = em.merge(newInstance);
		em.persist(e);
		util.commit();
		util.closeEntityManager();
		return e;
	}

	public T read(PK id) {
		return util.getEntityManager().find(type, id);
	}

	public T update(final T transientObject) {
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		T e = em.merge(transientObject);
		
		util.commit();
		util.closeEntityManager();
		return e;
	}

	public void delete(T persistedObject) {
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		T e = persistedObject;
		if (persistedObject.isPersisted())
			e = em.merge(persistedObject);
			
		em.remove(e); 
		util.commit();
		util.closeEntityManager();
		
	}

	public T findByName(String name) {
		String hql = "from " + type.getSimpleName() + " where name = :name";
		TypedQuery<T> q = util.getEntityManager().createQuery(hql, type);
		q.setParameter("name", name);
		List<T> resultList = q.getResultList();
		if (resultList.iterator().hasNext())
			return resultList.iterator().next();
		else return null;
	}

	public Collection<T> findAll() {
		String hql = "From " + type.getSimpleName();
		TypedQuery<T> q = util.getEntityManager().createQuery(hql, type);
		
		return q.getResultList();
	}

}
