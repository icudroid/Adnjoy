package fr.k2i.adnjoy.dwr.bean;

public class HomeJackPotWinnerBean {
	private String pseudo;
	private String lotName;
	private Double lotValue;
	private String currency;
	private String winOnImg;
	private String dt;
	
	
	
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public Double getLotValue() {
		return lotValue;
	}
	public void setLotValue(Double lotValue) {
		this.lotValue = lotValue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getWinOnImg() {
		return winOnImg;
	}
	public void setWinOnImg(String winOnImg) {
		this.winOnImg = winOnImg;
	}
	
	
}
