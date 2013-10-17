package fr.k2i.adnjoy.business.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table(name = "TBL_USER_FRIEND")
public class UserFriend extends BusinessObject {

	private static final long serialVersionUID = -916680480087386882L;
	
	private TypeValidation valid;
	private User friend;
	private FriendsGroup friendsGroup;
	
	public UserFriend(){}
	
	public UserFriend(User user, TypeValidation valid, FriendsGroup fg) {
		friend = user;
		this.valid = valid;
		friendsGroup = fg;
	}

	@Enumerated(EnumType.ORDINAL)
	public TypeValidation getValid() {
		return valid;
	}

	public void setValid(TypeValidation valid) {
		this.valid = valid;
	}
	
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	@JoinColumn(name="FRIEND_ID")
	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	@ManyToOne
    @JoinColumn(name="friendsGroup_fk")
	public FriendsGroup getFriendsGroup() {
		return friendsGroup;
	}

	public void setFriendsGroup(FriendsGroup friendsGroup) {
		this.friendsGroup = friendsGroup;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getFriend() == null) ? 0 : getFriend().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFriend other = (UserFriend) obj;
		if (getFriend() == null) {
			if (other.getFriend() != null)
				return false;
		} else if (!getFriend().equals(other.getFriend()))
			return false;
		return true;
	}

}
