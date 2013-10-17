package fr.k2i.adnjoy.dwr.impl;

import java.util.List;

import fr.k2i.adnjoy.dwr.WonJackPotDwrManager;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.dwr.bean.HomeJackPotWinnerBean;
import fr.k2i.adnjoy.manager.WonJackPotManager;

public class WonJackPotDwrManagerImpl implements WonJackPotDwrManager {

	private WonJackPotManager wonJackPotManager;
	

	public WonJackPotManager getWonJackPotManager() {
		return wonJackPotManager;
	}

	public void setWonJackPotManager(WonJackPotManager wonJackPotManager) {
		this.wonJackPotManager = wonJackPotManager;
	}

	@Override
	public CallBackData public_getLastBtWinners() {
		CallBackData res = new CallBackData();
		try {
			List<HomeJackPotWinnerBean> list= wonJackPotManager.getLastBtWinners();
			res.setData(list);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData public_getLastTvWinners() {
		CallBackData res = new CallBackData();
		try {
			List<HomeJackPotWinnerBean> list= wonJackPotManager.getLastTvWinners();
			res.setData(list);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

}
