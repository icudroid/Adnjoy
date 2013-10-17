package fr.k2i.adnjoy.business.ad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.Country;
import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.business.jackpot.chanel.ChanelJackPot;

@Entity
@Table (name="TBL_CHANNEL")
public class Chanel extends BusinessObject {

	private static final long serialVersionUID = -8804735069196110910L;
	
	private String name;
	private String login;
	private String password;
	private String email;
	private String phone;
	private String fax;
	
	private Country country;
	private ChanelJackPot jackpot;
	
	
	private File dlFile;
	
	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="DL_FILE_ID")
	public File getDlFile() {
		return dlFile;
	}

	public void setDlFile(File dlFile) {
		this.dlFile = dlFile;
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


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="JACKPOT_CHANEL_ID")
	public ChanelJackPot getJackpot() {
		return jackpot;
	}

	public void setJackpot(ChanelJackPot jackpot) {
		this.jackpot = jackpot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="COUNTRY_ID")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chanel other = (Chanel) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
