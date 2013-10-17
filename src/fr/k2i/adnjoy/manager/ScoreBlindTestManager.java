package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.score.Score;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.JackPotClassementBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.stripes.bean.ClassementBean;

public interface ScoreBlindTestManager extends IManager<ScoreBlindTestDao,Score, Long>{
	BlindTestBean inscriptionBlindTest(User user)throws Exception;
	ScoreBean responseUser(long idBlindTest,int numAd,long idBrand)throws Exception;
	ClassementBean getClassment(User u,long idJackPot) throws Exception;
	List<JackPotClassementBean> getClassment(long idJackpot, int start, int limit)throws Exception;
}
