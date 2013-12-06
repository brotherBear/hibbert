package test.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


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
}
