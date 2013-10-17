package fr.k2i.adnjoy.dao;

import java.util.Date;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.NetJackPot;
import fr.k2i.adnjoy.business.jackpot.TVJackPot;

public interface JackPotDao extends IBaseDao<JackPot, Long>{

	JackPot getNextJackPot()throws Exception;

	JackPot getNextJackPot(long idChanel)throws Exception;

	List<JackPot> getByDay(Date start,Date end) throws Exception;
	List<JackPot> getNetByDay(Date start, Date end)throws Exception;
	List<JackPot> getTVByDay(Date start, Date end)throws Exception;

	TVJackPot getTVById(long idJackPot)throws Exception;
	NetJackPot getNetById(long idJackPot)throws Exception;

	List<Ad> getJackPotAds(long idJackPot)throws Exception;

}
