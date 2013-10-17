package fr.k2i.adnjoy.dwr.bean;

import fr.k2i.adnjoy.business.ad.Brand;

public class BrandBean {
	private Long id;
	private String name;
	private String logoFile;
	private Long logoId;
	private String login;
	private String password;
	private String email;
	private String phone;
	private String fax;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	public Long getLogoId() {
		return logoId;
	}
	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public BrandBean() {
	}
	public BrandBean(Brand brand,Boolean allInfo) {
		id = brand.getId();
		name = brand.getName();
		logoFile = brand.getDlFile().getDlUrl();
		if(allInfo == true){
			email = brand.getEmail();
			fax = brand.getFax();
			login = brand.getLogin();
			logoId = brand.getDlFile().getId();
			password = brand.getPassword();
			phone = brand.getPhone();
		}
		
	}
	
}
