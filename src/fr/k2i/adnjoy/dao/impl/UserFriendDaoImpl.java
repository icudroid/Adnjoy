package fr.k2i.adnjoy.dao.impl;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.UserFriendDao;

public class UserFriendDaoImpl extends BaseDao<UserFriend, Long> implements UserFriendDao {

	@Override
	public void deleteFriends(FriendsGroup fg, User user) throws Exception {
		String hql = "delete from UserFriend where friendsGroup = :fg and friend = :user";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("fg", fg);
		query.setParameter("user", user);
		query.executeUpdate();
	}

	@Override
	public void update(FriendsGroup fg, User user,
			TypeValidation validated) throws Exception {
		String hql = "update UserFriend set valid=:typeValide where friendsGroup = :fg and friend = :user";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("fg", fg);
		query.setParameter("user", user);
		query.setParameter("typeValide", validated);
		query.executeUpdate();
	}
}
