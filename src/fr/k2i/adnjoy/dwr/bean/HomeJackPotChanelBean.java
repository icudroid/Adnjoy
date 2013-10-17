package fr.k2i.adnjoy.dwr.bean;

import java.text.SimpleDateFormat;

import fr.k2i.adnjoy.business.ad.Chanel;


public class HomeJackPotChanelBean {
	private String lastWonDate;
	private Double value;
	private String chanelImg;
	private String currency = "&euro;";
	
	public HomeJackPotChanelBean(){}
	
	public HomeJackPotChanelBean(Chanel chanel) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if(chanel.getJackpot().getLastWonDate()!=null){
			lastWonDate = sdf.format(chanel.getJackpot().getLastWonDate());
		}
		value = chanel.getJackpot().getValue();
		chanelImg = chanel.getDlFile().getDlUrl();
	}
	
	public String getLastWonDate() {
		return lastWonDate;
	}
	public void setLastWonDate(String lastWonDate) {
		this.lastWonDate = lastWonDate;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getChanelImg() {
		return chanelImg;
	}
	public void setChanelImg(String chanelImg) {
		this.chanelImg = chanelImg;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
