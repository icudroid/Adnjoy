package fr.k2i.adnjoy.business.webservice;

import java.io.Serializable;

public class TVQuestion implements Serializable {

	private static final long serialVersionUID = 3470655250743981191L;
	private String question;
	private TVAnswer[]answers;
	private TVAnswer correctAnswer;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public TVAnswer[] getAnswers() {
		return answers;
	}
	public void setAnswers(TVAnswer[] answers) {
		this.answers = answers;
	}
	public TVAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(TVAnswer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
