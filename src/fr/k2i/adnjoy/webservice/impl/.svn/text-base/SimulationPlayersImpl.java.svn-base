package fr.k2i.adnjoy.webservice.impl;

import java.util.List;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.webservice.SimulationPlayers;

@WebService(endpointInterface = "fr.k2i.adnjoy.webservice.SimulationPlayers")
public class SimulationPlayersImpl implements SimulationPlayers {

	private UserDao userDao;
	private JackPotDao jackPotDao;
	private ChanelDao chanelDao;
	private ScoreBlindTestDao scoreBlindTestDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setJackPotDao(JackPotDao jackPotDao) {
		this.jackPotDao = jackPotDao;
	}

	public void setChanelDao(ChanelDao chanelDao) {
		this.chanelDao = chanelDao;
	}

	public void setScoreBlindTestDao(ScoreBlindTestDao scoreBlindTestDao) {
		this.scoreBlindTestDao = scoreBlindTestDao;
	}

	@WebMethod
	public Boolean simulation() {
		JackPot nextJackPot;
		try {
			nextJackPot = jackPotDao.getNextJackPot(chanelDao.getByName(
					"France 2").get(0).getId());
			List<User> users = userDao.getSimuationUser();
			for (User user : users) {

				if (nextJackPot == null) {
					return false;
				}
				// TVScore blindTest = (TVScore)
				// scoreBlindTestDao.getBlindTest(user, nextJackPot);

				// if(blindTest == null){
				TVScore blindTest = new TVScore();
				user.getScores().add(blindTest);
				blindTest.setUser(user);
				blindTest.setJackPot(nextJackPot);
				blindTest.setScore(0.0);

				Random random = new Random();
				blindTest.setScore(random.nextInt(80) + random.nextInt(100)
						/ 100.0);
				scoreBlindTestDao.save(blindTest);
				// }

				userDao.save(user);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
