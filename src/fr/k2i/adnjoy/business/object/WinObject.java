package fr.k2i.adnjoy.business.object;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.File;

@Entity
@Table (name="TBL_WIN_OBJECT")
public class WinObject extends BusinessObject {

	private static final long serialVersionUID = -4614432432005108519L;
	private Double value;
	private String name;
	private String description;
	private File dlFile;
	private Status status;
	private Date winDate;
	

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

	@Enumerated(EnumType.ORDINAL)
	public Status getStatus() {
		return status;
	}
	
	
	public void setStatus(Status status) {
		this.status = status;
	}


	@Temporal(TemporalType.DATE)
	public Date getWinDate() {
		return winDate;
	}




	public void setWinDate(Date winDate) {
		this.winDate = winDate;
	}

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public WinObject(StockObject obj) {
		value = obj.getValue();
		name = obj.getName();
		description = obj.getDescription();
		dlFile = obj.getDlFile();
		winDate = new Date();
		status = Status.InProgress;
		obj.setAvailable(obj.getAvailable()-1);
  	}
  	
  	public WinObject(){}
}
