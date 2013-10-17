package fr.k2i.adnjoy.stripes.bean;

public class UserMessageBean {
	private long timeMsg;
	private String pseudo;
	private String msg;
	public long getTimeMsg() {
		return timeMsg;
	}
	public void setTimeMsg(long timeMsg) {
		this.timeMsg = timeMsg;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserMessageBean(long timeMsg, String pseudo, String msg) {
		super();
		this.timeMsg = timeMsg;
		this.pseudo = pseudo;
		this.msg = msg;
	}
}
