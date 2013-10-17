package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.Country;
import fr.k2i.adnjoy.dao.CountryDao;
import fr.k2i.adnjoy.dwr.bean.CountryBean;

public interface CountryManager extends IManager<CountryDao,Country, Long>{
	List<CountryBean> getCountriesBean()throws Exception;
	CountryBean getCountryBeanById(Long countryId)throws Exception;
}
