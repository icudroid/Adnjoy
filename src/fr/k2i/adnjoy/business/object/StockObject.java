package fr.k2i.adnjoy.business.object;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.business.ad.Brand;

@Entity
@Table (name="TBL_STOCK_OBJECT")
public class StockObject extends BusinessObject {

	private static final long serialVersionUID = -1527086579894698756L;
	private Double value;
	private String name;
	private String description;
	private File dlFile;
	private Long available;
	private Brand brand;

	@ManyToOne( cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
	@JoinColumn(name="BRAND_ID")
	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="DL_FILE_ID")
	public File getDlFile() {
		return dlFile;
	}


	public void setDlFile(File dlFile) {
		this.dlFile = dlFile;
	}

	@Column(nullable=false)
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	@Column(nullable=false)
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAvailable() {
		return available;
	}


	public void setAvailable(Long available) {
		this.available = available;
	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
}
