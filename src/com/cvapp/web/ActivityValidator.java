package com.cvapp.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cvapp.entities.Nature;

/**
 * 
 *         This class is used to define constraints for activities edition
 */
@ManagedBean
public class ActivityValidator {



	private Nature nature;
	private Integer id;

	@Size(min = 5, max = 100, message = "Titre d'une activite: min 5 car, max 50 car")
	private String title;

	@NotNull(message = "Entrer une date")
	private Date year;

	@Size(min = 5, max = 500, message = "Description d'une activite: min 5 car, max 50 car")
	private String description;

	@Pattern(regexp = "(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?", message = "Cet URL n'est pas valide")
	private String webAddress;

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
