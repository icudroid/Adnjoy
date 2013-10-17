package fr.k2i.adnjoy.dwr.security;

import fr.k2i.adnjoy.dwr.bean.CallBackData;
import fr.k2i.adnjoy.dwr.bean.ErrorDwr;
import fr.k2i.adnjoy.manager.UserManager;
import org.aspectj.lang.ProceedingJoinPoint;

public class DwrSecurity {
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Object securityDwr(ProceedingJoinPoint pjp) throws Throwable {
		
			
		CallBackData resCallBackData = new CallBackData();
		ErrorDwr err = resCallBackData.getErr();
		try {
			
			if(pjp.getSignature().getName().startsWith("adm_") && userManager.getAdminFromDwrContext() == null){
				resCallBackData.getErr().setErrno(ErrorDwr.NO_ADMIN);
				resCallBackData.getErr().setMessage(ErrorDwr.NO_ADMIN_STR);
				resCallBackData.getErr().setExecJs(
						"window.location.href='Home.htm'");
				return resCallBackData;
			}else if (userManager.getUserFromDwrContext() == null) {
				if(!pjp.getSignature().getName().startsWith("public_")){
					resCallBackData.getErr().setErrno(ErrorDwr.SESS_EXP);
					resCallBackData.getErr().setMessage(ErrorDwr.SESS_EXP_STR);
					resCallBackData.getErr().setExecJs(
							"window.location.href='Home.htm'");
					return resCallBackData;
				}
			}

			
		} catch (Exception e) {
			resCallBackData.getErr().setErrno(ErrorDwr.SESS_EXP);
			resCallBackData.getErr().setMessage(e.getMessage());
			resCallBackData.getErr().setExecJs(
						"window.location.href='Home.htm'");
			return resCallBackData;
		}

		Object retVal = null;
		try {
			retVal = pjp.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			err.setErrno(ErrorDwr.EXCEPTION);
			err.setMessage("Une erreur est survenue sur le serveur");
			return resCallBackData;
		}

		return retVal;
	}
}
