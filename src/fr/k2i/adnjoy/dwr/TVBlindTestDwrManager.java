package fr.k2i.adnjoy.dwr;

import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;

public interface TVBlindTestDwrManager {
	CallBackData inscriptionUser(long idChanel);
	CallBackData playGame(long idJackPot,long idBlindTest,int numAd,long idBrandResponse);
	CallBackData getClassement(long idJackpot);
	CallBackData getClassementByPage(GetClassementBean get);
	CallBackData getJackPotChanel(long idJackPot);
	CallBackData doResponseJackpot(long idJackPot,long idResponse);
	CallBackData toLateJackpot(long idJackPot);
}
