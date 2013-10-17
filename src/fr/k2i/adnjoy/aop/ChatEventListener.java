package fr.k2i.adnjoy.aop;

import java.util.EventListener;

public abstract class ChatEventListener implements EventListener {
	protected Long userId;
	
	
	public Long getUserId() {
		return userId;
	}

	public ChatEventListener(Long uId){
		super();
		this.userId = uId;
	}
	
	abstract public void messaged(ChatEvent evt);
}