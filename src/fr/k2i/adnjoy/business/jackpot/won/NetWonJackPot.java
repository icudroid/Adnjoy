package fr.k2i.adnjoy.business.jackpot.won;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.k2i.adnjoy.business.jackpot.JackPot;

@Entity
@DiscriminatorValue("BlindTestWonJackPot")
public class NetWonJackPot extends WonJackPot {
	
	private static final long serialVersionUID = -1504365996255677285L;

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="JACKPOT_ID")
	public JackPot getJackPot() {
		return jackPot;
	}

	public void setJackPot(JackPot jackPot) {
		this.jackPot = jackPot;
	}

	private JackPot jackPot;
	
	

}
