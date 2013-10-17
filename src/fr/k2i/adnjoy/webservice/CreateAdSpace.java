package fr.k2i.adnjoy.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.k2i.adnjoy.business.webservice.TVBlindTest;

@WebService
public interface CreateAdSpace {
	@WebMethod
    public Boolean describeAdSpace(TVBlindTest arg0);

}
