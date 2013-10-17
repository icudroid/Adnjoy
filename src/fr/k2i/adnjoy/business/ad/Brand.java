package fr.k2i.adnjoy.business.ad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.File;

@Entity
@Table (name="TBL_BRAND")
public class Brand extends BusinessObject {

	private static final long serialVersionUID = -2840240239928955933L;
	private String name;
	
	private String login;
	private String password;
	private String email;
	private String phone;
	private String fax;
	
	private File dlFile;
	
	private List<Ad> ads;
	private List<NoShowWith> noTogether;
	
	
	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="DL_FILE_ID")
	public File getDlFile() {
		return dlFile;
	}


	public void setDlFile(File dlFile) {
		this.dlFile = dlFile;
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}


	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}



	/**
	 * @return the name
	 */
	@Column(unique=true,nullable=false)
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="brand")
	public List<Ad> getAds() {
		return ads;
	}


	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}


	public Brand() {
		super();
	}


	public void removeAd(Ad a) {
		List<Ad> as = getAds();
		for (int i = 0; i < as.size(); i++) {
			Ad ad = as.get(i);
			if(a.getId().equals(ad.getId())){
				getAds().remove(i);
				break;
			}
		}
		a.setBrand(null);
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="brandSrc")
	public List<NoShowWith> getNoTogether() {
		return noTogether;
	}


	public void setNoTogether(List<NoShowWith> noTogether) {
		this.noTogether = noTogether;
	}


	@Override
	public String toString() {
		return name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Brand other = (Brand) obj;
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
