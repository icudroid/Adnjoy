package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.ad.AdBrandPossibiliites;
import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.won.NetWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.object.WinObject;
import fr.k2i.adnjoy.business.score.NetScore;
import fr.k2i.adnjoy.business.score.Score;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.StockObjectDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dao.WinObjectDao;
import fr.k2i.adnjoy.dao.WonJackPotDao;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.JackPotClassementBean;
import fr.k2i.adnjoy.dwr.bean.QuestionBean;
import fr.k2i.adnjoy.dwr.bean.ResponseBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.ScoreBlindTestManager;
import fr.k2i.adnjoy.stripes.bean.ClassementBean;
import fr.k2i.adnjoy.stripes.bean.LotBean;

public class ScoreBlindTestManagerImpl extends Manager<ScoreBlindTestDao, Score, Long> implements ScoreBlindTestManager {
	private UserDao userDao;
	private JackPotDao jackPotDao;
	private StockObjectDao stockObjectDao;
	private WinObjectDao winObjectDao;
	private WonJackPotDao wonJackPotDao;
	
	

	public void setWonJackPotDao(WonJackPotDao wonJackPotDao) {
		this.wonJackPotDao = wonJackPotDao;
	}

	public void setWinObjectDao(WinObjectDao winObjectDao) {
		this.winObjectDao = winObjectDao;
	}

	public void setStockObjectDao(StockObjectDao stockObjectDao) {
		this.stockObjectDao = stockObjectDao;
	}

