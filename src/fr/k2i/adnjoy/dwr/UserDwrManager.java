package fr.k2i.adnjoy.dwr;

import fr.k2i.adnjoy.dwr.bean.CallBackData;


public interface UserDwrManager {
	CallBackData public_checkUniquePseudo(String pseudo);
	CallBackData public_checkUniqueEmail(String email);
	CallBackData public_connectUser(String pseudo,String password);
	CallBackData public_sendPassorwd(String email);
	CallBackData disconnect();
	CallBackData getWonObjects();
	CallBackData public_getSexes();
}
