package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dwr.bean.ChanelBean;
import fr.k2i.adnjoy.dwr.bean.HomeJackPotChanelBean;

public interface ChanelManager extends IManager<ChanelDao,Chanel, Long>{

	List<ChanelBean> getAllFromUserCountry(User user, String filter)throws Exception;

	List<HomeJackPotChanelBean> getChanelsJackPot()throws Exception;

	List<ChanelBean> getAllBean(String filterChanel)throws Exception;

	ChanelBean getBeanById(Long id)throws Exception;

	void saveBean(ChanelBean c)throws Exception;

	void deletebyId(Long id)throws Exception;


}
