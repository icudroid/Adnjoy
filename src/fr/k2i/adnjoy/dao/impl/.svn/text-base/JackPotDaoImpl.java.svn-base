package fr.k2i.adnjoy.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.ad.AdBrandPossibiliites;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.NetJackPot;
import fr.k2i.adnjoy.business.jackpot.TVJackPot;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.JackPotDao;

public class JackPotDaoImpl extends BaseDao<JackPot, Long> implements JackPotDao {

	@Override
	public JackPot getNextJackPot() throws Exception {
		Calendar now = new GregorianCalendar();
		
		Calendar calBT = new GregorianCalendar(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH),now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),0);
		Calendar eBT = new GregorianCalendar(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH),now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),0);
		
//		int minute = calBT.get(Calendar.MINUTE);
		calBT.add(Calendar.MINUTE,1);
		eBT.add(Calendar.MINUTE,3);
		
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetJackPot.class);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", calBT.getTime()), Restrictions.lt("startDate", eBT.getTime())));
		
		return (JackPot) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JackPot> getByDay(Date start,Date end) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetJackPot.class);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", start), Restrictions.lt("startDate", end)));
		return criteria.list();
	}

	@Override
	public JackPot getNextJackPot(long idChanel) throws Exception {
		Calendar calBT = new GregorianCalendar();
		
//		Calendar calBT = new GregorianCalendar(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH),now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),0);
		Calendar eBT = new GregorianCalendar();
		eBT.setTimeInMillis(calBT.getTimeInMillis());
		
//		int minute = calBT.get(Calendar.MINUTE);
//		calBT.add(Calendar.MINUTE,1);
		eBT.add(Calendar.MINUTE,3);
		
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVJackPot.class);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", calBT.getTime()), Restrictions.lt("startDate", eBT.getTime())));
		System.out.println(calBT.getTime()+" "+eBT.getTime());
//		criteria.add(Restrictions.eq("startDate",calBT.getTime()));
		criteria.createAlias("chanel","chanel").add(Restrictions.eq("chanel.id",idChanel));
		
		return (JackPot) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JackPot> getNetByDay(Date start, Date end) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(NetJackPot.class);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", start), Restrictions.lt("startDate", end)));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JackPot> getTVByDay(Date start, Date end) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(TVJackPot.class);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", start), Restrictions.lt("startDate", end)));
		return criteria.list();
	}

	@Override
	public NetJackPot getNetById(long id) throws Exception {
		return (NetJackPot) this.getHibernateTemplate().load(NetJackPot.class, id);
	}

	@Override
	public TVJackPot getTVById(long id) throws Exception {
		return (TVJackPot) this.getHibernateTemplate().load(TVJackPot.class, id);
	}

	@Override
	public List<Ad> getJackPotAds(long idJackPot) throws Exception {
		List<Ad> res = new ArrayList<Ad>();
		JackPot j = getById(idJackPot);
		List<AdBrandPossibiliites> ads = j.getAds();
		
		for (AdBrandPossibiliites a : ads) {
			res.add(a.getAd());
		}
		
		return res;
		
	}


}
