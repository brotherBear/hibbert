package test.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.domain.BaseEntity;

@Transactional(propagation = Propagation.MANDATORY)
public class GenericRepositoryHibernateImpl<T, PK extends BaseEntity> implements GenericRepository<T, PK> {

	private Class<T> type;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public GenericRepositoryHibernateImpl(Class<T> type) {
		this.type = type;
	}
	
	public PK create(T newInstance) {
		return (PK) getSession().save(newInstance);
	}


	public T read(PK id) {
		return (T) getSession().get(type, id);
	}

	public void update(T transientObject) {
		getSession().update(transientObject);
	}

	public void delete(T persistedObject) {
		getSession().delete(persistedObject);
	}

}
