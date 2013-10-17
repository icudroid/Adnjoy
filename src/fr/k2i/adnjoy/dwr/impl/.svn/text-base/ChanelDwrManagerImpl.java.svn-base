package fr.k2i.adnjoy.dwr.impl;

import java.util.List;

import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.ChanelDwrManager;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.dwr.bean.HomeJackPotChanelBean;
import fr.k2i.adnjoy.manager.ChanelManager;
import fr.k2i.adnjoy.manager.UserManager;

public class ChanelDwrManagerImpl implements ChanelDwrManager {

	private UserManager userManager;
	private ChanelManager chanelManager;
	

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setChanelManager(ChanelManager chanelManager) {
		this.chanelManager = chanelManager;
	}



	@Override
	public CallBackData getAllFromUserCountry(String filter) {
		CallBackData res = new CallBackData();
		try {
			User user = userManager.getUserFromDwrContext();
			res.setData(chanelManager.getAllFromUserCountry(user,filter));
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData public_getChanelsJackPot() {
		CallBackData res = new CallBackData();
		try {
			List<HomeJackPotChanelBean>	list = chanelManager.getChanelsJackPot();
			res.setData(list);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

}
