package com.cvapp.web;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
public class PersonValidator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min = 3, max = 50, message = " min 3 car, max 50 car")
	private String name;

	@Size(min = 3, max = 50, message = " min 3 car, max 50 car")
	private String firstName;

	@Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Cet adresse mail n'est pas valide")
	private String email;

	@Pattern(regexp = "(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?", message = "Cet URL n'est pas valide")
	private String webAddress;

	@Past
	private Date birthday;

	@Size(min = 3, max = 50, message = " min 3 car, max 50 car")
	private String password;

	public Date getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
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

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

}