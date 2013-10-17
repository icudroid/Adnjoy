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
import fr.k2i.adnjoy.dwr.bean.StockObjectBean;
import fr.k2i.adnjoy.manager.BrandManager;
import fr.k2i.adnjoy.manager.FileManager;
import fr.k2i.adnjoy.manager.StockObjectManager;

@UrlBinding("/Admin/StockObject.htm")
public class StockObjectActionBean extends BaseActionBean {
	
	@SpringBean
	private BrandManager brandManager;
	
	@SpringBean
	private StockObjectManager stockObjectManager;
	
	@SpringBean
	private FileManager fileManager;
	
    @SpringBean
    private String directory;
    @SpringBean
    private String imageDirectory;
	
	private Long id;
	private Long brandId;
	private Double value;
	private String name;
	private String description;
	private Long dlFile;
	private FileBean fileBean;
	private Long available;
	private Integer addNb;
	
	public FileBean getFileBean() {
		return fileBean;
	}

	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}

	public Integer getAddNb() {
		return addNb;
	}

	public void setAddNb(Integer addNb) {
		this.addNb = addNb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public StockObjectManager getStockObjectManager() {
		return stockObjectManager;
	}

	public void setStockObjectManager(StockObjectManager stockObjectManager) {
		this.stockObjectManager = stockObjectManager;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDlFile() {
		return dlFile;
	}

	public void setDlFile(Long dlFile) {
		this.dlFile = dlFile;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

	@HandlesEvent("getBrands")
	public Resolution getBrands() {
		ExtResponseBean res = null;
		try {
			List<BrandBean> allJsonObj = brandManager.getAllJsonObj();
			
			Collections.sort(allJsonObj, new Comparator<BrandBean>() {

				@Override
				public int compare(BrandBean o1, BrandBean o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			
			res = new ExtResponseBean();
			BrandBean adBeBack = new BrandBean();
			adBeBack.setId(null);
			adBeBack.setName("AdBeBack");
			allJsonObj.add(adBeBack);
			res.setResults(allJsonObj.size());
			res.setRows(allJsonObj);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
	@HandlesEvent("getStockObjectsByBrand")
	public Resolution getStockObjectsByBrand() {
		ExtResponseBean res = null;
		try {
			List<StockObjectBean> list = stockObjectManager.getByBrandId(brandId);
			res = new ExtResponseBean();
			res.setResults(list.size());
			res.setRows(list);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(res).toString());
    }

	@HandlesEvent("getStockObjectsById")
	public Resolution getStockObjectsById() {
		StockObjectBean so = null;
		try {
			so = stockObjectManager.getBeanById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(so).toString());
    }
	
	@HandlesEvent("saveStockObject")
	public Resolution saveStockObject() {
		StockObjectBean so = new StockObjectBean();
		try {
			so.setId(id);
			so.setAvailable(available);
			so.setBrandId(brandId);
			so.setDescription(description);
			if(fileBean == null){
				fr.k2i.adnjoy.stripes.bean.FileBean fb = new fr.k2i.adnjoy.stripes.bean.FileBean();
				fb.setId(dlFile);
				so.setDlFile(fb);
			}else{
				File file = new File(fileBean, directory+imageDirectory,"winObject");
				fileManager.save(file);
				so.setDlFile( new fr.k2i.adnjoy.stripes.bean.FileBean(file));
			}
			so.setName(name);
			so.setValue(value);
		
			stockObjectManager.saveBean(so,addNb);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
    }

	@HandlesEvent("deleteStockObject")
	public Resolution deleteStockObject() {
		try {
			stockObjectManager.deletebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
//		return getStockObjectsByBrand();
		return new StreamingResolution("text", "{success: true}");
    }

	
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/stockObject.jsp");
    }
    
    
    
}
