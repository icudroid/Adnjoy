package fr.k2i.adnjoy.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;

import org.aspectj.lang.JoinPoint.StaticPart;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.stripes.bean.UserMessageBean;

public class AopChat {
	
	private static final Map<Long, CptUsersMessage> map = new HashMap<Long, CptUsersMessage>();
	
	private UserManager userManager;
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	public void addUser(User u){
		CptUsersMessage cptUsers = new CptUsersMessage();
		FriendsGroup friendsGroup = u.getFriendsGroup();
		List<UserFriend> friends = friendsGroup.getFriends();
		for (UserFriend userFriend : friends) {
			if(userFriend.getValid() == TypeValidation.Validated){
				cptUsers.getUsersId().add(userFriend.getFriend().getId());
			}
		}
//		cptUsers.getUsersId().add(u.getId());
		map.put(u.getId(), cptUsers);
	}
	
//	public void chatGame(StaticPart staticPart, Object result){
//		MessageUser msgUser = (MessageUser)result;
//		User player = null;
//		try {
//			player = userManager.getById(msgUser.getIdUser());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		CptUsersMessage cptUsers = map.get(player.getId());
//		if(cptUsers!=null){
//			cptUsers.addMessage(msgUser.getMessage(),player.getPseudo());
//			fireEvent(new ChatEvent(this,cptUsers));
//		}
//	}

	private final EventListenerList listeners = new EventListenerList();


	public void addChatEventListener(ChatEventListener listener) {
		listeners.add(ChatEventListener.class,listener);
	}

	public void removeChatEventListener(ChatEventListener listener) {
		listeners.remove(ChatEventListener.class,listener);
	}
	
	private void fireEvent(CptUsersMessage cptUsers,ChatEvent evt) {
		List<Long> usersId = cptUsers.getUsersId();
       for(ChatEventListener listener : listeners.getListeners(ChatEventListener.class)) {
    	   if(usersId.contains(listener.getUserId())){
    		   listener.messaged(evt);
    	   }
       }
	}

	public boolean needUpdate(Long idUser, long version) {
		return (map.get(idUser).getCpt()<=version);
	}

	public UserMessageBean postMessage(Long idUser, String msg) throws Exception {
		UserMessageBean res = userManager.postMessage(map,idUser,msg);
		List<Long> usersId = map.get(idUser).getUsersId();
		for (Long id : usersId) {
			fireEvent(map.get(idUser),new ChatEvent(this));	
		}
		return res;
	}


	public CptUsersMessage getCptUsersMessage(Long idUser) {
		return map.get(idUser);
	}
}
