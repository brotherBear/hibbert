package test.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;

@Transactional(propagation = Propagation.MANDATORY)
public class GenericRepositoryHibernateImpl<T, PK extends BaseEntity> implements GenericRepository<T, PK> {

	private Class<T> type;

	@PersistenceUnit
	EntityManagerFactory emf;

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public GenericRepositoryHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public void create(T newInstance) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(newInstance);
		entityManager.getTransaction().commit();
	}

	public T read(PK id) {
		return getEntityManager().find(type, id);
	}

	public void update(T transientObject) {
		getEntityManager().merge(transientObject);
	}

	public void delete(T persistedObject) {
		getEntityManager().remove(persistedObject);
	}

	public T findByName(String name) {
		String hql = "from " + type.getSimpleName() + " where name = :name";
		TypedQuery<T> q = getEntityManager().createQuery(hql, type);
		q.setParameter("name", name);
		return q.getSingleResult();
	}

}
