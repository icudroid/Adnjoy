package fr.k2i.adnjoy.business.jackpot.won;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.k2i.adnjoy.business.jackpot.TVJackPot;

@Entity
@DiscriminatorValue("ChanelWonJackPot")
public class ChanelWonJackPot extends WonJackPot {
	private static final long serialVersionUID = -380374947696394379L;
	
	private TVJackPot scheduleAd;

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="SCHEDULE_AD_ID")
	public TVJackPot getScheduleAd() {
		return scheduleAd;
	}

	public void setScheduleAd(TVJackPot scheduleAd) {
		this.scheduleAd = scheduleAd;
	}
	
}
