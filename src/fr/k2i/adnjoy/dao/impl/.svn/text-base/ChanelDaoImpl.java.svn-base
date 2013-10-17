package fr.k2i.adnjoy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.ChanelDao;

public class ChanelDaoImpl extends BaseDao<Chanel, Long> implements ChanelDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Chanel> getAllFromCountry(Long idCountry, String filter)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Chanel.class);
		criteria.createAlias("country","country").add(Restrictions.eq("country.id",idCountry));
		criteria.add(Restrictions.ilike("name", filter, MatchMode.START));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chanel> getByName(String startName) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Chanel.class);
		if(startName != null)
			criteria.add(Restrictions.ilike("name", startName, MatchMode.START));
		return criteria.list();
	}

	@Override
	public Chanel getConnectChanel(String chanelId, String password)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Chanel.class);
		
		criteria.add(Restrictions.ilike("login", chanelId, MatchMode.START));
		criteria.add(Restrictions.ilike("password", password, MatchMode.START));
		
		return (Chanel) criteria.uniqueResult();
	}



}
