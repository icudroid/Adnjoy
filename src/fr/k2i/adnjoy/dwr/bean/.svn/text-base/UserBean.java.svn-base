package fr.k2i.adnjoy.dwr.bean;

import java.util.Date;

import fr.k2i.adnjoy.business.user.User;

public class UserBean {
	private Long id;
	private String email;
	private String password;
	private String pseudo;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private String firstName;
	private String lastName;
	private String sex;
	private Date birthday;
	private CountryBean country;
	public UserBean(User u) {
		address1 = u.getAddress1();
		address2 = u.getAddress2();
		birthday = u.getBirthday();
		city = u.getCity();
		country = new CountryBean(u.getCountry());
		email = u.getEmail();
		firstName = u.getFirstName();
		id = u.getId();
		lastName = u.getLastName();
		password = u.getPassword();
		pseudo = u.getPseudo();
		sex = u.getSex().getLabel();
		zipCode = u.getZipCode();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public CountryBean getCountry() {
		return country;
	}
	public void setCountry(CountryBean country) {
		this.country = country;
	}

}
