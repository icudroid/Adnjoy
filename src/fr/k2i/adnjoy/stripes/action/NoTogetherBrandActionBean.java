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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.dwr.bean.BrandBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.BrandManager;

@UrlBinding("/Admin/NoTogetherBrand.htm")
public class NoTogetherBrandActionBean extends BaseActionBean {
    
    @SpringBean
    private BrandManager brandManager;
    
    private String filterBrand;
    private Long brandId;
    private Long idNoBrand;
    
	public String getFilterBrand() {
		return filterBrand;
	}

	public void setFilterBrand(String filterBrand) {
		this.filterBrand = filterBrand;
	}


	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getIdNoBrand() {
		return idNoBrand;
	}

	public void setIdNoBrand(Long idNoBrand) {
		this.idNoBrand = idNoBrand;
	}

	@DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/noTogetherBrand.jsp");
    }
	
	@HandlesEvent("getBrandById")
	public Resolution getBrandById(){
		BrandBean b = null;
		try {
			b = brandManager.getBeanById(brandId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(b).toString());
	}
	
	
	@HandlesEvent("addNoTogetherBrand")
	public Resolution addNoTogetherBrand(){
		try {
			brandManager.addNoTogether(brandId,idNoBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}
	
	
	@HandlesEvent("deleteNoTogetherBrand")
	public Resolution deleteNoTogetherBrand(){
		try {
			brandManager.deleteNoTogetherBrand(brandId,idNoBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");	
	}
	
	@HandlesEvent("getNoTogetherBrands")
	public Resolution getNoTogetherBrands(){
		ExtResponseBean res = null;
		try {
			List<BrandBean> allJsonObj = brandManager.getNoTogetherBrands(brandId);
			
			Collections.sort(allJsonObj, new Comparator<BrandBean>() {

				@Override
				public int compare(BrandBean o1, BrandBean o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			
			res = new ExtResponseBean();
			res.setResults(allJsonObj.size());
			res.setRows(allJsonObj);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	@HandlesEvent("getBrands")
	public Resolution getBrands(){
		ExtResponseBean res = null;
		try {
			List<BrandBean> allJsonObj = brandManager.getBrandsLike(filterBrand);
			
			Collections.sort(allJsonObj, new Comparator<BrandBean>() {

				@Override
				public int compare(BrandBean o1, BrandBean o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			
			res = new ExtResponseBean();
			res.setResults(allJsonObj.size());
			res.setRows(allJsonObj);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	
}