	public void setJackPotDao(JackPotDao jackPotDao) {
		this.jackPotDao = jackPotDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public BlindTestBean inscriptionBlindTest(User u) throws Exception {
//		List<Brand> brands = brandDao.getAll();
		User user = userDao.getById(u.getId());
		JackPot nextJackPot = jackPotDao.getNextJackPot();
		NetScore blindTest = (NetScore) dao.getBlindTest(user, nextJackPot.getId());
		
		if(blindTest == null){
			blindTest = new NetScore();
			user.getScores().add(blindTest);
			blindTest.setUser(user);
			blindTest.setJackPot(nextJackPot);
			blindTest.setScore(0.0);
			dao.save(blindTest);
		}
		
		userDao.save(user);

		BlindTestBean res = new BlindTestBean();
		
		res.setIdBlindTest(blindTest.getId());
		res.setIdJackPot(nextJackPot.getId());

		List<AdBrandPossibiliites> ads = nextJackPot.getAds();
		List<QuestionBean> questions = new ArrayList<QuestionBean>();
		for (AdBrandPossibiliites ad : ads) {
			QuestionBean q = new QuestionBean();
			q.setUrl(ad.getAd().getDlFile().getDlUrl());
			List<ResponseBean>rs = new ArrayList<ResponseBean>();
			List<Brand> possibilities = ad.getPossibilities();
				for (Brand brand : possibilities) {
					ResponseBean r = new ResponseBean();
					r.setIdBrand(brand.getId());
					r.setUrlLogo(brand.getDlFile().getDlUrl());
					rs.add(r);
				}
			q.setResponses(rs);
			q.setDuration(ad.getAd().getDuration());
			questions.add(q);
		}
		res.setQuestions(questions);
		//fill Question
//		List<Ad> ads = nextJackPot.getAds();;
		
//		List<QuestionBean> questions = new ArrayList<QuestionBean>();
//		for (Ad ad : ads) {
//			Set<Brand> brandRandom = new HashSet<Brand>();
//			brandRandom.add(ad.getBrand());
//			
//			QuestionBean q = new QuestionBean();
//			q.setUrl(ad.getDlFile().getDlUrl());
//			
//			Random random = new Random();
//			int whereCorrect = random.nextInt(3);
//			
//			List<ResponseBean>rs = new ArrayList<ResponseBean>();
//			for (int i = 0; i < 3; i++) {
//				ResponseBean r = new ResponseBean();
//				Brand brand = null;
//				
//				if(i == whereCorrect){
//					brand = ad.getBrand();
//				}else{
//					Random rb = new Random();
//					do{
//						int nextInt = rb.nextInt(brands.size());
//						Brand b = brands.get(nextInt);
//							if(!brandRandom.contains(b)){
//								brandRandom.add(b);
//								brand = b;
//							}
//					}while(brand == null);
//				}
//				
//				r.setIdBrand(brand.getId());
//				r.setUrlLogo(brand.getDlFile().getDlUrl());
//				
//				rs.add(r);
//			}
//			q.setResponses(rs);
//			q.setDuration(ad.getDuration());
//			questions.add(q);
//		}
//		res.setQuestions(questions);

		//calcul de temps avant début
		Calendar cal = new GregorianCalendar();
		Date startDate = nextJackPot.getStartDate();
		res.setBeginIn(startDate.getTime() - cal.getTimeInMillis());
		
		return res;
	}

	@Override
	public ScoreBean responseUser(long idBlindTest, int numAd, long idBrand) throws Exception {
		long current = new Date().getTime();
		Score bt = dao.getById(idBlindTest);
		ScoreBean res = new ScoreBean();
		List<AdBrandPossibiliites> ads = bt.getJackPot().getAds();
		
		Long start = bt.getJackPot().getStartDate().getTime(); 
			
		for (int i = 0; i < numAd; i++) {
			start+=ads.get(i).getAd().getDuration();
		}
		
		Ad ad = ads.get(numAd).getAd();
		Long duration = ad.getDuration();
		
//		//calcul responseTime
		long responseTime=current - start;
		res.setResponseTime(responseTime);
//		//calcul score
		
		Long correctBrand = ad.getBrand().getId();
		double score = (duration - responseTime)/1000.0;
		res.setCorrectBrand(correctBrand);
		if(correctBrand.equals(idBrand)){
			res.setCorrect(true);
			bt.setScore(score+bt.getScore());
		}else{
			res.setCorrect(false);
			score*=-1;
			if(score/10.0+bt.getScore()<0)bt.setScore(0.0);
			else bt.setScore(score+bt.getScore());
		}
		
		res.setScore(bt.getScore());
		dao.save(bt);
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ClassementBean getClassment(User u, long idJackPot) throws Exception {
		User user = userDao.getById(u.getId());
		
		ClassementBean res = new ClassementBean();
		NetWonJackPot wjp = wonJackPotDao.getUserNetWonJackPot(user.getId(),idJackPot);
		
		Score scoreUser = dao.getBlindTest(user, idJackPot);
		int classement = dao.getClassementJackPot(idJackPot,scoreUser);
		int nbTotal =  dao.getCountPlayer(idJackPot);
//		List<NetScore>bts = (List) dao.getClassementJackPot(idJackPot,0,0);
//		long classement = 1;
//		
//		for (NetScore scoreBlindTest : bts) {
//			if(scoreBlindTest.getUser().getId().equals(user.getId())){
//				res.setScore(scoreBlindTest.getScore());
//				break;
//			}
//			classement++;
//		}
		
		res.setClassement(classement);
		res.setIdJackPot(idJackPot);
		res.setNbPlayer(nbTotal);
		
		if(wjp != null){
			WinObject winObject = wjp.getValue();
			LotBean lot = new LotBean();
			lot.setDescription(winObject.getDescription());
			lot.setName(winObject.getName());
			lot.setPhotoUrl(winObject.getDlFile().getDlUrl());
			lot.setValue(winObject.getValue());
			res.setLot(lot);
		}else{
			WinObject winObject = calulateBTWinObject(classement,nbTotal);
			if(winObject !=null){
				winObjectDao.save(winObject);
				NetWonJackPot won = new NetWonJackPot();
				won.setWinDate(new Date());
				won.setUser(user);
				won.setValue(winObject);
				won.setJackPot(jackPotDao.getById(idJackPot));
				List<WonJackPot> wins = user.getWins();
				if(wins == null){
					user.setWins(new ArrayList<WonJackPot>());
				}
				wins.add(won);
				userDao.save(user);
				
				LotBean lot = new LotBean();
				lot.setDescription(winObject.getDescription());
				lot.setName(winObject.getName());
				lot.setPhotoUrl(winObject.getDlFile().getDlUrl());
				lot.setValue(winObject.getValue());
				res.setLot(lot);
			}
		}
		return res;
	}

	private WinObject calulateBTWinObject(long classement, int nbPlayer) throws Exception {
		double value = 0;
		if(classement == 1 ){
			if(nbPlayer >= 1&&nbPlayer <= 100){
				value = 2;
			}else if(nbPlayer > 100&&nbPlayer <= 200){
				value = 4;
			}else if(nbPlayer > 200&&nbPlayer <= 300){
				value = 6;
			}else if(nbPlayer > 300&&nbPlayer <= 400){
				value = 8;
			}else if(nbPlayer > 400&&nbPlayer <= 500){
				value = 10;
			}else{
				value = 20;
			}
			return stockObjectDao.getRandomWinObjectAvailable(value);
		}else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JackPotClassementBean> getClassment(long idJackpot, int start,
			int limit) throws Exception {
		List<JackPotClassementBean> res = new ArrayList<JackPotClassementBean>();
		List<NetScore>bts = (List) dao.getClassementJackPot(idJackpot,start,limit);
		long position = 1;
		for (NetScore scoreBlindTest : bts) {
			JackPotClassementBean cb = new JackPotClassementBean();
			cb.setPosition(position);
			cb.setPseudo(scoreBlindTest.getUser().getPseudo());
			cb.setScore(scoreBlindTest.getScore());
			res.add(cb);
			position++;
		}
		return res;
	}


	

}
