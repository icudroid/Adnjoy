package fr.k2i.adnjoy.business.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.ad.Ad;

@Entity
@Table(name = "TBL_RECEIVED_OFFER")
public class ReceivedOffer extends BusinessObject {

	private static final long serialVersionUID = 5870775828724464030L;
	
	private User user;
	private Date sendDate;
	private Double score;
	private Date validateDate;
	private Ad offerBy;
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}

	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	@JoinColumn(name="AD_ID")
	public Ad getOfferBy() {
		return offerBy;
	}

	public void setOfferBy(Ad offerBy) {
		this.offerBy = offerBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
