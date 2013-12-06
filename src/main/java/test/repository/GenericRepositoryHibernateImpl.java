package test.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;
import test.util.HibernateUtil;

@Transactional(propagation = Propagation.MANDATORY)
public class GenericRepositoryHibernateImpl<T, PK extends BaseEntity> implements GenericRepository<T, PK> {

	private Class<T> type;

	@Autowired
	HibernateUtil util;

	private EntityManager getEm() {
		return util.getEntityManager();
	}

	public GenericRepositoryHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public void create(T newInstance) {
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		em.persist(newInstance);
		util.commit();
	}

	public T read(PK id) {
		return getEm().find(type, id);
	}

	public void update(T transientObject) {
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		em.merge(transientObject);
		em.persist(transientObject);
		
		util.commit();
	}

	public void delete(T persistedObject) {
		EntityManager em = util.getEntityManager();
		util.beginTransaction();
		em.remove(persistedObject);
		util.commit();
		
	}

	public T findByName(String name) {
		String hql = "from " + type.getSimpleName() + " where name = :name";
		TypedQuery<T> q = getEm().createQuery(hql, type);
		q.setParameter("name", name);
		return q.getSingleResult();
	}

}
