package fr.k2i.adnjoy.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="TBL_COUNTRY")
public class Country extends BusinessObject {

	private static final long serialVersionUID = -2059102897399642386L;
	
	private String name;
	private String code;
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
