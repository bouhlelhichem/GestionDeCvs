package com.cvapp.tests;

import static org.junit.Assert.*;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;

import com.cvapp.business.IAuthManager;
import com.cvapp.business.IPersonManager;
import com.cvapp.entities.Activity;
import com.cvapp.entities.Nature;
import com.cvapp.entities.Person;
import com.cvapp.web.PersonController;

public class TestAuthManager {

	@EJB
	IAuthManager authPersonManager;

	@EJB
	IPersonManager personManager;

	@EJB
	PersonController pc;

	Person person;
	Person nonperson;

	public TestAuthManager() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
	}

	@Before
	public void setUp() {
		person = new Person();
		nonperson = new Person();
		person.setEmail("bouhlelhichem07@gmail.com");
		person.setPassword("password");
		nonperson.setEmail("bouhlelhichem07@gmail.com");
		nonperson.setPassword("pwd0000");
		assertNotNull(authPersonManager);
		assertNotNull(authPersonManager);
	}

	//
	@Test
	public void testAuthentificate() throws Exception {

		Person authenPerson = personManager.login(person.getEmail(), person.getPassword());
		assertNotNull(authenPerson);
		Person nonauthenPerson = personManager.login(nonperson.getEmail(), nonperson.getPassword());
		assertNull(nonauthenPerson);

	}

	
	 @Test
	 public void TestRemovePerson() {
	
	 Person person = new Person();
	 person.setId(1);
	 // List<Activity> activities = personManager.showActivities(person);
	 // activityManager.removeActivities(activities);
	 personManager.removePerson(person);
	 assertNull(personManager.showPerson(person));
	 }
	 @Test
	 public void TestRemoveActivy() {
	
	 Activity activity = new Activity();
	 activity.setId(2);
	 authPersonManager.removeActivity(activity);
	 }
	
	@Test
	public void TestCreatActivity() {
		Person p = new Person();
		p.setId(1);
		Person person = personManager.showPerson(p);
		Activity c = new Activity();
		c.setTitle("Stage chez Chandamama");
		c.setDescription("playing for change");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);
		
		c = new Activity();
		c.setTitle("football");
		c.setDescription("Match chez Chandamama");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);
		
		c = new Activity();
		c.setTitle("Basket BALL");
		c.setDescription("playing for change");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Dut Developpement Informatique");
		c.setDescription("Master chez AixMarseille");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Master  Informatique");
		c.setDescription("Licence chez AixMarseille");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Licence Informatique");
		c.setDescription("playing for change");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Stage chez CGI");
		c.setDescription("Stage chez CGI");
		c.setNature(Nature.EXPERIENCE_PROFESSIONNELLE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);
	}
	 @Test
	 public void updateActivity(){
	 Activity c = new Activity();
	 c.setId(1);
	 c.setTitle("PL/SQL");
	 c.setDescription("SQL/PL LQS/LP");
	 c.setNature(Nature.FORMATION);
	 c.setYear(new Date(2012));
	 authPersonManager.updateActivity(c);
	
	
	 }
	
	 @Test
	 public void testUpdatePerson() {
	 Person p = new Person();
	 p.setId(1);
	 p = personManager.showPerson(p);
	 p.setFirstName("Bouhlel");
	 p.setName("Hichem");
	 personManager.updatePerson(p);
	 Person personUpdated = personManager.showPerson(p);
	 assertTrue(personUpdated.getFirstName().equals("Bouhlel"));
	
	 }

}
