package fr.k2i.adnjoy.dwr;

import fr.k2i.adnjoy.dwr.bean.AdBean;
import fr.k2i.adnjoy.dwr.bean.CallBackData;



public interface AdDwrManager {
	CallBackData adm_deleteTmpFile(String url);
	CallBackData adm_saveAd(AdBean ad);
	CallBackData adm_getAdsByBrand(Long brandId);
	CallBackData adm_deleteAd(long idAd);
	CallBackData public_getNewAds();
}
