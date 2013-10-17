package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.k2i.adnjoy.aop.AopPlayGame;
import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.ad.AdBrandPossibiliites;
import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.TVJackPot;
import fr.k2i.adnjoy.business.jackpot.chanel.ChanelJackPot;
import fr.k2i.adnjoy.business.jackpot.chanel.JackPotQuestion;
import fr.k2i.adnjoy.business.jackpot.won.ChanelWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.TVWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.object.Status;
import fr.k2i.adnjoy.business.object.WinObject;
import fr.k2i.adnjoy.business.score.Score;
import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.StockObjectDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dao.WinObjectDao;
import fr.k2i.adnjoy.dao.WonJackPotDao;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;
import fr.k2i.adnjoy.dwr.bean.JackPotClassementBean;
import fr.k2i.adnjoy.dwr.bean.JackPotQuestionBean;
import fr.k2i.adnjoy.dwr.bean.JackPotResultBean;
import fr.k2i.adnjoy.dwr.bean.MetaClassementBean;
import fr.k2i.adnjoy.dwr.bean.QuestionBean;
import fr.k2i.adnjoy.dwr.bean.ResponseBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.TVScoreBlindTestManager;
import fr.k2i.adnjoy.stripes.bean.ClassementBean;
import fr.k2i.adnjoy.stripes.bean.LotBean;

public class TVScoreBlindTestManagerImpl extends Manager<ScoreBlindTestDao, Score, Long> implements TVScoreBlindTestManager {
	private UserDao userDao;
	private JackPotDao jackPotDao;
	private StockObjectDao stockObjectDao;
	private WinObjectDao winObjectDao;
	private WonJackPotDao wonJackPotDao;
	private ChanelDao chanelDao;
	private AopPlayGame playGame;
	
	public void setPlayGame(AopPlayGame playGame) {
		this.playGame = playGame;
	}

