package fr.k2i.adnjoy.aop;

import java.util.EventObject;

public class PlayEvent extends EventObject {
	private static final long serialVersionUID = -4791715896552330077L;
	private CptUsers cptUsers;

	public CptUsers getCptUsers() {
		return cptUsers;
	}

	public PlayEvent(Object source, CptUsers cptUsers) {
		super(source);
		this.cptUsers = cptUsers;
	}
}