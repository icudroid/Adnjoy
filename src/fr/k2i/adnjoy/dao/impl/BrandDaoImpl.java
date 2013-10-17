package fr.k2i.adnjoy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.ad.Type;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.BrandDao;

public class BrandDaoImpl extends BaseDao<Brand, Long> implements BrandDao {

	@SuppressWarnings("unchecked")
	public List<Brand> getBrandsLike(String query) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Brand.class);
		if(query != null)
			criteria.add(Restrictions.like("name", query,MatchMode.START));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> findByTypeAd(Type type) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Brand.class);
		criteria.createAlias("ads","ad").add(Restrictions.eq("ad.type.id",type.getId()));
		return criteria.list();
	}

//	@SuppressWarnings("unchecked")
//	public List<Brand> findByTypeAd(Type type,Brand brand) throws Exception {
//		Session session = getSessionFactory().getCurrentSession();
//		Criteria criteria = session.createCriteria(Brand.class);
//		criteria.createAlias("ads","ad").add(Restrictions.eq("ad.type.id",type.getId()));
//		
//		List<Long>no = new ArrayList<Long>();
//		List<NoShowWith> noTogether = brand.getNoTogether();
//		for (NoShowWith noShowWith : noTogether) {
//			no.add(noShowWith.getBrandDst().getId());	
//		}
//		no.addAll(noTogether(brand));
//		if(no.size()>0){
//			criteria.add(Restrictions.not(Restrictions.in("id", no)));
//		}
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
//	}
	
//	@SuppressWarnings("unchecked")
//	private List<Long>noTogether(Brand brand){
//		List<Long> res = new ArrayList<Long>();
//		Session session = getSessionFactory().getCurrentSession();
//		Criteria criteria = session.createCriteria(NoShowWith.class);
//		criteria.add(Restrictions.eq("brandDst.id", brand.getId()));
//		List<NoShowWith> no = criteria.list();
//		
//		for (NoShowWith noShowWith : no) {
//			res.add(noShowWith.getBrandSrc().getId());
//		}
//		return res;
//	}
//	
//	@Override
//	public List<Brand> findByTypeAd(Type type, List<Brand> brands)
//			throws Exception {
//		Session session = getSessionFactory().getCurrentSession();
//		Criteria criteria = session.createCriteria(Brand.class);
//		criteria.createAlias("ads","ad").add(Restrictions.eq("ad.type.id",type.getId()));
//		
//		List<Long>no = new ArrayList<Long>();
//		List<NoShowWith> noTogether = brand.getNoTogether();
//		for (NoShowWith noShowWith : noTogether) {
//			no.add(noShowWith.getBrandDst().getId());	
//		}
//		no.addAll(noTogether(brand));
//		if(no.size()>0){
//			criteria.add(Restrictions.not(Restrictions.in("id", no)));
//		}
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
//	}


}
