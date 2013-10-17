package fr.k2i.adnjoy.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BaseDao<OBJ, ID extends Serializable> extends HibernateDaoSupport
		implements IBaseDao<OBJ, ID> {

	private Class<OBJ> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		
		this.persistentClass = (Class<OBJ>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void delete(OBJ obj)throws Exception {
		this.getHibernateTemplate().delete(obj);
	}

	public List<OBJ> getAll()throws Exception {
		return (List<OBJ>) this.getHibernateTemplate().loadAll(this.persistentClass);
	}

	public OBJ getById(ID id) throws Exception{
			return (OBJ) this.getHibernateTemplate().load(this.persistentClass, id);
	}

	public void save(OBJ obj) throws Exception{
			this.getHibernateTemplate().saveOrUpdate(obj);
	}

}
