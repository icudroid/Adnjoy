package fr.k2i.adnjoy.dao;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;

public interface UserFriendDao extends IBaseDao<UserFriend, Long>{
	void deleteFriends(FriendsGroup fg, User user) throws Exception;

	void update(FriendsGroup friendsGroup, User okuser, TypeValidation validated)throws Exception;
}
