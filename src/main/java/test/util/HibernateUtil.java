package test.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import test.domain.BaseEntity;


public class HibernateUtil {

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	private final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
	
	public EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();
		if (em == null) {
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}

	public void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null) {
			em.close();
			threadLocal.set(null);
		}
	}
	
	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}
	
	public void rollback() {
		getEntityManager().getTransaction().rollback();
	}
	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T unproxy(T proxied) {
		T entity = proxied;
		if (entity != null && entity instanceof HibernateProxy) {
			Hibernate.initialize(entity);
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}
}
