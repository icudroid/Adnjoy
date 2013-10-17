package fr.k2i.adnjoy.dwr.bean;

public class ScoreBean {
	private double score;
	private boolean correct;
	private long responseTime;
	private String classement;
	private long correctBrand;
	private String pseudo;
	
	public String getClassement() {
		return classement;
	}
	public void setClassement(String classement) {
		this.classement = classement;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public long getCorrectBrand() {
		return correctBrand;
	}
	public void setCorrectBrand(long correctBrand) {
		this.correctBrand = correctBrand;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}
