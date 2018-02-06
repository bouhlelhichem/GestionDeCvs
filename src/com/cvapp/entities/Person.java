package com.cvapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.openjpa.persistence.jdbc.Unique;

@Entity()
@Table(name = "PERSON")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	public Person() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Person(String name, String firstName, String email, String webAddress, Date birthday, String password) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.email = email;
		this.webAddress = webAddress;
		this.birthday = birthday;
		this.password = password;
	}

	@Column
	@NotNull
	private String name;
	@Column
	@NotNull
	private String firstName;
	@Column(unique=true)
	@NotNull
	private String email;
	@Column
	private String webAddress;
	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Column
	@NotNull
	private String password;

	@OneToMany(mappedBy="person")
	@JoinTable(name="PERSON_ACTIVITIES")
	private List<Activity> activities;

	public Date getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
