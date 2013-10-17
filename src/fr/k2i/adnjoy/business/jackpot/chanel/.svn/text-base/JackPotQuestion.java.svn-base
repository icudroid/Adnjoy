package fr.k2i.adnjoy.business.jackpot.chanel;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_JACKPOT_QUESTION")
public class JackPotQuestion extends BusinessObject {

	private static final long serialVersionUID = -8447289536596640594L;

	private String question;
	private List<Response> reponses;
	private Response correctResponse;   
	private Date askedDateTime;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAskedDateTime() {
		return askedDateTime;
	}



	public void setAskedDateTime(Date askedDateTime) {
		this.askedDateTime = askedDateTime;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="JACKPOT_QUESTION_ID")
	public List<Response> getReponses() {
		return reponses;
	}


	public void setReponses(List<Response> reponses) {
		this.reponses = reponses;
	}


	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="RESPONSE_ID")
	public Response getCorrectResponse() {
		return correctResponse;
	}




	public void setCorrectResponse(Response correctResponse) {
		this.correctResponse = correctResponse;
	}




	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
