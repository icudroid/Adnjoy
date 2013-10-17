package fr.k2i.adnjoy.dao.impl;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.FriendsGroupDao;

public class FriendsGroupDaoImpl extends BaseDao<FriendsGroup, Long> implements FriendsGroupDao {

	@Override
	public void deleteFriends(FriendsGroup fg, User user) throws Exception {
		String hql = "delete from UserFriend where friendsGroup = :fg and friend = :user";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("fg", fg);
		query.setParameter("user", user);
		query.executeUpdate();
	}
}
