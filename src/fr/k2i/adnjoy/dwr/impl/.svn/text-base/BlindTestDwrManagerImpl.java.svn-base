package fr.k2i.adnjoy.dwr.impl;

import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.BlindTestDwrManager;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.ScoreBlindTestManager;
import fr.k2i.adnjoy.manager.UserManager;

public class BlindTestDwrManagerImpl implements BlindTestDwrManager {

	private ScoreBlindTestManager scoreBlindTestManager;
	
	public void setScoreBlindTestManager(ScoreBlindTestManager scoreBlindTestManager) {
		this.scoreBlindTestManager = scoreBlindTestManager;
	}

	private UserManager userManager;
	
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	@Override
	public CallBackData inscriptionUser() {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				BlindTestBean bt = scoreBlindTestManager.inscriptionBlindTest(u);
				res.setData(bt);
			}else{
				res.setData(null);
			}
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public CallBackData playGame(long idBlindTest, int numAd, long idBrandResponse) {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				ScoreBean sb = scoreBlindTestManager.responseUser( idBlindTest, numAd, idBrandResponse);
				res.setData(sb);
			}else{
				res.setData(null);
			}
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public CallBackData getClassement(long idJackpot) {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				res.setData(scoreBlindTestManager.getClassment(idJackpot,0,0));
			}else{
				res.setData(null);
			}
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}


//	@Override
//	public CallBackData getResultGame(long idJackPot) {
//		CallBackData res = new CallBackData();
//		try{
//		User u = userManager.getUserFromDwrContext();
//			if(u!=null){
////				ScoreBean sb = scoreBlindTestManager.responseUser(u,idJackPot);
////				res.setData(sb);
//			}else{
//				res.setData(null);
//			}
//		} catch (Exception e) {
//			ErrorDwr err = res.getErr();
//			err.setErrno(-1);
//			err.setMessage(e.getMessage());
//		}
//		return res;
//	}
}
