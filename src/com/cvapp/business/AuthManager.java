package com.cvapp.business;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cvapp.entities.Activity;
import com.cvapp.entities.Person;

@Stateful
public class AuthManager implements IAuthManager {

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity addActivity(Activity activity) {
		em.persist(activity);
		return this.findActivity(activity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity updateActivity(Activity activity) {
		return em.merge(activity);
	}

	@Override
	public Activity removeActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		em.remove(foundActivity);
		return foundActivity;

	}

	@Override
	public Activity findActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		return foundActivity;
	}

	

	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> showActivities(Person p) {
		Query query = null;
		if (p.getId() != null) {
			try {
				query = em.createQuery("SELECT a FROM Activity a WHERE a.person.id=:id").setParameter("id", p.getId());
			} catch (Exception e) {
			}
			if (query != null) {
				List<Activity> activities = query.getResultList();
				return activities;
			}
		}
		return null;
	}



	

}
