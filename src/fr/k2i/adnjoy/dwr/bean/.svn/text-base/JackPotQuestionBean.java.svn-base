package fr.k2i.adnjoy.dwr.bean;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.jackpot.chanel.JackPotQuestion;
import fr.k2i.adnjoy.business.jackpot.chanel.Response;

public class JackPotQuestionBean {
	private long idQuestion;
	private String question;
	private List<JackPotResponseBean> reponses;
	
	public JackPotQuestionBean(){}
	public JackPotQuestionBean(JackPotQuestion q) {
		if(q!=null){
			idQuestion = q.getId();
			question = q.getQuestion();
			reponses = new ArrayList<JackPotResponseBean>();
			for (Response resp : q.getReponses()) {
				reponses.add(new JackPotResponseBean(resp));
			}
		}
	}
	public long getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<JackPotResponseBean> getReponses() {
		return reponses;
	}
	public void setReponses(List<JackPotResponseBean> reponses) {
		this.reponses = reponses;
	}
	
	
}
