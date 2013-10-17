package fr.k2i.adnjoy.dwr.impl;

import java.util.List;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.dwr.AdDwrManager;
import fr.k2i.adnjoy.dwr.bean.AdBean;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.manager.AdManager;
import fr.k2i.adnjoy.manager.FileManager;

public class AdDwrManagerImpl implements AdDwrManager {
	private AdManager adManager;

	public void setAdManager(AdManager adManager) {
		this.adManager = adManager;
	}

	private FileManager fileManager;
	
	
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}


	@Override
	public CallBackData adm_deleteTmpFile(String url) {
		CallBackData res = new CallBackData();
		try {
			File byUrl = fileManager.getByUrl(url);
			new java.io.File(byUrl.getFile()).delete();
			fileManager.delete(byUrl);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData adm_saveAd(AdBean ad) {
		CallBackData res = new CallBackData();
		try {
			adManager.saveFromUI(ad);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public CallBackData adm_getAdsByBrand(Long brandId) {
		CallBackData res = new CallBackData();
		try {
			res.setData(adManager.getAdsByBrand(brandId));
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public CallBackData adm_deleteAd(long idAd) {
		CallBackData res = new CallBackData();
		try {
			adManager.delete(adManager.getById(idAd));
			res.setData(true);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public CallBackData public_getNewAds() {
		CallBackData res = new CallBackData();
		try {
			List<AdBean> ads = adManager.getNewAds();
			res.setData(ads);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}
	



}
