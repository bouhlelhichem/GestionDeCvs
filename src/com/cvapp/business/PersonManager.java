package com.cvapp.business;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cvapp.entities.Activity;
import com.cvapp.entities.Person;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonManager implements IPersonManager {

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	private Person authenPerson = new Person();
	
	@PostConstruct()
	public void debut() {
		System.out.println("Starting " + this);
	}

	@PreDestroy
	public void fin() {
		System.out.println("Stopping " + this);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Person addPerson(Person person) throws org.apache.openjpa.persistence.EntityExistsException {
		if (em.find(Person.class, person.getId()) == null) {
			em.persist(person);
		} else {
			return null;
		}
		return person;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> showPersons() {
		Query query = em.createQuery("SELECT p FROM Person p");
		List<Person> persons = query.getResultList();
		return persons;
	}

	@Override
	public Person showPerson(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.id=:id")
				.setParameter("id", person.getId());
		if (query.getResultList().size() == 0)
			return null;
		Person shownPerson = (Person) query.getSingleResult();
		return shownPerson;
	}

	@Override
	public List<Person> findPerson(String nom) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE :nom")
				.setParameter("nom", "%"+nom+"%");
		if (query.getResultList().size() == 0)
			return null;
		List<Person> shownPerson = (List<Person>) query.getResultList();
		return shownPerson;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> showActivities(Person p) {
		Query query = null;
		if (p.getId() != null) {
			try {
				query = em.createQuery("SELECT a FROM Activity a WHERE a.person.id=:id")
				.setParameter("id", p.getId());
			} catch (Exception e) {
			}
			if (query != null) {
				List<Activity> activities = query.getResultList();
				return activities;
			}
		}
		return null;
	}

	@Override
	public List<Person> findByTitle(String title) {
		Query query = null;
		System.out.println("Find by title method");
		try {
			query = em.createQuery("SELECT DISTINCT p FROM Person p,Activity a WHERE a.title LIKE :title")
				.setParameter("title", "%"+title+"%");

		} catch (NoResultException e) {
			return null;
		}
		if (query != null) {
			@SuppressWarnings("unchecked")
			List<Person> persons = query.getResultList();

			System.out.println(persons.size());

			return persons;
		}
		return null;
	}

//	@Override
//	public String MD5(String md5) {
//		try {
//			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
//			byte[] array = md.digest(md5.getBytes());
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < array.length; ++i) {
//				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
//			}
//			return sb.toString();
//		} catch (java.security.NoSuchAlgorithmException e) {
//		}
//		return null;
//	}
	
	@Override
	public void removePerson(Person person) {
		Person foundPerson = em.find(Person.class, person.getId());
		em.remove(foundPerson);
	}
	
	@Override
	public Person login(String email, String password) throws NoResultException {
		Query query = null;
		query = em
				.createQuery(
						"SELECT authperson FROM Person authperson WHERE authperson.email=:email AND authperson.password=:password")
				.setParameter("email", email).setParameter("password", password);
		if (query.getResultList().size() == 1)
			authenPerson = (Person) query.getSingleResult();
		else
			logout();
		return authenPerson;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePerson(Person person) {
		if (em.find(Person.class, person.getId()) != null)
			em.merge(person);
	}
	
	@Override
	public Person logout() {
		authenPerson = null;
		return authenPerson;
	}

	public Person getAuthenPerson() {
		return authenPerson;
	}

	public void setAuthenPerson(Person authenPerson) {
		this.authenPerson = authenPerson;
	}
	@Override
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}
