package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.dao.AdDao;
import fr.k2i.adnjoy.dwr.bean.AdBean;

public interface AdManager extends IManager<AdDao,Ad, Long>{

	void saveFromUI(AdBean ad)throws Exception;
	List<AdBean> getAdsByBrand(Long brandId)throws Exception;
	List<AdBean> getNewAds()throws Exception;
	List<fr.k2i.adnjoy.stripes.bean.AdBean> getAllBean(Long brandId)throws Exception;
	void deletebyId(Long id)throws Exception;
	fr.k2i.adnjoy.stripes.bean.AdBean getBeanById(Long id)throws Exception;
	void saveBean(fr.k2i.adnjoy.stripes.bean.AdBean ad)throws Exception;
}
