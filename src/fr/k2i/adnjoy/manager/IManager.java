package fr.k2i.adnjoy.manager;

import java.io.Serializable;
import java.util.List;

public interface IManager <DAO,OBJ, ID extends Serializable>{
	public List<OBJ> getAll()throws Exception;
	public OBJ getById(ID id)throws Exception;
	public void save(OBJ obj)throws Exception;
	public void delete(OBJ obj)throws Exception;
}
