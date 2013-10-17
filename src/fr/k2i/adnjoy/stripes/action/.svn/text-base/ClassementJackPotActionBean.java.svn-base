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
package fr.k2i.adnjoy.stripes.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.dwr.bean.JackPotClassementBean;
import fr.k2i.adnjoy.manager.TVScoreBlindTestManager;
import fr.k2i.adnjoy.stripes.bean.ClassementBean;

@UrlBinding("/ClassmentJP.htm")
public class ClassementJackPotActionBean extends BaseActionBean {

	@SpringBean
	private TVScoreBlindTestManager tvScoreBlindTestManager;
	
	private long jackpot;

	private int start;

	private int limit;

	private ClassementBean yourClassement;

	public ClassementBean getYourClassement() {
		return yourClassement;
	}

	public void setYourClassement(ClassementBean yourClassement) {
		this.yourClassement = yourClassement;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getJackpot() {
		return jackpot;
	}

	public void setJackpot(long jackpot) {
		this.jackpot = jackpot;
	}



	@DefaultHandler
    public Resolution view() {
		User user = (User) this.getContext().getRequest().getSession().getAttribute("user");
		try {
			yourClassement = tvScoreBlindTestManager.getClassment(user, jackpot);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ForwardResolution("/WEB-INF/jsp/classementJP.jsp");
    }
	
	@HandlesEvent("getClassement")
	public Resolution getClassement(){
		ExtResponseBean res = null;
		try {
			List<JackPotClassementBean> list = tvScoreBlindTestManager.getClassment(jackpot,start,limit);
			res = new ExtResponseBean();
			res.setResults(tvScoreBlindTestManager.getClassment(jackpot,0,0).size());
			res.setRows(list);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	
}
