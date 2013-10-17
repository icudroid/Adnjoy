package fr.k2i.adnjoy.stripes.bean;

import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;

public class FriendBean {
	private long id;
	private String pseudo;
	private String avatar;
	private SexBean sexBean;
	private boolean validate;
	
	public FriendBean(UserFriend u) {
		id = u.getFriend().getId();
		pseudo = u.getFriend().getPseudo();
		avatar = u.getFriend().getAvator();
		sexBean = new SexBean(u.getFriend().getSex());
		validate = (u.getValid() == TypeValidation.Validated);
	}

	public FriendBean(User u) {
		id = u.getId();
		pseudo = u.getPseudo();
		avatar = u.getAvator();
		sexBean = new SexBean(u.getSex());
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public SexBean getSexBean() {
		return sexBean;
	}
	public void setSexBean(SexBean sexBean) {
		this.sexBean = sexBean;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	
}
