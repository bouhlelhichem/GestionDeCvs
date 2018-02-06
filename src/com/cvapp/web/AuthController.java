package com.cvapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.cvapp.business.IAuthManager;
import com.cvapp.business.IPersonManager;
import com.cvapp.entities.Activity;
import com.cvapp.entities.Nature;
import com.cvapp.entities.Person;

@ManagedBean(name = "auth")
@SessionScoped
public class AuthController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	IAuthManager am;

	@EJB
	IPersonManager pm;

	private PersonValidator validatePerson = new PersonValidator();
	private ActivityValidator validateActivity = new ActivityValidator();
	private List<Nature> nature = new ArrayList<Nature>();
	private Person authPerson, thePerson;
	private Activity activity;
	private List<Activity> activities, findedActivities;

	public PersonValidator getValidatePerson() {
		return validatePerson;
	}

	public void setValidatePerson(PersonValidator validatePerson) {
		this.validatePerson = validatePerson;
	}

	public ActivityValidator getValidateActivity() {
		return validateActivity;
	}

	public void setValidateActivity(ActivityValidator validateActivity) {
		this.validateActivity = validateActivity;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	@PostConstruct
	public void init() {
		nature.add(Nature.AUTRE);
		nature.add(Nature.EXPERIENCE_PROFESSIONNELLE);
		nature.add(Nature.FORMATION);

		validateActivity.setTitle(null);
		validateActivity.setDescription(null);
		// activity.setPerson(validateActivity.getPerson());
		validateActivity.setNature(null);
		validateActivity.setYear(null);
		validateActivity.setId(null);
	}

	String email, password;

	// -----------------------Getters & Setters------------------------------

	public Person getAuthPerson() {
		return authPerson;
	}

	public void setAuthPerson(Person authPerson) {
		this.authPerson = authPerson;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Nature> getNature() {
		return nature;
	}

	public void setNature(List<Nature> nature) {
		this.nature = nature;
	}

	// -----------------------Les méthodes------------------------------

	public String authentification() {
//		password=pm.MD5(password);
		if (pm.login(email, password)!= null) {
			authPerson = pm.login(email, password);
			activities = am.showActivities(getAuthPerson());
			authPerson.setActivities(activities);
			System.out.println("babobaboabbo");
			return "authHome?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscription OK", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "showPersons?faces-redirect=true";

	}

	public String addActivity() {

		Activity activity = new Activity();
		if (validateActivity.getId()== null) {
			activity.setTitle(validateActivity.getTitle());
			activity.setDescription(validateActivity.getDescription());
			activity.setNature(validateActivity.getNature());
			activity.setYear(validateActivity.getYear());
			activity.setWebAddress(validateActivity.getWebAddress());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout d'activité OK", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(authPerson.getEmail());
			activity.setPerson(authPerson);
			System.out.println("*****************************" + activity.getId());
			am.addActivity(activity);
			activities = pm.showActivities(authPerson);

			
		}

		return "authHome?faces-redirect=true";
	}
	
	public String updateActivity() {

		Activity activity = new Activity();
		if (validateActivity.getId()!= null) {
			activity.setTitle(validateActivity.getTitle());
			activity.setDescription(validateActivity.getDescription());
			// activity.setPerson(validateActivity.getPerson());
			activity.setNature(validateActivity.getNature());
			activity.setYear(validateActivity.getYear());
			activity.setId(validateActivity.getId());
			activity.setWebAddress(validateActivity.getWebAddress());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout d'activité OK", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(authPerson.getEmail());
			activity.setPerson(authPerson);
			System.out.println("*****************************" + activity.getId());
			am.updateActivity(activity);

			
		}

		return "authHome?faces-redirect=true";
	}

	public String chargeActivity(Activity activity) {

		this.activity = activity;
		this.validateActivity.setDescription(this.activity.getDescription());
		this.validateActivity.setNature(this.activity.getNature());
		this.validateActivity.setTitle(this.activity.getTitle());
		this.validateActivity.setYear(this.activity.getYear());
		this.validateActivity.setId(this.activity.getId());
		this.validateActivity.setWebAddress(this.activity.getWebAddress());
		System.out.println("------------------->" + this.activity.getId());
		return "updateActivity?faces-redirect=true";
	}

	public String deleteActivity(Activity activity) {

		am.removeActivity(activity);
		activities = pm.showActivities(authPerson);
		return "authHome?faces-redirect=true";

	}

	public String desactivateProfil() {
		pm.removePerson(authPerson);
		authPerson=null;
		return "showPersons?faces-redirect=true";

	}

	public String updateProfile() {

		validatePerson.setBirthday(authPerson.getBirthday());
		validatePerson.setEmail(authPerson.getEmail());
		validatePerson.setFirstName(authPerson.getFirstName());
		validatePerson.setPassword(authPerson.getPassword());
		validatePerson.setName(authPerson.getName());
		validatePerson.setWebAddress(authPerson.getWebAddress());

		return "updateProfile?faces-redirect=true";

	}

	public String updatePerson() {
		Person person = new Person();
		person.setBirthday(validatePerson.getBirthday());
		person.setEmail(validatePerson.getEmail());
		person.setFirstName(validatePerson.getFirstName());
		person.setName(validatePerson.getName());
		person.setWebAddress(validatePerson.getWebAddress());
		person.setId(authPerson.getId());
		pm.updatePerson(person);
		this.authPerson = person;
		return "authHome?faces-redirect=true";
	}

	public String leave() {
		setAuthPerson(pm.logout());
		return "showPersons?faces-redirect=true";
	}

	public String showResume() {
		thePerson = pm.showPerson(authPerson);
		findedActivities = pm.showActivities(authPerson);
		System.out.println("___________________-------------------" + thePerson.getEmail());
		return "showResume?faces-redirect=true";
	}

	public Person getThePerson() {
		return thePerson;
	}

	public void setThePerson(Person thePerson) {
		this.thePerson = thePerson;
	}

	public List<Activity> getFindedActivities() {
		return findedActivities;
	}

	public void setFindedActivities(List<Activity> findedActivities) {
		this.findedActivities = findedActivities;
	}

}
