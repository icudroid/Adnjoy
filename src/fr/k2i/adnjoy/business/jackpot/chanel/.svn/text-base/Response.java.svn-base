package fr.k2i.adnjoy.business.jackpot.chanel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_RESPONSE")
public class Response extends BusinessObject {

	private static final long serialVersionUID = -4333075039692246051L;
	
	private String reponse;

	/**
	 * @return the reponse
	 */
	public String getReponse() {
		return reponse;
	}



	/**
	 * @param reponse the reponse to set
	 */
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}



	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
