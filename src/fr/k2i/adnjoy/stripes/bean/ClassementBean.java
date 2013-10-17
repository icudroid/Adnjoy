package fr.k2i.adnjoy.stripes.bean;

public class ClassementBean {
	private long classement;
	private long nbPlayer;
	private LotBean lot;
	private long idJackPot;
	private boolean showJackPotChanel = false;
	private double jackPotValue;
	private String currency;
	private double score;
	
	public double getJackPotValue() {
		return jackPotValue;
	}
	public void setJackPotValue(double jackPotValue) {
		this.jackPotValue = jackPotValue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public boolean isShowJackPotChanel() {
		return showJackPotChanel;
	}
	public void setShowJackPotChanel(boolean showJackPotChanel) {
		this.showJackPotChanel = showJackPotChanel;
	}
	public long getClassement() {
		return classement;
	}
	public void setClassement(long classement) {
		this.classement = classement;
	}
	public long getNbPlayer() {
		return nbPlayer;
	}
	public void setNbPlayer(long nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	public LotBean getLot() {
		return lot;
	}
	public void setLot(LotBean lot) {
		this.lot = lot;
	}
	public long getIdJackPot() {
		return idJackPot;
	}
	public void setIdJackPot(long idJackPot) {
		this.idJackPot = idJackPot;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
