package fr.k2i.adnjoy.dwr.impl;

import fr.k2i.adnjoy.aop.AopPlayGame;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.TVBlindTestDwrManager;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;
import fr.k2i.adnjoy.dwr.bean.JackPotQuestionBean;
import fr.k2i.adnjoy.dwr.bean.JackPotResultBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.TVScoreBlindTestManager;
import fr.k2i.adnjoy.manager.UserManager;

public class TVBlindTestDwrManagerImpl implements TVBlindTestDwrManager {

	private TVScoreBlindTestManager tvScoreBlindTestManager;
	private AopPlayGame listenerPlayGame;
	
	public void setListenerPlayGame(AopPlayGame listenerPlayGame) {
		this.listenerPlayGame = listenerPlayGame;
	}


	public void setTvScoreBlindTestManager(
			TVScoreBlindTestManager tvScoreBlindTestManager) {
		this.tvScoreBlindTestManager = tvScoreBlindTestManager;
	}


	private UserManager userManager;
	
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	@Override
	public CallBackData inscriptionUser(long idChanel) {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				BlindTestBean bt = tvScoreBlindTestManager.inscriptionBlindTest(u,idChanel);
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
	public CallBackData playGame(long idJackPot,long idBlindTest, int numAd, long idBrandResponse) {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				ScoreBean sb = tvScoreBlindTestManager.responseUser( idBlindTest, numAd, idBrandResponse);
				res.setData(sb);
				listenerPlayGame.playGame(idJackPot,u.getId(),sb);
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

	public CallBackData getClassementByPage(GetClassementBean get) {
		CallBackData res = new CallBackData();
		try{
		User u = userManager.getUserFromDwrContext();
			if(u!=null){
				res.setData(tvScoreBlindTestManager.getClassment(get));
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
				res.setData(tvScoreBlindTestManager.getClassment(idJackpot,0,0));
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
	public CallBackData getJackPotChanel(long idJackPot) {
		CallBackData res = new CallBackData();
		try{
			User u = userManager.getUserFromDwrContext();
			if(u!=null){
				JackPotQuestionBean jqb = tvScoreBlindTestManager.getJackPotChanel(u,idJackPot);
				res.setData(jqb);
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
	public CallBackData doResponseJackpot(long idJackPot, long idResponse) {
		CallBackData res = new CallBackData();
		try{
			User u = userManager.getUserFromDwrContext();
			if(u!=null){
				JackPotResultBean jrb = tvScoreBlindTestManager.doResponseJackpot(u,idJackPot,idResponse);
				res.setData(jrb);
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
	public CallBackData toLateJackpot(long idJackPot) {
		CallBackData res = new CallBackData();
		try{
			User u = userManager.getUserFromDwrContext();
			if(u!=null){
				tvScoreBlindTestManager.toLateJackpot(u,idJackPot);
			}
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}
}
