package fr.k2i.adnjoy.business.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.k2i.adnjoy.business.BusinessObject;

@Entity
@Table(name = "TBL_FRIENDS_GROUP")
public class FriendsGroup extends BusinessObject {

	private static final long serialVersionUID = 1870132504423647460L;

	private String name;
	private List<UserFriend> friends;
	private FriendsGroupsRigth rigth;

	@Column(nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="friendsGroup")
	public List<UserFriend> getFriends() {
		return friends;
	}

	public void setFriends(List<UserFriend> friends) {
		this.friends = friends;
	}

	@Enumerated(EnumType.ORDINAL)
	public FriendsGroupsRigth getRigth() {
		return rigth;
	}

	public void setRigth(FriendsGroupsRigth rigth) {
		this.rigth = rigth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
