package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.score.Score;
import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;

public interface ScoreBlindTestDao extends IBaseDao<Score, Long>{
	Score getBlindTest(User user, long idJackPot)throws Exception;

	List<Score> getClassementJackPot(long idJackPot, int start, int limit)throws Exception;

	List<Score> getClassementJackPot(GetClassementBean get)throws Exception;

	Integer getCountPlayer(long idJackPot)throws Exception;

	Integer getClassementJackPot(long idJackPot, Score scoreUser)throws Exception;

	List<TVScore> getFriendsScore(List<User> users, Long idJackPot)throws Exception;

}