	public void setChanelDao(ChanelDao chanelDao) {
		this.chanelDao = chanelDao;
	}

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
	public BlindTestBean inscriptionBlindTest(User u,long idChanel) throws Exception {
//		List<Brand> brands = brandDao.getAll();
		User user = userDao.getById(u.getId());
		JackPot nextJackPot = jackPotDao.getNextJackPot(idChanel);
		if(nextJackPot == null){
			return null;
		}
		TVScore blindTest = (TVScore) dao.getBlindTest(user, nextJackPot.getId());
		
		if(blindTest == null){
			blindTest = new TVScore();
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
			q.setPauseBefore((ad.getPauseBefore()==null)?0L:ad.getPauseBefore());
			questions.add(q);
		}
		res.setQuestions(questions);
		//calcul de temps avant début
		Calendar cal = new GregorianCalendar();
		Date startDate = nextJackPot.getStartDate();
		res.setBeginIn(startDate.getTime() - cal.getTimeInMillis());
		
		playGame.addUser(user,res.getIdJackPot());
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
		
		//calcul responseTime
		long responseTime=current - start;
		res.setResponseTime(responseTime);
		//calcul score
		Long correctBrand = ad.getBrand().getId();
		double score = (duration - responseTime)/1000.0;
		res.setCorrectBrand(correctBrand);
		if(correctBrand.equals(idBrand)){
			res.setCorrect(true);
			bt.setScore(score+bt.getScore());
		}else{
			res.setCorrect(false);
			score*=-1;
			if(score+bt.getScore()<0)bt.setScore(0.0);
			else bt.setScore(score/10.0+bt.getScore());
		}
		
		res.setScore(bt.getScore());
		res.setPseudo(bt.getUser().getPseudo());
		dao.save(bt);
		return res;
	}
	
	
	@Override
	public ClassementBean getClassment(User u, long idJackPot) throws Exception {
		User user = userDao.getById(u.getId());
		ClassementBean res = new ClassementBean();
		TVJackPot jackPotTV = jackPotDao.getTVById(idJackPot);
		
		TVWonJackPot wjp = wonJackPotDao.getUserTvWonJackPot(user.getId(),idJackPot);
		Score scoreUser = dao.getBlindTest(user, idJackPot);
		int classement = dao.getClassementJackPot(idJackPot,scoreUser);
//		List<Score>bts =  dao.getClassementJackPot(idJackPot,0,100);
		int nbTotal =  dao.getCountPlayer(idJackPot);
		res.setScore(scoreUser.getScore());
//		long classement = bts.indexOf(scoreUser) +1;
//		if(classement == 0){
//			classement = nbTotal;
//		}
		res.setClassement(classement);
//		Double scoreMaxWin = bts.get(99).getScore();
//		Score bt = scoreUser;
//		long classement = 1;
//		if(bt.getScore()<=scoreMaxWin){
//			for (Score scoreBlindTest : bts) {
//				if(scoreBlindTest.getUser().getId().equals(user.getId())){
//					break;
//				}
//				classement++;
//			}
//			res.setClassement(classement);
//		}else{
//			res.setClassement(-1);
//		}
		
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
			TVWonJackPot won = new TVWonJackPot();
			
			WinObject winObject = calulateBTWinObject(classement,nbTotal,this.jackPotDao.getJackPotAds(idJackPot));
			if(winObject !=null){
				winObjectDao.save(winObject);
				won.setWinDate(new Date());
				won.setUser(user);
				won.setValue(winObject);
				won.setScheduleAd(jackPotTV);
				List<WonJackPot> wins = user.getWins();
				if(wins == null){
					user.setWins(new ArrayList<WonJackPot>());
				}
				wins.add(won);
				
				LotBean lot = new LotBean();
				lot.setDescription(winObject.getDescription());
				lot.setName(winObject.getName());
				lot.setPhotoUrl(winObject.getDlFile().getDlUrl());
				lot.setValue(winObject.getValue());
				res.setLot(lot);
			}
			
			//possiblite de jouer au jackpot de la chaîne de télé
			if(classement == 1){
				res.setCurrency("&euro;");
				res.setJackPotValue(jackPotTV.getChanel().getJackpot().getValue());
				res.setShowJackPotChanel(true);
			}
			userDao.save(user);
		}
		return res;
	}

	private WinObject calulateBTWinObject(long classement, int nbPlayer, List<Ad> ads) throws Exception {
		double value = 0;
		
		double gain = nbPlayer * 0.005 * ads.size()*0.7;
//		double gain = nbPlayer * 0.01 * 8*0.7;
		//premier : 50%
		//deuxième : 25%
		//troisième : 12.5%
		//4 -> 100 : 12.5%
		if(gain<2.0){
			if(classement == 1 ){
				value = 2;
			}else{
				return null;
			}
		}else{
			if(classement == 1 ){
				value = gain*0.5;
			}else if(classement == 2 ){
				value = gain*0.25;
			}else if(classement == 3 ){
				value = gain*0.125;
			}else if(classement > 3 && classement<=100 ){
				value = gain*0.125/97;
			}else{
				return null;
			}
		}
		
		return stockObjectDao.getRandomWinObjectAvailable(value,ads);

	}

	@SuppressWarnings("unchecked")
	@Override
	public MetaClassementBean getClassment(GetClassementBean get)
			throws Exception {
		MetaClassementBean res = new MetaClassementBean();
		List<JackPotClassementBean> results = new ArrayList<JackPotClassementBean>();
		List<TVScore>bts =  (List) dao.getClassementJackPot(get);
		long position = get.getStartIndex()+1;
			
		for (TVScore scoreBlindTest : bts) {
			JackPotClassementBean cb = new JackPotClassementBean();
			cb.setPosition(position);
			cb.setPseudo(scoreBlindTest.getUser().getPseudo());
			cb.setScore(scoreBlindTest.getScore());
//			cb.setQuestion(new JackPotQuestionBean(((TVJackPot)scoreBlindTest.getJackPot()).getQuestion()));
			results.add(cb);
			position++;
		}
		
		res.setRecordsReturned(bts.size());
		res.setDir(get.getDir());
		res.setPageSize(bts.size());
		res.setSort(get.getSort());
		res.setStartIndex(get.getStartIndex());
		res.setRecords(results);
		res.setTotalRecords(dao.getCountPlayer(get.getIdJackPot()));
		return res;
//		return null;
	}

	@Override
	public JackPotQuestionBean getJackPotChanel(User u, long idJackPot)
			throws Exception {
		TVWonJackPot wjp = wonJackPotDao.getUserTvWonJackPot(u.getId(),idJackPot);
		if(wjp != null){
			TVJackPot jp = jackPotDao.getTVById(idJackPot);
			JackPotQuestion question = jp.getQuestion();
			JackPotQuestionBean res = new JackPotQuestionBean(question);
			question.setAskedDateTime(new Date());
			jackPotDao.save(jp);
			return res;
		}
		return null;
	}

	@Override
	public JackPotResultBean doResponseJackpot(User u, long idJackPot,
			long idResponse) throws Exception {
		JackPotResultBean res = new JackPotResultBean();
		Calendar calNow = new GregorianCalendar();

		TVJackPot jp = jackPotDao.getTVById(idJackPot);
		JackPotQuestion question = jp.getQuestion();
		
		Date askedDateTime = question.getAskedDateTime();
		
		Calendar calQuestion = new GregorianCalendar();
		calQuestion.setTime(askedDateTime);
		calQuestion.add(Calendar.SECOND, 35);
		
		if(calNow.after(calQuestion)){
			//perdu car trop tard
			res.setWin(false);
		}else{
			res.setWin((question.getCorrectResponse().getId().equals(idResponse)));
		}
		
		if(!res.isWin()){
			Chanel chanel = jp.getChanel();
			ChanelJackPot jackpot = chanel.getJackpot();
			Double value = jackpot.getValue();
			value+=100;
			jackpot.setValue(value);
			chanelDao.save(chanel);
		}else{
			Date now = new Date();
			Chanel chanel = jp.getChanel();
			ChanelJackPot jackpot = chanel.getJackpot();
			WinObject winObject = new WinObject();
			winObject.setDescription("Jackpot de la chaîne "+chanel.getName());
			winObject.setName("Jackpot de chaîne");
			winObject.setValue(jackpot.getValue());
			winObject.setWinDate(now);
			winObject.setStatus(Status.InProgress);
			winObjectDao.save(winObject);
			User user = userDao.getById(u.getId());
			//création du ChanelWonJackpot
			
			ChanelWonJackPot chanelWonJackPot = new ChanelWonJackPot();
			chanelWonJackPot.setScheduleAd(jp);
			chanelWonJackPot.setValue(winObject);
			chanelWonJackPot.setWinDate(now);
			
			List<WonJackPot> wins = user.getWins();
			if(wins == null){
				user.setWins(new ArrayList<WonJackPot>());
			}
			wins.add(chanelWonJackPot);
			userDao.save(user);
			
			jackpot.setLastWonDate(now);
			res.setCurrency("&euro;");
			res.setValue(jackpot.getValue());
			jackpot.setValue(100.0);
			chanelDao.save(chanel);
		}
		
		jackPotDao.save(jp);
		return res;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<JackPotClassementBean> getClassment(long idJackpot, int start,
			int limit) throws Exception {
		List<JackPotClassementBean> res = new ArrayList<JackPotClassementBean>();
		List<TVScore>bts =  (List) dao.getClassementJackPot(idJackpot,start,limit);
		long position = start+1;
		for (TVScore scoreBlindTest : bts) {
			JackPotClassementBean cb = new JackPotClassementBean();
			cb.setPosition(position);
			cb.setPseudo(scoreBlindTest.getUser().getPseudo());
			cb.setScore(scoreBlindTest.getScore());
//			cb.setQuestion(new JackPotQuestionBean(((TVJackPot)scoreBlindTest.getJackPot()).getQuestion()));
			res.add(cb);
			position++;
		}
		return res;
		
	}

	@Override
	public void toLateJackpot(User u, long idJackPot) throws Exception {
		TVJackPot jp = jackPotDao.getTVById(idJackPot);
		Chanel chanel = jp.getChanel();
		ChanelJackPot jackpot = chanel.getJackpot();
		Double value = jackpot.getValue();
		value+=100;
		jackpot.setValue(value);
		chanelDao.save(chanel);		
	}
	

}
