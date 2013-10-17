package fr.k2i.adnjoy.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;

import org.aspectj.lang.JoinPoint.StaticPart;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.ScoreBlindTestManager;
import fr.k2i.adnjoy.manager.UserManager;

public class AopPlayGame {

	private final Map<Long, Map<Long, CptUsers>> map = new HashMap<Long, Map<Long, CptUsers>>();
	private UserManager userManager;
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	private ScoreBlindTestManager scoreBlindTestManager;

	public void setScoreBlindTestManager(ScoreBlindTestManager scoreBlindTestManager) {
		this.scoreBlindTestManager = scoreBlindTestManager;
	}

	public void addUser(User u, long idJackPot){
		CptUsers cptUsers = new CptUsers();
		FriendsGroup friendsGroup = u.getFriendsGroup();
		List<UserFriend> friends = friendsGroup.getFriends();
		for (UserFriend userFriend : friends) {
			if(userFriend.getValid() == TypeValidation.Validated){
				cptUsers.addUser(userFriend.getFriend().getId());
			}
		}
//		cptUsers.addUser(u.getId());
		
		Map<Long, CptUsers> mapJackpot = map.get(idJackPot);
		
		if(mapJackpot == null){
			mapJackpot =  new HashMap<Long, CptUsers>();
			map.put(idJackPot, mapJackpot);
		}
		
		mapJackpot.put(u.getId(), cptUsers);
	}
	
	public void playGame(Long idJackPot,Long idUser, ScoreBean res){
		 Map<Long, CptUsers> mapJackPot = map.get(idJackPot);
		CptUsers cptUsers = mapJackPot.get(idUser);
		if(cptUsers!=null){
//			cptUsers.setUserScore(idUser, res);
//			cptUsers.incrementCpt();
			List<Long> usersId = cptUsers.getUsersId();
			for (Long id : usersId) {
				CptUsers cptUsersF = mapJackPot.get(id);
				if(cptUsersF!=null){
					cptUsersF.setUserScore(idUser, res);
					cptUsersF.incrementCpt();
					fireEvent(idJackPot,id,cptUsersF);
				}
				
			}
//			fireEvent(idUser,cptUsers);
		}
	}
	
	public void playGameDwr(StaticPart staticPart, Object result){
//		CallBackData callBackData = (CallBackData)result;
//		User player = null;
//		try {
//			player = userManager.getUserByPseudo(((ScoreBean)callBackData.getData()).getPseudo());
//		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		CptUsers cptUsers = map.get(player.getId());
//		if(cptUsers!=null){
//			cptUsers.setUserScore(player.getId(),((ScoreBean)callBackData.getData()));
//			cptUsers.incrementCpt();
//			fireEvent(cptUsers);
//		}
	}
	
	private final EventListenerList listeners = new EventListenerList();

	public void addPlayEventListener(PlayEventListener listener) {
		listeners.add(PlayEventListener.class,listener);
	}

	public void removePlayEventListener(PlayEventListener listener) {
		listeners.remove(PlayEventListener.class,listener);
	}
	
	private void fireEvent(Long idJackPot,Long idUser,CptUsers cptUsers ) {
//		CptUsers cptUsers = evt.getCptUsers();
//		List<Long> usersId = cptUsers.getUsersId();
       for(PlayEventListener listener : listeners.getListeners(PlayEventListener.class)) {
    	   if(idUser.equals(listener.getUserId())){
    		   listener.played(new PlayEvent(this, cptUsers));
    	   }
       }
	}


	public boolean needUpdate(Long idJackPot,Long idUser, long version) {
		Map<Long, CptUsers> mapJackPot = map.get(idJackPot);
		return (mapJackPot.get(idUser).getCpt()>=version);
	}
}
