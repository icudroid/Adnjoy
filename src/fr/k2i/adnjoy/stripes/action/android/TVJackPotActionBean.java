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

import fr.k2i.adnjoy.aop.AopPlayGame;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.BlindTestBean;
import fr.k2i.adnjoy.dwr.bean.GetClassementBean;
import fr.k2i.adnjoy.dwr.bean.JackPotQuestionBean;
import fr.k2i.adnjoy.dwr.bean.JackPotResultBean;
import fr.k2i.adnjoy.dwr.bean.MetaClassementBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.TVScoreBlindTestManager;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.stripes.action.BaseActionBean;
import fr.k2i.adnjoy.stripes.bean.ClassementBean;

@UrlBinding("/Android/TVJackPot.htm")
public class TVJackPotActionBean extends BaseActionBean {
	@SpringBean
	private UserManager userManager;
	
	@SpringBean
	private TVScoreBlindTestManager tvScoreBlindTestManager;
	
	@SpringBean
	private AopPlayGame listenerPlayGame;
	
	private Long idChanel;
	
	private Long idBlindTest;
	private Integer numAd;
	private Long idBrandResponse;

	private Long idJackPot;
	private Long idResponse;
	
	
	private String sort;
	private String dir;
	private Integer startIndex;
	private Integer results;
	
	@HandlesEvent("inscriptionUser")
	public Resolution inscriptionUser() {
		//idChanel
		BlindTestBean res = null;
		try{
		User u = userManager.getUserFromContext(this.getContext());
			if(u!=null){
				res = tvScoreBlindTestManager.inscriptionBlindTest(u,idChanel);
			}
		} catch (Exception e) {
			
		}
		if(res ==null)
			return new StreamingResolution("text","");
		else
			return new StreamingResolution("text",new JSONObject(res).toString());
	 }
	
	@HandlesEvent("playGame")
	public Resolution playGame() {
		//idBlindTest;
		//numAd;
		//idBrandResponse; 
		//idJackPot
		ScoreBean res = null;
		try{
			res = tvScoreBlindTestManager.responseUser( idBlindTest, numAd, idBrandResponse);
			listenerPlayGame.playGame(idJackPot,userManager.getUserFromContext(getContext()).getId(),res);
		} catch (Exception e) {
			System.out.println("");
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
	 }
	

	@HandlesEvent("getJackPotChanel")
	public Resolution getJackPotChanel(){
		//idJackPot
		JackPotQuestionBean res = null;
		try{
			User u = userManager.getUserFromContext(this.getContext());
			res = tvScoreBlindTestManager.getJackPotChanel(u,idJackPot);
		} catch (Exception e) {
			
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	@HandlesEvent("doResponseJackpot")
	public Resolution doResponseJackpot(){
		//idJackPot
		//idResponse
		JackPotResultBean res = null;
		try{
			User u = userManager.getUserFromContext(this.getContext());
			res = tvScoreBlindTestManager.doResponseJackpot(u,idJackPot,idResponse);
		} catch (Exception e) {
			
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	@HandlesEvent("toLateJackpot")
	public Resolution toLateJackpot(){
		//idJackPot
		try{
			User u = userManager.getUserFromContext(this.getContext());
			tvScoreBlindTestManager.toLateJackpot(u,idJackPot);
		} catch (Exception e) {
			
		}
		return new StreamingResolution("text","{success:true}");
	}
	
	@HandlesEvent("getClassementByPage")
	public Resolution getClassementByPage(){
		//idJackPot
		//sort;
		//dir;
		//startIndex;
		//results;
		MetaClassementBean res = null;
		try{
			res = tvScoreBlindTestManager.getClassment(new GetClassementBean(idJackPot,sort,dir,startIndex,results));
		} catch (Exception e) {
			
		}
		return new StreamingResolution("text",new JSONObject(res).toString()); 
	}
	
	@HandlesEvent("getResultPage")
	public Resolution getResultPage(){
		ClassementBean res = null;
		try{
			User u = userManager.getUserFromContext(this.getContext());
			res = tvScoreBlindTestManager.getClassment(u, idJackPot);
		} catch (Exception e) {
			
		}
		return new StreamingResolution("text",new JSONObject(res).toString()); 
	}
	
    @DefaultHandler
    public Resolution view() {
    	return new StreamingResolution("text", "{}");
    }

	public Long getIdChanel() {
		return idChanel;
	}

	public void setIdChanel(Long idChanel) {
		this.idChanel = idChanel;
	}

	public Long getIdBlindTest() {
		return idBlindTest;
	}

	public void setIdBlindTest(Long idBlindTest) {
		this.idBlindTest = idBlindTest;
	}

	public Integer getNumAd() {
		return numAd;
	}

	public void setNumAd(Integer numAd) {
		this.numAd = numAd;
	}

	public Long getIdBrandResponse() {
		return idBrandResponse;
	}

	public void setIdBrandResponse(Long idBrandResponse) {
		this.idBrandResponse = idBrandResponse;
	}

	public Long getIdJackPot() {
		return idJackPot;
	}

	public void setIdJackPot(Long idJackPot) {
		this.idJackPot = idJackPot;
	}

	public Long getIdResponse() {
		return idResponse;
	}

	public void setIdResponse(Long idResponse) {
		this.idResponse = idResponse;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getResults() {
		return results;
	}

	public void setResults(Integer results) {
		this.results = results;
	}
    
    
}
