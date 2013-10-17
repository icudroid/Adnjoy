package fr.k2i.adnjoy.business.webservice;

import java.io.Serializable;

public class TVAnswer implements Serializable {
	private static final long serialVersionUID = 1781325159789248941L;
	private String answer;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
