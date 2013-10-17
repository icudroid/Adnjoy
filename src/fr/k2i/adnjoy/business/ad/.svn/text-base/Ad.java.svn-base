package fr.k2i.adnjoy.business.ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.File;

@Entity
@Table (name="TBL_AD")
public class Ad extends BusinessObject {

	private static final long serialVersionUID = 1940594617085188106L;
	
	private Brand brand;
	private String logoText;
	private Type type;
	private Long duration;//ms
	private List<QuestionByProfile> questions;
	private File dlFile;
	private Date startDate;
	private Date endDate;
	private String uId;
	private Double offerScore;
	
	public Double getOfferScore() {
		return offerScore;
	}

	public void setOfferScore(Double offerScore) {
		this.offerScore = offerScore;
	}


	public String getuId() {
		return uId;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	@ManyToOne( cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
	@JoinColumn(name="DL_FILE_ID")
	public File getDlFile() {
		return dlFile;
	}


	public void setDlFile(File dlFile) {
		this.dlFile = dlFile;
	}
	
	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}


	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}


	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="BRAND_ID")
	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		if(brand!=null){
			if(brand.getAds() == null){
				brand.setAds(new ArrayList<Ad>());
			}
			brand.getAds().add(this);
		}
		this.brand = brand;
	}


	public String getLogoText() {
		return logoText;
	}

	public void setLogoText(String logoText) {
		this.logoText = logoText;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="TYPE_ID")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="AD_ID")
	public List<QuestionByProfile> getQuestions() {
		return questions;
	}


	public void setQuestions(List<QuestionByProfile> questions) {
		this.questions = questions;
	}

	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((uId == null) ? 0 : uId.hashCode());
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
		Ad other = (Ad) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (uId == null) {
			if (other.uId != null)
				return false;
		} else if (!uId.equals(other.uId))
			return false;
		return true;
	}

}
