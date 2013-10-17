package fr.k2i.adnjoy.aop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.k2i.adnjoy.stripes.bean.UserMessageBean;

public class CptUsersMessage {
	private Long cpt = 0L;
	private List<Long>usersId = new ArrayList<Long>();
	private Map<Long, UserMessageBean>messages = new HashMap<Long, UserMessageBean>();
	
	public Map<Long, UserMessageBean> getMessages() {
		return messages;
	}
	public void setMessages(Map<Long, UserMessageBean> messages) {
		this.messages = messages;
	}
	public Long getCpt() {
		return cpt;
	}
	public void setCpt(Long cpt) {
		this.cpt = cpt;
	}
	public List<Long> getUsersId() {
		return usersId;
	}
	public void setUsersId(List<Long> usersId) {
		this.usersId = usersId;
	}
//	public synchronized void incrementCpt() {
//		cpt++;
//	}
	
	public List<UserMessageBean> getMessages(Long version){
		List<UserMessageBean> msgs = new ArrayList<UserMessageBean>();
		for(long v = (version+1);v<=cpt;v++){
			UserMessageBean m = messages.get(v);
			if(m!=null){
				msgs.add(m);
			}
		}
		return msgs;
	}
	
	public void addMessage(UserMessageBean msgUser){
		cpt++;
		messages.put(cpt, msgUser);
		if(messages.size()>10){
			messages.remove(cpt-10);
		}
	}
}
