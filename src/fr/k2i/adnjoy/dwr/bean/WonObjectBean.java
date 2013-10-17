package fr.k2i.adnjoy.dwr.bean;

import java.util.Date;

public class WonObjectBean {
	private Long idWinObject;
	private Double value;
	private String name;
	private String description;
	private String objUrlPhoto;
	private String status;
	private Date winDate;
	private String winOn;
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
	public String getObjUrlPhoto() {
		return objUrlPhoto;
	}
	public void setObjUrlPhoto(String objUrlPhoto) {
		this.objUrlPhoto = objUrlPhoto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getWinDate() {
		return winDate;
	}
	public void setWinDate(Date winDate) {
		this.winDate = winDate;
	}
	public Long getIdWinObject() {
		return idWinObject;
	}
	public void setIdWinObject(Long idWinObject) {
		this.idWinObject = idWinObject;
	}
	public String getWinOn() {
		return winOn;
	}
	public void setWinOn(String winOn) {
		this.winOn = winOn;
	}
	
}
