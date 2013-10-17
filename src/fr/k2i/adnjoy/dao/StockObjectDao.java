package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.business.object.WinObject;

public interface StockObjectDao extends IBaseDao<StockObject, Long>{

	WinObject getRandomWinObjectAvailable(Double value)throws Exception;

	WinObject getRandomWinObjectAvailable(double value, List<Ad> ads)throws Exception;

	List<StockObject> getByBrandId(Long brandId)throws Exception;

}
