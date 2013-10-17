package fr.k2i.adnjoy.business.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NETSCORE")
public class NetScore extends Score {
	private static final long serialVersionUID = -4281942844650023116L;
}
