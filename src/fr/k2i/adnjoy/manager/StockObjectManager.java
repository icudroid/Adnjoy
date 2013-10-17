package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.dao.StockObjectDao;
import fr.k2i.adnjoy.dwr.bean.StockObjectBean;

public interface StockObjectManager extends IManager<StockObjectDao,StockObject, Long>{

	List<StockObjectBean> getByBrandId(Long brandId)throws Exception;
	StockObjectBean getBeanById(Long idStockObject)throws Exception;
	void saveBean(StockObjectBean so, Integer addNb)throws Exception;
	void deletebyId(Long id)throws Exception;
}
