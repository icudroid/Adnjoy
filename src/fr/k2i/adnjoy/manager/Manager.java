package fr.k2i.adnjoy.manager;

import java.io.Serializable;
import java.util.List;

import fr.k2i.adnjoy.dao.IBaseDao;
@SuppressWarnings("unchecked")
public abstract class Manager <DAO,OBJ, ID extends Serializable> implements IManager<DAO,OBJ, ID> {
	
	protected DAO dao;
		
	public void setDao(DAO dao){
		this.dao = dao;
	}
	
	public void delete(OBJ obj) throws Exception {
		((IBaseDao<OBJ, ID>) dao).delete(obj);
	}

	public List<OBJ> getAll() throws Exception {
		return ((IBaseDao<OBJ, ID>) dao).getAll();
	}

	public OBJ getById(ID id) throws Exception {
		return ((IBaseDao<OBJ, ID>) dao).getById(id);
	}

	public void save(OBJ obj) throws Exception {
		((IBaseDao<OBJ, ID>) dao).save(obj);
	}


}
