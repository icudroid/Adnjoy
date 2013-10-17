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

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.dwr.bean.ChanelBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.ChanelManager;
import fr.k2i.adnjoy.stripes.action.BaseActionBean;

@UrlBinding("/Android/Chanels.htm")
public class ChanelAndroidActionBean extends BaseActionBean {

	@SpringBean
	private ChanelManager chanelManager;
	
	private String filterChanel;
	
	
	
	public String getFilterChanel() {
		return filterChanel;
	}

	public void setFilterChanel(String filterChanel) {
		this.filterChanel = filterChanel;
	}

	@HandlesEvent("getChanels")
	public Resolution getChanels() {
		ExtResponseBean res = null;
		try {
			java.util.List<ChanelBean> allJsonObj = chanelManager.getAllBean(filterChanel);
			res = new ExtResponseBean();
			res.setResults(allJsonObj.size());
			res.setRows(allJsonObj);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	@DefaultHandler
    public Resolution view() {
//		Boolean res = null;
//		try {
//			User connectUser = userManager.connectUser(pseudo, pwd);
//			if(connectUser!=null){
//				HttpServletRequest request = this.getContext().getRequest();
//				request.getSession().setAttribute("user",connectUser);
//				res = true;
//			}else{
//				res = false;
//			}
//			
//		} catch (Exception e) {
//			return new StreamingResolution("text", "{failure:'"+e.getMessage()+"'}");
//		}
		
		return new StreamingResolution("text", "{}");
    }

}
