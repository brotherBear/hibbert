package test.repository;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class PersistenceInterceptor {
	@PostLoad
	public void onLoad(Object o) {
//		log(o, "<<<<", "Loaded");
	}
	
	@PrePersist
	public void prePersist(Object o) {
//		log(o, "++++", "Persisting");
	}
	
	@PostPersist
	public void postPersist(Object o) {
		log(o, ">>>>", "Persisted");
	}
	
	@PreUpdate
	public void preUpdate(Object o){
		log(o, "!?!?", "Updating");
	}
	
	@PostUpdate
	public void postUpdate(Object o) {
		log(o, "====", "Updated");
	}
	
	@PreRemove
	public void preRemove(Object o) {
		log(o, "!!!!", "Deleting");
	}
	
	@PostRemove
	public void postRemove(Object o) {
		log(o, "----", "Removed");
	}

	private void log(Object o, String prefix, String ingress) {
		System.out.println(prefix +" " + ingress + " " + o.getClass().getSimpleName() + ": " + o.toString());
	}

}
