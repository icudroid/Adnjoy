package fr.k2i.adnjoy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.score.Score;
import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;

public class ScoreBlindTestDaoImpl extends BaseDao<Score, Long> implements ScoreBlindTestDao {

	@Override
	public Score getBlindTest(User user, long jackpot)throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Score.class);
		Criteria aliasUser = criteria.createAlias("user", "user");
		aliasUser.add(Restrictions.eq("user.id", user.getId()));
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", jackpot));
		return (Score) criteria.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Score> getClassementJackPot(GetClassementBean get)
			throws Exception {
		int start = get.getStartIndex();
		int end = start + get.getResults();
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Score.class);
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", get.getIdJackPot()));
		criteria.addOrder(Order.desc("score"));
		criteria.setFirstResult(start).setMaxResults(end);
		return criteria.list();
	}

	@Override
	public Integer getCountPlayer(long idJackPot) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Score.class);
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", idJackPot));
		criteria.setProjection( Projections.rowCount() );

		return (Integer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Score> getClassementJackPot(long idJackPot, int start, int limit)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Score.class);
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", idJackPot));
		criteria.addOrder(Order.desc("score"));
		criteria.setFirstResult(start);
		if(limit!=0)
		criteria.setMaxResults(limit);
		return criteria.list();
	}


	@Override
	public Integer getClassementJackPot(long idJackPot, Score scoreUser)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Score.class);
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", idJackPot));
		criteria.add(Restrictions.ge("score", scoreUser.getScore()));
		criteria.addOrder(Order.desc("score"));
		criteria.setProjection( Projections.rowCount() );
		return (Integer) criteria.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TVScore> getFriendsScore(List<User> users, Long idJackPot)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVScore.class);
		Criteria aliasJackpot = criteria.createAlias("jackPot", "jackpot");
		aliasJackpot.add(Restrictions.eq("jackpot.id", idJackPot));
		criteria.add(Restrictions.in("user", users));
		criteria.addOrder(Order.desc("score"));
		return criteria.list();
	}



}
