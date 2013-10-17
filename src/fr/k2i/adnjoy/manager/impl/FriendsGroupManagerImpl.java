package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.usertype.UserVersionType;

import fr.k2i.adnjoy.business.score.TVScore;
import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.business.user.TypeValidation;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.business.user.UserFriend;
import fr.k2i.adnjoy.dao.FriendsGroupDao;
import fr.k2i.adnjoy.dao.ScoreBlindTestDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dao.UserFriendDao;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.FriendsGroupManager;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.service.SendMailService;
import fr.k2i.adnjoy.stripes.bean.FriendBean;

public class FriendsGroupManagerImpl extends Manager<FriendsGroupDao, FriendsGroup, Long>
		implements FriendsGroupManager {

	
	private SendMailService sendMailService;
	
	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private UserFriendDao userFriendDao;
	
	
	public void setUserFriendDao(UserFriendDao userFriendDao) {
		this.userFriendDao = userFriendDao;
	}

	private String baseURL;
	

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	
	private ScoreBlindTestDao scoreBlindTestDao;
	
	public void setScoreBlindTestDao(ScoreBlindTestDao scoreBlindTestDao) {
		this.scoreBlindTestDao = scoreBlindTestDao;
	}

	@Override
	public void addFriend(Long userId, Long userToAdd) throws Exception {
		User user = userDao.getById(userId);
		FriendsGroup friendsGroup = user.getFriendsGroup();
		User toAdd = userDao.getById(userToAdd);
		FriendsGroup friendsGroupToAdd = toAdd.getFriendsGroup();
		
		if(friendsGroup==null){
			friendsGroup = new FriendsGroup();
			user.setFriendsGroup(friendsGroup);
		}
		
		if(friendsGroupToAdd==null){
			friendsGroupToAdd = new FriendsGroup();
			toAdd.setFriendsGroup(friendsGroupToAdd);
		}
		
		//vérifier que l'utilisateur n'est pas dans les listes d'ami
		List<UserFriend> friends = friendsGroup.getFriends();
		
		if(friends.contains(toAdd) ){
			//l'utilisateur à déjà ajouter à la liste d'ami
			return;
		}
		
		if(friendsGroupToAdd.getFriends()==null){
			friendsGroupToAdd.setFriends(new ArrayList<UserFriend>());
		}
		friendsGroupToAdd.getFriends().add(new UserFriend(user,TypeValidation.WantToBe,friendsGroupToAdd));
		friends.add(new UserFriend(toAdd,TypeValidation.NoValidated,friendsGroup));
		
		userDao.save(user);
		userDao.save(toAdd);
		dao.save(friendsGroup);
		dao.save(friendsGroupToAdd);
		
		//envoie du mai de confirmation
//		
//		String subject = "Un ami veut vous ajoute sur AD BeBack";
//		StringBuilder message = new StringBuilder();
//		message.append("Bonjour,");
//		message.append("<br><br>");
//		message.append(user.getSex().getLabel()+ " ");
//		message.append(user.getFirstName()+ " ");
//		message.append(user.getLastName()+ ",vous vous ajouter à son groupe d'ami.<br>");
//		message.append("Pour valider votre ajout à son groupe d'ami, cliquer <a href='"+baseURL+"/ValidateFriendsGroup.htm?validateNumber="+user.getId()+"_"+toAdd.getId()+"'>ici</a><br>");
//		message.append("Si le lien ne fonctionne pas, copiez l'url suivante dans votre navigateur : ");
//		message.append(baseURL+"/ValidateFriendsGroup.htm?validateNumber="+user.getId()+"_"+toAdd.getId());
//		message.append("<br><br>");
//		message.append("Toute l'équipe de AD BeBack vous remercie");
//		sendMailService.sendMail(subject, message.toString(), toAdd.getEmail());
	}

	@Override
	public void deleteFriend(Long userId, Long userToDel) throws Exception {
		User user = userDao.getById(userId);
		FriendsGroup friendsGroup = user.getFriendsGroup();
		User delUser = userDao.getById(userToDel);
		FriendsGroup friendsGroupToDel = delUser.getFriendsGroup();

		dao.deleteFriends(friendsGroup,delUser);
		dao.deleteFriends(friendsGroupToDel,user);
	}

	@Override
	public void friendValidate(Long userId, Long userIdOk) throws Exception {
		User user = userDao.getById(userId);
		FriendsGroup friendsGroup = user.getFriendsGroup();
		User okuser = userDao.getById(userIdOk);
		FriendsGroup friendsGroupOk = okuser.getFriendsGroup();
		
		userFriendDao.update(friendsGroup,okuser,TypeValidation.Validated);
		userFriendDao.update(friendsGroupOk,user,TypeValidation.Validated);
	}

	@Override
	public List<FriendBean> getAll(Long userId,String filter) throws Exception {
		User user = userDao.getById(userId);
		FriendsGroup friendsGroup = user.getFriendsGroup();
		
		if(friendsGroup ==null){
			friendsGroup = new FriendsGroup();
			user.setFriendsGroup(friendsGroup);
			userDao.save(user);
		}
		
		List<FriendBean> res = new ArrayList<FriendBean>();
		
		List<UserFriend> friends = friendsGroup.getFriends();
		if(friends != null){
			for (UserFriend u : friends) {
				if((filter==null || u.getFriend().getPseudo().startsWith(filter) || "".equals(filter))&&(u.getValid()!=TypeValidation.WantToBe)){
					res.add(new FriendBean(u));
				}
			}
		}
		
		Collections.sort(res, new Comparator<FriendBean>() {
			@Override
			public int compare(FriendBean o1, FriendBean o2) {
				return o1.getPseudo().compareTo(o2.getPseudo());
			}
		});
		return res;
	}

	@Override
	public void invitFriend(Long userId,String email) throws Exception {
		User user = userDao.getById(userId);
		//envoie de l'invitation
		String subject = "Un ami veut vous faire connaitre le jeu AD BeBack";
		StringBuilder message = new StringBuilder();
		message.append("Bonjour,");
		message.append("<br><br>");
		message.append(user.getSex().getLabel()+ " ");
		message.append(user.getFirstName()+ " ");
		message.append(user.getLastName()+ ",vous veut faire connaitre le jeu AD BeBack.<br>");
		message.append("Pour vous inscrire cliquez <a href='"+baseURL+"/CreateAcount.htm'>ici</a><br>");
		message.append("Si le lien ne fonctionne pas, copiez l'url suivante dans votre navigateur : ");
		message.append(baseURL+"/CreateAcount.htm");
		message.append("<br><br>");
		message.append("Si vous posséder un téléphone vous pouvez vous inscrire directement depuis celui-ci. En téléchargant l'application AdBeBack depuis le market");
		message.append("Toute l'équipe de AD BeBack vous remercie");
		sendMailService.sendMail(subject, message.toString(), email);
	}

	@Override
	public List<FriendBean> getNoValidateFriends(Long userId) throws Exception {
		User user = userDao.getById(userId);
		FriendsGroup friendsGroup = user.getFriendsGroup();
		
		if(friendsGroup ==null){
			friendsGroup = new FriendsGroup();
			user.setFriendsGroup(friendsGroup);
			userDao.save(user);
		}
		
		List<FriendBean> res = new ArrayList<FriendBean>();
		
		List<UserFriend> noValidateFriends = friendsGroup.getFriends();
		if(noValidateFriends!=null){
			for (UserFriend u : noValidateFriends) {
				if(u.getValid() == TypeValidation.WantToBe)
					res.add(new FriendBean(u));
			}
		}
		
		Collections.sort(res, new Comparator<FriendBean>() {
			@Override
			public int compare(FriendBean o1, FriendBean o2) {
				return o1.getPseudo().compareTo(o2.getPseudo());
			}
		});
		return res;
	}

	@Override
	public List<ScoreBean> getScoresFriends(Long idUser,Long idJackPot) throws Exception {
		List<ScoreBean> res = new ArrayList<ScoreBean>();
		User user = userDao.getById(idUser);
		List<UserFriend> friends = user.getFriendsGroup().getFriends();
		List<User> users = new ArrayList<User>();
		users.add(user);
		for (UserFriend f : friends) {
			if(f.getValid() == TypeValidation.Validated){
				users.add(f.getFriend());
			}
		}
		
		List<TVScore> scores =  scoreBlindTestDao.getFriendsScore(users,idJackPot);
		
		for (TVScore tvScore : scores) {
			ScoreBean scoreBean = new ScoreBean();
			scoreBean.setPseudo(tvScore.getUser().getPseudo());
			scoreBean.setScore(tvScore.getScore());
			res.add(scoreBean);
		}
		//getallFriendsScoreOrderedByBlindTest
		return res;
	}
}
