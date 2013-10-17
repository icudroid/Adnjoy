package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.jackpot.chanel.ChanelJackPot;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.CountryDao;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dwr.bean.ChanelBean;
import fr.k2i.adnjoy.dwr.bean.HomeJackPotChanelBean;
import fr.k2i.adnjoy.manager.ChanelManager;
import fr.k2i.adnjoy.manager.Manager;

public class ChanelManagerImpl extends Manager<ChanelDao, Chanel, Long> implements ChanelManager {

	private UserDao userDao;
	private CountryDao countryDao;
	private FileDao fileDao;

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}



	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}



	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	@Override
	public List<ChanelBean> getAllFromUserCountry(User u, String filter)
			throws Exception {
		List<ChanelBean> res = new ArrayList<ChanelBean>();
		Long idCountry = userDao.getById(u.getId()).getCountry().getId();
		List<Chanel>chanels=  dao.getAllFromCountry(idCountry,filter);
		for (Chanel chanel : chanels) {
			res.add(new ChanelBean(chanel,false));
		}
		return res;
	}



	@Override
	public List<HomeJackPotChanelBean> getChanelsJackPot() throws Exception {
		List<HomeJackPotChanelBean> res = new ArrayList<HomeJackPotChanelBean>();
		List<Chanel> all = dao.getAll();
		for (Chanel chanel : all) {
			res.add(new HomeJackPotChanelBean(chanel));		
		}
		return res;
	}



	@Override
	public List<ChanelBean> getAllBean(String filterChanel) throws Exception {
		List<ChanelBean> res = new ArrayList<ChanelBean>();
		List<Chanel>chanels=  dao.getByName(filterChanel);
		for (Chanel chanel : chanels) {
			res.add(new ChanelBean(chanel,true));
		}
		return res;
	}



	@Override
	public ChanelBean getBeanById(Long id) throws Exception {
		return new ChanelBean(dao.getById(id),true);
	}



	@Override
	public void saveBean(ChanelBean c) throws Exception {
		Chanel chanel = null;
		if(c.getId()==null){
			chanel = new Chanel();
			chanel.setCountry(countryDao.getById(c.getCountry().getId()));
			chanel.setName(c.getName());
			ChanelJackPot jp = new ChanelJackPot();
			jp.setValue(100.0);
			chanel.setJackpot(jp);
		}else{
			chanel = dao.getById(c.getId());
		}
		
		chanel.setDlFile(fileDao.getById(c.getLogoId()));
		chanel.setEmail(c.getEmail());
		chanel.setFax(c.getFax());
		chanel.setLogin(c.getLogin());
		chanel.setPassword(c.getPassword());
		chanel.setPhone(c.getPhone());
		dao.save(chanel);
	}



	@Override
	public void deletebyId(Long id) throws Exception {
		dao.delete(dao.getById(id));
	}

}
