package fr.k2i.adnjoy.business.jackpot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NetJackPot")
public class NetJackPot extends JackPot{
	private static final long serialVersionUID = 936850576277962981L;
}
