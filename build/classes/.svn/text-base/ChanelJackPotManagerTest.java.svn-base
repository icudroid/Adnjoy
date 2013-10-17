import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.chanel.ChanelJackPot;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.manager.ChanelManager;
import fr.k2i.adnjoy.manager.JackPotManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-*.xml")  
public class ChanelJackPotManagerTest {
	
	
    private ChanelManager chanelManager;
    
    
    @Resource
	public void setChanelManager(ChanelManager chanelManager) {
		this.chanelManager = chanelManager;
	}



	@Test
	public void testGenerateChanelJp() throws Exception {
		List<Chanel> all = chanelManager.getAll();
		for (Chanel chanel : all) {
			ChanelJackPot jp = chanel.getJackpot();
			if(jp==null){
				jp = new ChanelJackPot();
			}
			jp.setValue(100.0);
			chanel.setJackpot(jp);
			chanelManager.save(chanel);
		}
	}




}
