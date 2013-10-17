package fr.k2i.adnjoy.business.ad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_QUESTION")
public class Question extends BusinessObject {

	private static final long serialVersionUID = -8447289536596640594L;

	private String question;
	
	
	
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}



	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}



	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
