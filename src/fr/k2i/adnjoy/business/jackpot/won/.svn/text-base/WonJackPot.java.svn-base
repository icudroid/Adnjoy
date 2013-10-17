package fr.k2i.adnjoy.business.jackpot.won;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.object.WinObject;
import fr.k2i.adnjoy.business.user.User;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table (name="TBL_WON_JACKPOT")
@DiscriminatorColumn(
    name="classe",
    discriminatorType=DiscriminatorType.STRING
)
public abstract class WonJackPot extends BusinessObject {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((winDate == null) ? 0 : winDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WonJackPot other = (WonJackPot) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (winDate == null) {
			if (other.winDate != null)
				return false;
		} else if (!winDate.equals(other.winDate))
			return false;
		return true;
	}

	private static final long serialVersionUID = -5095523083771615073L;

	private WinObject value;
	
	private User user;
	
	private Date winDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getWinDate() {
		return winDate;
	}

	public void setWinDate(Date winDate) {
		this.winDate = winDate;
	}

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setValue(WinObject value) {
		this.value = value;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="WIN_OBJECT_ID")
	public WinObject getValue() {
		return value;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
