package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.jackpot.won.NetWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.TVWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;

public interface WonJackPotDao extends IBaseDao<WonJackPot, Long>{

	TVWonJackPot getUserTvWonJackPot(Long id, long idJackPot)throws Exception;
	NetWonJackPot getUserNetWonJackPot(Long id, long idJackPot)throws Exception;

	List<NetWonJackPot> getLastBtWinners()throws Exception;
	
	List<TVWonJackPot> getLastTvWinners()throws Exception;
	NetWonJackPot getNetWonJackPot(Long idJackPot)throws Exception;
	TVWonJackPot getTVWonJackPot(Long idJackPot)throws Exception;

}
