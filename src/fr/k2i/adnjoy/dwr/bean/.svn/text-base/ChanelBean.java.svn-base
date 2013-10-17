package fr.k2i.adnjoy.dwr.bean;

import fr.k2i.adnjoy.business.ad.Chanel;


public class ChanelBean {
	private Long id;
	private String logoFile;
	private Long logoId;
	private String name;
	private CountryBean country;
	private String login;
	private String password;
	private String email;
	private String phone;
	private String fax;
	
	public ChanelBean(){}
	
	public ChanelBean(Chanel chanel,Boolean allInfo) {
		if(allInfo==true){
			logoId = chanel.getDlFile().getId();	
			country = new CountryBean(chanel.getCountry());	
			login = chanel.getLogin();
			password = chanel.getPassword();
			email = chanel.getEmail();
			phone = chanel.getPhone();
			fax = chanel.getFax();
		}
		id = chanel.getId();
		logoFile = chanel.getDlFile().getDlUrl();
		name = chanel.getName();
		
	}
	
	public CountryBean getCountry() {
		return country;
	}
	public void setCountry(CountryBean country) {
		this.country = country;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
