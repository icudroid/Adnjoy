/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package fr.k2i.adnjoy.stripes.action.android;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.stripes.action.BaseActionBean;

@UrlBinding("/Android/Connexion.htm")
public class ConnexionAndroidActionBean extends BaseActionBean {

	@SpringBean
	private UserManager userManager;
	
	private String pseudo;
	private String pwd;
	

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
//	@HandlesEvent("getAds")
//	public Resolution getAds() {
//		ExtResponseBean res = null;
//		try {
//			List<AdBean> allJsonObj = adManager.getAllBean(brandId);
//			
//			res = new ExtResponseBean();
//			res.setResults(allJsonObj.size());
//			res.setRows(allJsonObj);
//			res.setSuccess(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        return new StreamingResolution("text",new JSONObject(res).toString());
//	}
	
	@DefaultHandler
    public Resolution view() {
		Boolean res = null;
		try {
			User connectUser = userManager.connectUser(pseudo, pwd);
			if(connectUser!=null){
				HttpServletRequest request = this.getContext().getRequest();
				request.getSession().setAttribute("user",connectUser);
				res = true;
			}else{
				res = false;
			}
			
		} catch (Exception e) {
			return new StreamingResolution("text", "{failure:'"+e.getMessage()+"'}");
		}
		
		return new StreamingResolution("text", "{success: "+res.toString()+"}");
    }

	@HandlesEvent("isConnect")
	public Resolution isConnect() {
		HttpServletRequest request = this.getContext().getRequest();
		User u = (User) request.getSession().getAttribute("user");
		Boolean res = (u!=null);
		return new StreamingResolution("text", "{success: "+res.toString()+"}");
	}
}
