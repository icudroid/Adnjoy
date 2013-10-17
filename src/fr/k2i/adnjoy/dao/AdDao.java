package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;

public interface AdDao extends IBaseDao<Ad, Long>{

	List<Ad> getAdsByBrand(Long brandId)throws Exception;

	List<Ad> getNewAds()throws Exception;

	void delete(Long id)throws Exception;

	Ad getByUid(String adUid)throws Exception;

}
