package fr.k2i.adnjoy.business.ad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_NO_SHOW_WITH")
public class NoShowWith extends BusinessObject {

	private static final long serialVersionUID = 5529199402107154434L;

	private Brand brandSrc;
	private Brand brandDst;
	
	@ManyToOne( cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
	@JoinColumn(name="SRC_BRAND_ID")
	public Brand getBrandSrc() {
		return brandSrc;
	}


	public void setBrandSrc(Brand brandSrc) {
		this.brandSrc = brandSrc;
	}

	@ManyToOne( cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
	@JoinColumn(name="DST_BRAND_ID")
	public Brand getBrandDst() {
		return brandDst;
	}


	public void setBrandDst(Brand brandDst) {
		this.brandDst = brandDst;
	}

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
