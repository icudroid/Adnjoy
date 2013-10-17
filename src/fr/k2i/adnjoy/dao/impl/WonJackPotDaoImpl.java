package fr.k2i.adnjoy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.jackpot.won.NetWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.TVWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.WonJackPotDao;

public class WonJackPotDaoImpl extends BaseDao< WonJackPot, Long> implements  WonJackPotDao {

	@Override
	public NetWonJackPot getUserNetWonJackPot(Long idUser, long idJackPot)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetWonJackPot.class);
		criteria.createAlias("user", "user").add(Restrictions.eq("user.id",idUser));
		criteria.createAlias("jackPot", "jackPot").add(Restrictions.eq("jackPot.id",idJackPot));
		return (NetWonJackPot)criteria.uniqueResult();
	}

	@Override
	public TVWonJackPot getUserTvWonJackPot(Long idUser, long idJackPot)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVWonJackPot.class);
		criteria.createAlias("user", "user").add(Restrictions.eq("user.id",idUser));
		criteria.createAlias("scheduleAd", "scheduleAd").add(Restrictions.eq("scheduleAd.id",idJackPot));
		return (TVWonJackPot)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetWonJackPot> getLastBtWinners() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetWonJackPot.class);
		criteria.createAlias("jackPot", "jackPot");
		criteria.createAlias("value", "value");
		criteria.addOrder(Order.desc("jackPot.startDate"));
		criteria.addOrder(Order.desc("value.value"));
		criteria.setMaxResults(24);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TVWonJackPot> getLastTvWinners() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVWonJackPot.class);
		criteria.createAlias("scheduleAd", "jackPot");
		criteria.createAlias("value", "value");
		criteria.addOrder(Order.desc("jackPot.startDate"));
		criteria.addOrder(Order.desc("value.value"));
		criteria.setMaxResults(24);
		return criteria.list();
	}

	@Override
	public NetWonJackPot getNetWonJackPot(Long idJackPot) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetWonJackPot.class);
		criteria.createAlias("jackPot", "jackPot").add(Restrictions.eq("jackPot.id",idJackPot));
		return (NetWonJackPot)criteria.uniqueResult();
	}

	@Override
	public TVWonJackPot getTVWonJackPot(Long idJackPot) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVWonJackPot.class);
		criteria.createAlias("scheduleAd", "scheduleAd").add(Restrictions.eq("scheduleAd.id",idJackPot));
		return (TVWonJackPot)criteria.uniqueResult();
	}

}
