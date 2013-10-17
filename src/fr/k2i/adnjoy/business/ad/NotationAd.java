package fr.k2i.adnjoy.business.ad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_NOTATION_AD")
public class NotationAd extends BusinessObject {

	private static final long serialVersionUID = 2317978889164338474L;

	private Integer note;
	private Question question;
	private Ad advertissement;
	
	
	
	public Integer getNote() {
		return note;
	}


	public void setNote(Integer note) {
		this.note = note;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="QUESTION_ID")
	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="AD_ID")
	public Ad getAdvertissement() {
		return advertissement;
	}

	public void setAdvertissement(Ad advertissement) {
		this.advertissement = advertissement;
	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
