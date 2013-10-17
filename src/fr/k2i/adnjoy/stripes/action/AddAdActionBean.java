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

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.dwr.bean.BrandBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.AdManager;
import fr.k2i.adnjoy.manager.BrandManager;
import fr.k2i.adnjoy.manager.FileManager;
import fr.k2i.adnjoy.manager.TypeManager;
import fr.k2i.adnjoy.stripes.bean.AdBean;
import fr.k2i.adnjoy.stripes.bean.TypeBean;

@UrlBinding("/Admin/AddAd.htm")
public class AddAdActionBean extends BaseActionBean {

	@Autowired
	private TypeManager typeManager;
	
	@Autowired
	private BrandManager brandManager;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private AdManager adManager;
	
	@Autowired
    private String directory;
	
	@Autowired
    private String videoDirectory;
	
	
	public void setTypeManager(TypeManager typeManager) {
		this.typeManager = typeManager;
	}

	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setAdManager(AdManager adManager) {
		this.adManager = adManager;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setVideoDirectory(String videoDirectory) {
		this.videoDirectory = videoDirectory;
	}


	private Long brandId;
	private Long id;
	private FileBean fileBean;
	private String uid;
	private String startDate;
	private String endDate;
	private Long typeId;
	private String logoText;
	

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FileBean getFileBean() {
		return fileBean;
	}

	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getLogoText() {
		return logoText;
	}

	public void setLogoText(String logoText) {
		this.logoText = logoText;
	}

	@HandlesEvent("getBrands")
	public Resolution getBrands() {
		ExtResponseBean res = null;
		try {
			List<BrandBean> list = brandManager.getAllJsonObj();
			
			Collections.sort(list, new Comparator<BrandBean>() {

				@Override
				public int compare(BrandBean o1, BrandBean o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			
			res = new ExtResponseBean();
			res.setResults(list.size());
			res.setRows(list);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
	}
	
	@HandlesEvent("getAds")
	public Resolution getAds() {
		ExtResponseBean res = null;
		try {
			List<AdBean> allJsonObj = adManager.getAllBean(brandId);
			
			res = new ExtResponseBean();
			res.setResults(allJsonObj.size());
			res.setRows(allJsonObj);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
	}

	@HandlesEvent("deleteAd")
	public Resolution deleteAd() {
		try {
			adManager.deletebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}
	
	@HandlesEvent("getTypes")
	public Resolution getTypes() {
		ExtResponseBean res = null;
		try {
			List<TypeBean> allJsonObj = typeManager.getAllBean();
			Collections.sort(allJsonObj, new Comparator<TypeBean>() {

				@Override
				public int compare(TypeBean o1, TypeBean o2) {
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

	@HandlesEvent("getAdById")
	public Resolution getAdById() {
		AdBean a = null;
		try {
			a = adManager.getBeanById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(a).toString());
	}
	
	@HandlesEvent("saveAd")
	public Resolution saveAd() {
		AdBean a = new AdBean();
		try {
			a.setId(id);
			BrandBean brandBean = new BrandBean();
			brandBean.setId(brandId);
			a.setBrand(brandBean);
			
			a.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(endDate));
			a.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(startDate));
			a.setLogoText(logoText);
			TypeBean typeBean = new TypeBean(); 
			typeBean.setId(typeId);
			a.setType(typeBean);
			a.setUniqueId(uid);
			
			if(fileBean != null){
				File file = new File(fileBean, directory+videoDirectory,"ad");
				fileManager.save(file);
				a.setDlFile(new fr.k2i.adnjoy.stripes.bean.FileBean(file));
				
				Metadata metadata = new Metadata();
				InputStream in = new FileInputStream(new java.io.File(file.getFile()));
		        new Tika().parseToString(in, metadata);
		        a.setDuration((long)(Double.parseDouble(metadata.get("duration"))*1000));
			}
		
			adManager.saveBean(a);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}
	
	
	@DefaultHandler
    public Resolution view() {
		return new ForwardResolution("/WEB-INF/jsp/createAd.jsp");
    }

}
