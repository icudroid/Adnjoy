package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.ad.Type;

public interface BrandDao extends IBaseDao<Brand, Long>{
	List<Brand> getBrandsLike(String query)throws Exception;

	List<Brand> findByTypeAd(Type type)throws Exception;
	/**
	 * retourne les compagnie d'un même type et qui ont le droit d'être affiché ensemble
	 * @param type
	 * @param brands
	 * @return
	 * @throws Exception
	 */
//	List<Brand> findByTypeAd(Type type, List<Brand> brands)throws Exception;

}
