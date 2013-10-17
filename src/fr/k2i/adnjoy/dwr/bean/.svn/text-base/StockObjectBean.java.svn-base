package fr.k2i.adnjoy.dwr.bean;

import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.stripes.bean.FileBean;


public class StockObjectBean {
	private Long id;
	private Double value;
	private String name;
	private String description;
	private FileBean dlFile;
	private Long available;
	private Long brandId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FileBean getDlFile() {
		return dlFile;
	}
	public void setDlFile(FileBean dlFile) {
		this.dlFile = dlFile;
	}
	public Long getAvailable() {
		return available;
	}
	public void setAvailable(Long available) {
		this.available = available;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	public StockObjectBean(StockObject so){
		id = so.getId();
		available = so.getAvailable();
		if(so.getBrand()==null)
			brandId = null;
		else
			brandId = so.getBrand().getId();
		description = so.getDescription();
		dlFile = new FileBean(so.getDlFile());
		name = so.getName();
		value = so.getValue();
	}
	public StockObjectBean() {
		// TODO Auto-generated constructor stub
	}
	
}
