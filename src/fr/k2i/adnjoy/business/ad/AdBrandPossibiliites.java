package fr.k2i.adnjoy.business.ad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_AD_BRANDS_CHOOSES")
public class AdBrandPossibiliites extends BusinessObject {

	private static final long serialVersionUID = -9223230308091786389L;
	
	private Ad ad;
	private Long pauseBefore;
	private List<Brand>possibilities;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="AD_ID")
	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@ManyToMany(
		targetEntity=Brand.class,
		cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinTable(
		name="TBL_AD_BRANDS_ASSO",
		joinColumns=@JoinColumn(name="AD_BRAND_CHOOSES_ID"),
		inverseJoinColumns=@JoinColumn(name="BRAND_ID")
	)
	public List<Brand> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(List<Brand> possibilities) {
		this.possibilities = possibilities;
	}

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public Long getPauseBefore() {
		return pauseBefore;
	}

	public void setPauseBefore(Long pauseBefore) {
		this.pauseBefore = pauseBefore;
	}
	
}
