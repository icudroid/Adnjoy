package fr.k2i.adnjoy.dwr.bean;

import fr.k2i.adnjoy.business.Country;

public class CountryBean {
	private String country;
	private Long id;
	public CountryBean(Country c) {
		country = c.getName();
		id = c.getId();
	}
	public CountryBean() {
		// TODO Auto-generated constructor stub
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
