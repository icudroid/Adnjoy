package fr.k2i.adnjoy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.user.Right;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.UserDao;

public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {

	public User getByLogin(String login) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return (User) session.createCriteria(User.class).add(
				Restrictions.eq("login", login)).uniqueResult();
	}

	@Override
	public User getByEmail(String email) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return (User) session.createCriteria(User.class).add(
				Restrictions.eq("email", email)).uniqueResult();
	}

	@Override
	public User getByPseudo(String pseudo) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return (User) session.createCriteria(User.class).add(
				Restrictions.eq("pseudo", pseudo)).uniqueResult();
	}

	@Override
	public User getByValidateNumber(String number) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return (User) session.createCriteria(User.class).add(
				Restrictions.eq("validateUrl", number)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WonJackPot> getWonJackPot(long idUser, int start, int limit)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(WonJackPot.class);
		criteria.createAlias("user", "user").add(Restrictions.eq("user.id", idUser));
		criteria.addOrder(Order.desc("winDate"));
		criteria.setFirstResult(start);
		if(limit!=0)
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getSimuationUser() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(User.class).add(
				Restrictions.eq("right", Right.Simulation)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> filterByPseudo(String pseudoFilter, int start, int limit)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ilike("pseudo",pseudoFilter,MatchMode.START));
		criteria.addOrder(Order.asc("pseudo"));
		criteria.setFirstResult(start);
		if(limit!=0)
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	@Override
	public Integer getCountUsersByPseudo(String pseudoFilter) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ilike("pseudo",pseudoFilter,MatchMode.START));
		criteria.setProjection( Projections.rowCount() );
		return (Integer) criteria.uniqueResult();
	}

}
