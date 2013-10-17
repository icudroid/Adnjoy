package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.dao.BrandDao;
import fr.k2i.adnjoy.dwr.bean.BrandBean;

public interface BrandManager extends IManager<BrandDao,Brand, Long>{

	List<BrandBean> getBrandsLike(String query)throws Exception;

	List<BrandBean> getAllJsonObj()throws Exception;

	BrandBean getBeanById(Long id)throws Exception;

	void deletebyId(Long id)throws Exception;

	void saveBean(BrandBean b)throws Exception;

	void addNoTogether(Long id, Long idNoBrand)throws Exception;

	void deleteNoTogetherBrand(Long idBrand, Long idNoBrand)throws Exception;

	List<BrandBean> getNoTogetherBrands(Long idBrand)throws Exception;

}
