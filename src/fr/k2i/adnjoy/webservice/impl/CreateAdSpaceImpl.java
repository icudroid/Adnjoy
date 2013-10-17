package fr.k2i.adnjoy.webservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;

import fr.k2i.adnjoy.business.webservice.TVBlindTest;
import fr.k2i.adnjoy.manager.JackPotManager;
import fr.k2i.adnjoy.webservice.CreateAdSpace;
@WebService(endpointInterface = "fr.k2i.adnjoy.webservice.CreateAdSpace")
public class CreateAdSpaceImpl implements CreateAdSpace {

	private JackPotManager jackPotManager;
	
	
	public JackPotManager getJackPotManager() {
		return jackPotManager;
	}

	public void setJackPotManager(@WebParam(mode=Mode.IN)JackPotManager jackPotManager) {
		this.jackPotManager = jackPotManager;
	}

	@WebMethod
	public Boolean describeAdSpace(TVBlindTest bt) {
		try {
			return jackPotManager.describeAdSpace(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
