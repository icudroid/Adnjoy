package fr.k2i.adnjoy.dwr.impl;

import fr.k2i.adnjoy.dwr.BrandDwrManager;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.manager.BrandManager;

public class BrandDwrManagerImpl implements BrandDwrManager {

	private BrandManager brandManager ;
	
	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	@Override
	public CallBackData adm_getBrandsLike(String query) {
		CallBackData res = new CallBackData();
		try {
			res.setData(brandManager.getBrandsLike(query));
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

}
