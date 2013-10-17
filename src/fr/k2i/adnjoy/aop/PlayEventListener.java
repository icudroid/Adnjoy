package fr.k2i.adnjoy.aop;

import java.util.EventListener;

public abstract class PlayEventListener implements EventListener {
	protected Long userId;
	protected Long idJackPot;
	
	
	public Long getIdJackPot() {
		return idJackPot;
	}

	public Long getUserId() {
		return userId;
	}

	public PlayEventListener(Long idJackPot,Long uId){
		super();
		this.userId = uId;
		this.idJackPot = idJackPot;
	}
	
	abstract public void played(PlayEvent evt);
}