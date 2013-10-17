package fr.k2i.adnjoy.dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.dao.AdDao;
import fr.k2i.adnjoy.dao.BaseDao;

public class AdDaoImpl extends BaseDao<Ad, Long> implements AdDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ad> getAdsByBrand(Long brandId) throws Exception {
		
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ad.class);
		if(brandId!=null)
		criteria.add(Restrictions.eq("brand.id",brandId));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ad> getNewAds() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ad.class);
		
		Calendar current = new GregorianCalendar();
		Calendar today = new GregorianCalendar(current.get(Calendar.YEAR),current.get(Calendar.MONTH),current.get(Calendar.DAY_OF_MONTH));
		
		
		Calendar calStart = new GregorianCalendar();
		calStart.setTime(today.getTime());
		calStart.add(Calendar.DATE,((today.get(Calendar.DAY_OF_WEEK)==1)?-6:-today.get(Calendar.DAY_OF_WEEK)+2));

		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(calStart.getTime());
		calEnd.add(Calendar.DATE,7);
		criteria.add(Restrictions.and(Restrictions.ge("startDate", calStart.getTime()), Restrictions.lt("startDate", calEnd.getTime())));
		
		return criteria.list();
	}

	@Override
	public void delete(Long id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("delete from TBL_AD where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public Ad getByUid(String adUid) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ad.class);
		criteria.add(Restrictions.eq("uId", adUid));
		return (Ad) criteria.uniqueResult();
	}


}
