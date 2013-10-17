package fr.k2i.adnjoy.business.jackpot;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.ad.AdBrandPossibiliites;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table (name="TBL_JACKPOT")
@DiscriminatorColumn(
    name="classe",
    discriminatorType=DiscriminatorType.STRING
)
public abstract class JackPot extends BusinessObject {

	private static final long serialVersionUID = -8228682715502981149L;

	private Date startDate;
	private Date endDate;
	private List<AdBrandPossibiliites> ads;
//	private List<Ad>ads;
	

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


//	@ManyToMany(
//			targetEntity=Ad.class,
//			cascade={CascadeType.PERSIST, CascadeType.MERGE}
//			)
//	@JoinTable(
//			name="TBL_JACKPOT_AD",
//			joinColumns=@JoinColumn(name="JACKPOT_ID"),
//			inverseJoinColumns=@JoinColumn(name="AD_ID")
//			)
//	public List<Ad> getAds() {
//		return ads;
//	}
//
//
//	public void setAds(List<Ad> ads) {
//		this.ads = ads;
//	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="AD_BRAND_CHOOSES_ID")
	public List<AdBrandPossibiliites> getAds() {
		return ads;
	}


	public void setAds(List<AdBrandPossibiliites> ads) {
		this.ads = ads;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		JackPot other = (JackPot) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
