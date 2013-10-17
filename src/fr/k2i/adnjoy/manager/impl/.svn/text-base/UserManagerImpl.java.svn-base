package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBeanContext;
import fr.k2i.adnjoy.aop.AopChat;
import fr.k2i.adnjoy.aop.CptUsersMessage;
import fr.k2i.adnjoy.business.jackpot.won.ChanelWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.NetWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.TVWonJackPot;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.object.WinObject;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.CountryDao;
import fr.k2i.adnjoy.dao.UserDao;
import fr.k2i.adnjoy.dwr.bean.UserBean;
import fr.k2i.adnjoy.dwr.bean.WonObjectBean;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.service.SendMailService;
import fr.k2i.adnjoy.stripes.bean.FriendBean;
import fr.k2i.adnjoy.stripes.bean.UserMessageBean;

public class UserManagerImpl extends Manager<UserDao, User, Long> implements
		UserManager {

	private SendMailService sendMailService;
	private CountryDao countryDao;
	private String baseURL;
	

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}

	public User getUserFromDwrContext() throws Exception {
		HttpServletRequest request = org.directwebremoting.WebContextFactory.get()
				.getHttpServletRequest();
		return (User) request.getSession().getAttribute("user");
	}

	public User createAccount(User user) throws Exception {
		user.setCreatedDate(new Date());
		user.setCountry(countryDao.getById(user.getCountry().getId()));
		user.setValidated(false);
		user.setValidateUrl(generateValdateUrl(user.getPseudo()));
		sendConfirmationMail(user);
		dao.save(user);
		return user;
	}

	private String generateValdateUrl(String pseudo) {
		return pseudo+Long.toHexString(System.currentTimeMillis());
	}

	public void sendConfirmationMail(User user) throws Exception {
		String subject = "Confirmation de l'inscription sur AD BeBack";
		StringBuilder message = new StringBuilder();
		message.append("Bonjour ");
		message.append(user.getSex().getLabel()+ " ");
		message.append(user.getFirstName()+ " ");
		message.append(user.getLastName()+ ",<br><br> ");
		message.append("Merci pour votre inscription sur AD BeBack.<br>");
		message.append("Pour valider votre inscription, cliquer <a href='"+baseURL+"/ValidateAccount.htm?validateNumber="+user.getValidateUrl()+"'>ici</a><br>");
		message.append("Si le lien ne fonctionne pas, copiez l'url suivante dans votre navigateur : ");
		message.append(baseURL+"/ValidateAccount.htm?validateNumber="+user.getValidateUrl());
		message.append("<br><br>");
		message.append("Toute l'équipe de AD BeBack vous remercie");
		sendMailService.sendMail(subject, message.toString(), user.getEmail());
	}

	@Override
	public Boolean checkUniqueEmail(String email) throws Exception {
		User user = dao.getByEmail(email);
		return (user == null);
	}

	@Override
	public Boolean checkUniquePseudo(String pseudo) throws Exception {
		User user = dao.getByPseudo(pseudo);
		return (user == null);
	}

	@Override
	public Boolean validateAccount(String number) throws Exception {
		User user = dao.getByValidateNumber(number);
		if(user == null){
			return false;
		}else{
			user.setValidated(true);
			dao.save(user);
			return true;
		}
	}

	@Override
	public User connectUser(String pseudo, String password) throws Exception {
		User user = dao.getByPseudo(pseudo);
		if(user !=null && user.getPassword().equals(password)){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public Boolean sendPassorwd(String email) throws Exception {
		User byEmail = dao.getByEmail(email);
		if(byEmail == null)return false;
		sendMailService.sendMail("Votre mot de passe", "votre mot de passe est : "+byEmail.getPassword(), email);
		return true;
	}

	@Override
	public List<WonObjectBean> getWonObjects(long idUser,int start, int limit) throws Exception {
		List<WonObjectBean> res = new ArrayList<WonObjectBean>();
		List<WonJackPot> wins = dao.getWonJackPot(idUser,start,limit);
		for (WonJackPot wonJackPot : wins) {
			WonObjectBean won = new WonObjectBean();
			WinObject winObj = wonJackPot.getValue();
			won.setDescription(winObj.getDescription());
			won.setIdWinObject(winObj.getId());
			won.setName(winObj.getName());
			if(winObj.getDlFile()!=null)
				won.setObjUrlPhoto(winObj.getDlFile().getDlUrl());
			won.setStatus(winObj.getStatus().getLabel());
			won.setValue(winObj.getValue());
			won.setWinDate(winObj.getWinDate());
			if (wonJackPot instanceof NetWonJackPot) {
				won.setWinOn("ADBEBACK");
			}else if (wonJackPot instanceof TVWonJackPot){
				TVWonJackPot dwj = (TVWonJackPot)wonJackPot;
				won.setWinOn(dwj.getScheduleAd().getChanel().getName());
			}else{
				ChanelWonJackPot dwj = (ChanelWonJackPot)wonJackPot;
				won.setWinOn(dwj.getScheduleAd().getChanel().getName());
			}
			res.add(won);
		}
		return res;
	}

	@Override
	public User getAdminFromDwrContext() throws Exception {
		HttpServletRequest request = org.directwebremoting.WebContextFactory.get().getHttpServletRequest();
		return (User) request.getSession().getAttribute("admin");
	}

	@Override
	public void saveFromUI(User user) throws Exception {
		User u = dao.getById(user.getId());
		u.setAddress1(user.getAddress1());
		u.setAddress2(user.getAddress2());
		u.setCity(user.getCity());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
	
		dao.save(u);
	}

	@Override
	public UserBean getUserBean(Long idUser) throws Exception {
		return new UserBean(dao.getById(idUser));
	}

	@Override
	public void modAccount(Long idUser, User user) throws Exception {
		User u = dao.getById(idUser);
		u.setAddress1(user.getAddress1());
		u.setAddress2(user.getAddress2());
		u.setCity(user.getCity());
		u.setZipCode(user.getZipCode());
		u.setCountry(countryDao.getById(user.getCountry().getId()));
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		dao.save(u);
	}

	@Override
	public User getUserFromContext(ActionBeanContext context) throws Exception {
		HttpServletRequest request = context.getRequest();
		return (User) request.getSession().getAttribute("user");
	}

	@Override
	public int getCountUsersByPseudo(String pseudoFilter) throws Exception {
		return dao.getCountUsersByPseudo(pseudoFilter);
	}

	@Override
	public List<FriendBean> getUsersByPseudo(String pseudoFilter, int start,
			int limit) throws Exception {
		List<FriendBean> res = new ArrayList<FriendBean>();
		List<User> users = dao.filterByPseudo(pseudoFilter,start,limit);
		for (User user : users) {
			res.add(new FriendBean(user));
		}
		return res;
	}

	@Override
	public User getUserByPseudo(String pseudo) throws Exception {
		return dao.getByPseudo(pseudo);
	}

	@Override
	public void registerChat(AopChat listenerChat, ActionBeanContext context)
			throws Exception {
		User user = getUserFromContext(context);
		listenerChat.addUser( dao.getById(user.getId()));
	}

//	@Override
//	public void postMessage(CptUsersMessage cptUsersMessage, Long idUser,
//			String msg) throws Exception {
//		cptUsersMessage.addMessage(msg, dao.getById(idUser).getPseudo());
//		
//	}

	@Override
	public UserMessageBean postMessage(Map<Long, CptUsersMessage> map, Long idUser,
			String msg) throws Exception {
		
		User u = dao.getById(idUser);
		long date = new Date().getTime();
		UserMessageBean res = new UserMessageBean(date,u.getPseudo(),msg);
		
		CptUsersMessage cptUsersMessage = map.get(idUser);
		List<Long> usersId = cptUsersMessage.getUsersId();
		for (Long id : usersId) {
			CptUsersMessage cum = map.get(id);
			if(cum!=null)
			cum.addMessage(res);
		}
		return res;
	}
}
