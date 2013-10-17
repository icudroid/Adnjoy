package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.Country;
import fr.k2i.adnjoy.dao.CountryDao;
import fr.k2i.adnjoy.dwr.bean.CountryBean;
import fr.k2i.adnjoy.manager.CountryManager;
import fr.k2i.adnjoy.manager.Manager;

public class CountryManagerImpl extends Manager<CountryDao, Country, Long> implements
		CountryManager {

	public List<CountryBean> getCountriesBean() throws Exception {
		List<Country> all = dao.getAll();
		List<CountryBean> res = new ArrayList<CountryBean>();
		for (Country country : all) {
			CountryBean c = new CountryBean();
			c.setCountry(country.getName());
			c.setId(country.getId());
			res.add(c);
		}
		return res;
	}

	@Override
	public CountryBean getCountryBeanById(Long countryId) throws Exception {
		return new CountryBean(dao.getById(countryId));
	}

	
}
