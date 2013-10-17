package fr.k2i.adnjoy.dwr.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.k2i.adnjoy.business.user.Right;
import fr.k2i.adnjoy.business.user.Sex;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.UserDwrManager;
import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.EnumBean;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.dwr.bean.WonObjectBean;
import fr.k2i.adnjoy.manager.UserManager;

public class UserDwrManagerImpl implements UserDwrManager {

	private UserManager userManager;
	
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public CallBackData public_checkUniqueEmail(String email) {
		CallBackData res = new CallBackData();
		try {
			res.setData(userManager.checkUniqueEmail(email));
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	public CallBackData public_checkUniquePseudo(String pseudo) {
		CallBackData res = new CallBackData();
		try {
			res.setData(userManager.checkUniquePseudo(pseudo));
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	public CallBackData public_connectUser(String pseudo, String password) {
		CallBackData res = new CallBackData();
		try {
			User connectUser = userManager.connectUser(pseudo, password);
			res.setData((connectUser!=null));
			HttpServletRequest request = org.directwebremoting.WebContextFactory.get().getHttpServletRequest();
			if(connectUser !=null){
				if(Right.Admin.equals(connectUser.getRight())){
					request.getSession().setAttribute("admin",connectUser);
				}
				if(Right.Brand.equals(connectUser.getRight())){
					request.getSession().setAttribute("brand",connectUser);	
				}
				if(Right.Chanel.equals(connectUser.getRight())){
					request.getSession().setAttribute("chanel",connectUser);
				}
			}
			
			request.getSession().setAttribute("user",connectUser);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData public_sendPassorwd(String email) {
		CallBackData res = new CallBackData();
		try {
			Boolean existe = userManager.sendPassorwd(email);
			res.setData(existe);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData disconnect() {
		CallBackData res = new CallBackData();
		try {
			HttpServletRequest request = org.directwebremoting.WebContextFactory.get().getHttpServletRequest();
			request.getSession().invalidate();
			request.getSession().removeAttribute("user");
			res.setData(true);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData getWonObjects() {
		CallBackData res = new CallBackData();
		try {
			User user = userManager.getUserFromDwrContext();
			List<WonObjectBean> list = userManager.getWonObjects(user.getId(),0,0);
			res.setData(list);
		} catch (Exception e) {
			ErrorDwr err = res.getErr();
			err.setErrno(-1);
			err.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public CallBackData public_getSexes() {
		CallBackData res = new CallBackData();
		List<EnumBean>enums = new ArrayList<EnumBean>();
		Sex[] values = Sex.values();
			for (Sex sex : values) {
				enums.add(new EnumBean(sex));
			}
		res.setData(enums);
		return res;
	}


}
