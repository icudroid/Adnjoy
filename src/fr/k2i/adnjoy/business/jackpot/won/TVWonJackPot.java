package fr.k2i.adnjoy.business.jackpot.won;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.jackpot.TVJackPot;

@Entity
@DiscriminatorValue("DirectWonJackPot")
public class TVWonJackPot extends WonJackPot {
	private static final long serialVersionUID = -380374947696394379L;
	
	private TVJackPot scheduleAd;
	
	private Date askedQuestion;
	

	@Temporal(TemporalType.TIMESTAMP)
	public Date getAskedQuestion() {
		return askedQuestion;
	}

	public void setAskedQuestion(Date askedQuestion) {
		this.askedQuestion = askedQuestion;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="SCHEDULE_AD_ID")
	public TVJackPot getScheduleAd() {
		return scheduleAd;
	}

	public void setScheduleAd(TVJackPot scheduleAd) {
		this.scheduleAd = scheduleAd;
	}
	
}
