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
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.dwr.bean.BrandBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.BrandManager;
import fr.k2i.adnjoy.manager.FileManager;

@UrlBinding("/Admin/AddBrand.htm")
public class AddBrandActionBean extends BaseActionBean {
    @SpringBean
    private String directory;
    @SpringBean
    private String imageDirectory;
    
    @SpringBean
    private BrandManager brandManager;
    
    @SpringBean
    private FileManager fileManager;
	
    private String filterBrand;
    private Long id;
	private String name;
	private String login;
	private String password;
	private String email;
	private String phone;
	private String fax;
	private Long logoId;
	
	private FileBean fileBean;
    
    public FileBean getFileBean() {
		return fileBean;
	}

	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}
	
    public String getFilterBrand() {
		return filterBrand;
	}

	public void setFilterBrand(String filterBrand) {
		this.filterBrand = filterBrand;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	
	
	@DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/createBrand.jsp");
    }
	
	@HandlesEvent("getBrandById")
	public Resolution getBrandById(){
		BrandBean b = null;
		try {
			b = brandManager.getBeanById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(b).toString());
	}
	
	
	@HandlesEvent("saveBrand")
	public Resolution saveBrand(){
		BrandBean b = new BrandBean();
		try {
			b.setId(id);
			b.setEmail(email);
			b.setFax(fax);
			b.setLogin(login);
			b.setName(name);
			b.setPhone(phone);
			b.setPassword(password);
			
			if(fileBean == null)
				b.setLogoId(logoId);
			else{
				File file = new File(fileBean, directory+imageDirectory,"brand");
				fileManager.save(file);
				b.setLogoId(file.getId());
			}
		
			brandManager.saveBean(b);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}
	
	
	@HandlesEvent("deleteBrand")
	public Resolution deleteBrand(){
		try {
			brandManager.deletebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");	
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
