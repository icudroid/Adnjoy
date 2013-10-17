package fr.k2i.adnjoy.business.ad;

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

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.user.Profile;

@Entity
@Table (name="TBL_QUESTION_BY_PROFILE")
public class QuestionByProfile extends BusinessObject {

	private static final long serialVersionUID = -2812345641982096242L;

	private Profile profile;
	private List<Question> questions;
	
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="PROFILE_ID")
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="QUESTION_BY_PRO_ID")
	public List<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}



	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
