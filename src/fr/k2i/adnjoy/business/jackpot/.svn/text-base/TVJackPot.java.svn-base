package fr.k2i.adnjoy.business.jackpot;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.jackpot.chanel.JackPotQuestion;

@Entity
@DiscriminatorValue("TVJackPot")
public class TVJackPot extends JackPot {

	private static final long serialVersionUID = 6396535813776198407L;
	
	private Chanel chanel;
	private JackPotQuestion question;


	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="CHANEL_ID")
	public Chanel getChanel() {
		return chanel;
	}


	public void setChanel(Chanel chanel) {
		this.chanel = chanel;
	}


	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="JACKPOT_QUESTION_ID")
	public JackPotQuestion getQuestion() {
		return question;
	}


	public void setQuestion(JackPotQuestion question) {
		this.question = question;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((chanel == null) ? 0 : chanel.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVJackPot other = (TVJackPot) obj;
		if (chanel == null) {
			if (other.chanel != null)
				return false;
		} else if (!chanel.equals(other.chanel))
			return false;
		return true;
	}


}
