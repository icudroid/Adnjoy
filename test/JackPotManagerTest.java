import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.manager.JackPotManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-*.xml")  
public class JackPotManagerTest {
	
	
	
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
	
    private JackPotManager jackPotManager;
    
    
    @Resource
	public void setJackPotManager(JackPotManager jackPotManager) {
		this.jackPotManager = jackPotManager;
	}


//
//	@Test
//	public void testGenerateNetJackPot() throws Exception {
//		jackPotManager.generateDayJackPots(new GregorianCalendar(2010,5,16),1,3);
//	}
//
//	@Test
//	public void testGenerateTVJackPot() throws Exception {
//		jackPotManager.generateDayTVJackPots(new GregorianCalendar(2010,5,16),1,3);
//	}

    @Transactional
	@Test
	public void testNext() throws Exception {
		JackPot nextJackPot = jackPotDao.getNextJackPot(chanelDao.getByName("France 2").get(0).getId());
		System.out.println(nextJackPot.getStartDate());
	}


}
