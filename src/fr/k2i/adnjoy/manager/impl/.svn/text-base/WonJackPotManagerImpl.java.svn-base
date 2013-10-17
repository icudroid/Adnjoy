package fr.k2i.adnjoy.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.jackpot.won.NetWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.TVWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.dao.WonJackPotDao;
import fr.k2i.adnjoy.dwr.bean.HomeJackPotWinnerBean;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.WonJackPotManager;

public class WonJackPotManagerImpl extends Manager<WonJackPotDao,WonJackPot, Long> implements WonJackPotManager {

	@Override
	public List<HomeJackPotWinnerBean> getLastBtWinners() throws Exception {
		List<HomeJackPotWinnerBean> res = new ArrayList<HomeJackPotWinnerBean>();
		List<NetWonJackPot> list = dao.getLastBtWinners();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for (NetWonJackPot wj : list) {
			HomeJackPotWinnerBean hjw = new HomeJackPotWinnerBean();
			hjw.setCurrency("&euro;");
			hjw.setLotName(wj.getValue().getName());
			hjw.setLotValue(wj.getValue().getValue());
			hjw.setPseudo(wj.getUser().getPseudo());
			hjw.setDt(sdf.format(wj.getWinDate()));
			res.add(hjw);
		}
		return res;
	}

	@Override
	public List<HomeJackPotWinnerBean> getLastTvWinners() throws Exception {
		List<HomeJackPotWinnerBean> res = new ArrayList<HomeJackPotWinnerBean>();
		List<TVWonJackPot> list = dao.getLastTvWinners();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for (TVWonJackPot wj : list) {
			HomeJackPotWinnerBean hjw = new HomeJackPotWinnerBean();
			hjw.setCurrency("&euro;");
			hjw.setLotName(wj.getValue().getName());
			hjw.setLotValue(wj.getValue().getValue());
			hjw.setPseudo(wj.getUser().getPseudo());
			hjw.setWinOnImg(wj.getScheduleAd().getChanel().getDlFile().getDlUrl());
			hjw.setDt(sdf.format(wj.getWinDate()));
			res.add(hjw);
		}
		return res;
	}


}
