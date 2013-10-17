package fr.k2i.adnjoy.manager;

import java.util.List;

import fr.k2i.adnjoy.business.user.FriendsGroup;
import fr.k2i.adnjoy.dao.FriendsGroupDao;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.stripes.bean.FriendBean;

public interface FriendsGroupManager extends IManager<FriendsGroupDao,FriendsGroup, Long>{
	/**
	 * Ajoute un ami dans ma liste d'amis non validé
	 * @param userId
	 * @param userToAdd
	 * @throws Exception
	 */
	void addFriend(Long userId,Long userToAdd) throws Exception;
	
	/**
	 * Supprimer un ami de la liste d'amis validé ou non validé
	 * @param userId
	 * @param userToDel
	 * @throws Exception
	 */
	void deleteFriend(Long userId,Long userToDel) throws Exception;
	
	/**
	 * Valide l'ajout dun utilisateur dans un groupe d'ami
	 * @param userId
	 * @param userIdOk
	 * @throws Exception
	 */
	void friendValidate(Long userId,Long userIdOk)throws Exception;

	/**
	 * Recupérer tous les amis validé ou non
	 * @param user
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	List<FriendBean> getAll(Long userId,String filter)throws Exception;
	
	
	/**
	 * Invitation d'un ami
	 * @param userId
	 * @param email
	 * @throws Exception
	 */
	void invitFriend(Long userId,String email)throws Exception;

	/**
	 * Récupérer tous les amis non validés
	 * @param idUser
	 * @return
	 * @throws Exception
	 */
	List<FriendBean> getNoValidateFriends(Long idUser)throws Exception;
	
	/**
	 * 
	 * @param idUser
	 * @param idJackPot
	 * @return
	 * @throws Exception
	 */
	List<ScoreBean> getScoresFriends(Long idUser,Long idJackPot)throws Exception;
}
