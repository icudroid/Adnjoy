package fr.k2i.adnjoy.manager;

import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.ActionBeanContext;
import fr.k2i.adnjoy.aop.AopChat;
import fr.k2i.adnjoy.aop.CptUsersMessage;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dwr.bean.UserBean;
import fr.k2i.adnjoy.dwr.bean.WonObjectBean;
import fr.k2i.adnjoy.stripes.bean.FriendBean;
import fr.k2i.adnjoy.stripes.bean.UserMessageBean;

public interface UserManager extends IManager<UserDao,User, Long>{
	User getUserFromDwrContext()throws Exception;
	User getAdminFromDwrContext()throws Exception;
	User createAccount(User user)throws Exception;
	void sendConfirmationMail(User user)throws Exception;
	Boolean checkUniqueEmail(String email)throws Exception;
	Boolean checkUniquePseudo(String pseudo)throws Exception;
	Boolean validateAccount(String number)throws Exception;
	User connectUser(String pseudo, String password)throws Exception;
	Boolean sendPassorwd(String email)throws Exception;
	List<WonObjectBean> getWonObjects(long idUser, int start, int limit)throws Exception;
	void saveFromUI(User user)throws Exception;
	UserBean getUserBean(Long idUser)throws Exception;
	void modAccount(Long id, User user)throws Exception;
	User getUserFromContext(ActionBeanContext context)throws Exception;
	List<FriendBean> getUsersByPseudo(String pseudoFilter, int start, int limit)throws Exception;
	int getCountUsersByPseudo(String pseudoFilter)throws Exception;
	User getUserByPseudo(String pseudo)throws Exception;
	void registerChat(AopChat listenerChat, ActionBeanContext context)throws Exception;
	UserMessageBean postMessage(Map<Long, CptUsersMessage> map, Long idUser, String msg)throws Exception;
}
