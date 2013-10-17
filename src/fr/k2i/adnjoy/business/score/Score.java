package fr.k2i.adnjoy.business.score;

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

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.user.User;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table (name="TBL_SCORE")
@DiscriminatorColumn(
    name="classe",
    discriminatorType=DiscriminatorType.STRING
)
public abstract class Score extends BusinessObject{

	private static final long serialVersionUID = -6278613553251971612L;

	private JackPot jackPot;
	private Double score;
	private User user;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="JACKPOT_ID")
	public JackPot getJackPot() {
		return jackPot;
	}

	public void setJackPot(JackPot jackPot) {
		this.jackPot = jackPot;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}
	
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	
}
