package fr.k2i.adnjoy.manager;

import java.util.Calendar;

import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.webservice.TVBlindTest;
import fr.k2i.adnjoy.dao.JackPotDao;

public interface JackPotManager extends IManager<JackPotDao,JackPot, Long>{
	static final int DEFAULT_AD = 8;
	static final int DEFAULT_POSSIBILITES = 3;
	
	void generateDayJackPots()throws Exception;
	public void generateDayJackPots(Calendar genDay,int nbAds,int nbPossibilities) throws Exception;
	JackPot getNextJackPot()throws Exception;
	void generateDayTVJackPots(Calendar genDay,int nbAds,int nbPossibilities)throws Exception;
	Boolean describeAdSpace(TVBlindTest bt)throws Exception;
}
