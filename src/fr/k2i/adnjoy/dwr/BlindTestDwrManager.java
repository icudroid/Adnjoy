package fr.k2i.adnjoy.dwr;

import fr.k2i.adnjoy.dwr.bean.CallBackData;

public interface BlindTestDwrManager {
	CallBackData inscriptionUser();
	CallBackData playGame(long idBlindTest,int numAd,long idBrandResponse);
//	CallBackData getResultGame(long idJackPot);
	CallBackData getClassement(long idJackpot);
}
