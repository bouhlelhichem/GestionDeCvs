package com.cvapp.business;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.openjpa.persistence.EntityExistsException;

import com.cvapp.entities.Activity;
import com.cvapp.entities.Person;

public interface IPersonManager {

	Person login(String email, String password) throws NoResultException;

	Person logout();

	Person addPerson(Person person) throws EntityExistsException;

	void updatePerson(Person person);

	void removePerson(Person person);

	List<Person> showPersons();

	Person showPerson(Person person);

	List<Person> findByTitle(String title);

	List<Activity> showActivities(Person p);

	List<Person> findPerson(String nom);

	String MD5(String md5);

}
