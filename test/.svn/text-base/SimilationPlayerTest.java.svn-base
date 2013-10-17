import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-*.xml")  
public class SimilationPlayerTest {
	
	
    private UserDao userDao;
    private JackPotDao jackPotDao;
    private ChanelDao chanelDao;
    private ScoreBlindTestDao scoreBlindTestDao;
    
    @Resource
    public void setScoreBlindTestDao(ScoreBlindTestDao scoreBlindTestDao) {
		this.scoreBlindTestDao = scoreBlindTestDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
    @Resource
	public void setJackPotDao(JackPotDao jackPotDao) {
		this.jackPotDao = jackPotDao;
	}
    
    @Resource
	public void setChanelDao(ChanelDao chanelDao) {
		this.chanelDao = chanelDao;
	}

    @Transactional
	@Test
	public void testSimulation() throws Exception {
		JackPot nextJackPot = jackPotDao.getNextJackPot(chanelDao.getByName("France 2").get(0).getId());
		List<User>users = userDao.getSimuationUser();
		for (User user : users) {
			
			if(nextJackPot == null){
				return;
			}
//			TVScore blindTest = (TVScore) scoreBlindTestDao.getBlindTest(user, nextJackPot);
			
//			if(blindTest == null){
				TVScore blindTest = new TVScore();
				user.getScores().add(blindTest);
				blindTest.setUser(user);
				blindTest.setJackPot(nextJackPot);
				blindTest.setScore(0.0);
				
				Random random = new Random();
				blindTest.setScore(random.nextInt(80)+random.nextInt(100)/100.0);
				scoreBlindTestDao.save(blindTest);
//			}
			
			userDao.save(user);
		}
		
	}



}
