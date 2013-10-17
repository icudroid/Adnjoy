package fr.k2i.adnjoy.business.jackpot.chanel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table (name="TBL_DIRECT_JACKPOT")
public class ChanelJackPot extends BusinessObject {

	private static final long serialVersionUID = -8228682715502981149L;

	private Date lastWonDate;
	private Double value;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastWonDate() {
		return lastWonDate;
	}


	public void setLastWonDate(Date lastWonDate) {
		this.lastWonDate = lastWonDate;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
