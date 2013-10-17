package fr.k2i.adnjoy.stripes.bean;

import java.util.Date;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.dwr.bean.BrandBean;

public class AdBean {
	private Long id;
	private BrandBean brand;
	private String logoText;
	private TypeBean type;
	private Long duration;//ms
	private FileBean dlFile;
	private Date startDate;
	private Date endDate;
	private String uniqueId;

	public AdBean(){}
	
	public AdBean(Ad ad) {
		id = ad.getId();
		brand = new BrandBean(ad.getBrand(),false);
		logoText = ad.getLogoText();
		type = new TypeBean(ad.getType());
		duration = ad.getDuration();
		dlFile = new FileBean(ad.getDlFile());
		startDate = ad.getStartDate();
		endDate = ad.getEndDate();
		uniqueId = ad.getuId();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BrandBean getBrand() {
		return brand;
	}
	public void setBrand(BrandBean brand) {
		this.brand = brand;
	}
	public String getLogoText() {
		return logoText;
	}
	public void setLogoText(String logoText) {
		this.logoText = logoText;
	}
	public TypeBean getType() {
		return type;
	}
	public void setType(TypeBean type) {
		this.type = type;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public FileBean getDlFile() {
		return dlFile;
	}
	public void setDlFile(FileBean dlFile) {
		this.dlFile = dlFile;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
}
