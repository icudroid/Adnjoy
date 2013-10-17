package fr.k2i.adnjoy.dwr.impl;

import fr.k2i.adnjoy.dwr.CountryDwrManager;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.manager.CountryManager;

public class CountryDwrManagerImpl implements CountryDwrManager {

	private CountryManager countryManager;
	
	
	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}


	public CallBackData public_getAll() {
		CallBackData res = new CallBackData();
		try {
			res.setData(countryManager.getCountriesBean());
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

}
