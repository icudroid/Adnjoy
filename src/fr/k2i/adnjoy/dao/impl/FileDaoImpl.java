package fr.k2i.adnjoy.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.dao.BaseDao;
import fr.k2i.adnjoy.dao.FileDao;

public class FileDaoImpl extends BaseDao<File, Long> implements FileDao {
	@Override
	public File getByUrl(String url) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(File.class);
		criteria.add(Restrictions.eq("dlUrl", url));
		return (File) criteria.uniqueResult();
	}

}
