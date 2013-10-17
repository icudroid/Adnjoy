package fr.k2i.adnjoy.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.business.object.WinObject;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.StockObjectDao;

public class StockObjectDaoImpl extends BaseDao<StockObject, Long> implements StockObjectDao {
	@SuppressWarnings("unchecked")
	@Override
	public synchronized WinObject getRandomWinObjectAvailable(Double value) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(StockObject.class);
		criteria.add(Restrictions.gt("available", (long) 1));
		criteria.add(Restrictions.le("value", value) );
		
		List<StockObject> list = criteria.list();
		Random r = new Random();
		
		StockObject obj = (StockObject) list.get(r.nextInt(list.size()));
		WinObject winObject = new WinObject(obj);
		save(obj);
		return winObject;
	}

	@Override
	public WinObject getRandomWinObjectAvailable(double value, List<Ad> ads)
			throws Exception {
		List<Long> ins = new ArrayList<Long>();
		for (Ad a : ads) {
			ins.add(a.getId());
		}
		WinObject winobj = getRandomWinObjectAvailableNoReq(value,ins,false,10);
		if(winobj == null){
			 winobj = getRandomWinObjectAvailableNoReq(value,ins,true,1000);
		}
		
		return winobj;
	}
	
	@SuppressWarnings("unchecked")
	private WinObject getRandomWinObjectAvailableNoReq(double value, List<Long> ins,boolean withAdBeBack,int iteration)throws Exception {
		if(iteration == 0) return null;
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(StockObject.class);
		criteria.add(Restrictions.gt("available", (long) 1));
		criteria.add(Restrictions.le("value", value+(value/10)));
		criteria.add(Restrictions.ge("value", value-(value/10)));
		
		if(withAdBeBack ==false){
			criteria.createAlias("brand.ads","ads",Criteria.LEFT_JOIN);
			criteria.add(Restrictions.in("ads.id",ins));
		}else{
			criteria.createAlias("brand.ads","ads",Criteria.LEFT_JOIN);
			criteria.add(Restrictions.or(Restrictions.isNull("brand"), Restrictions.in("ads.id",ins)));
		}

		List<StockObject> list = criteria.list();
		if(list.size()==0){
			return getRandomWinObjectAvailableNoReq(value+(value/10),ins,withAdBeBack,--iteration);
		}else{
			Random r = new Random();
			
			StockObject obj = (StockObject) list.get(r.nextInt(list.size()));
			WinObject winObject = new WinObject(obj);
			save(obj);
			return winObject;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockObject> getByBrandId(Long brandId) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(StockObject.class);
		if(brandId ==null){
			criteria.add(Restrictions.isNull("brand"));
		}else{
			criteria.createAlias("brand","brand").add(Restrictions.eq("brand.id", brandId));
		}
		return criteria.list();
	}

}
