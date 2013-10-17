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

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.UserBean;
import fr.k2i.adnjoy.manager.CountryManager;
import fr.k2i.adnjoy.manager.UserManager;

@UrlBinding("/ModAccount.htm")
public class ModifyAccountActionBean extends BaseActionBean {

	@SpringBean
    private CountryManager countryManager;
   @SpringBean
    private UserManager userManager;
    
    private String pseudo;
    private String sex;
    private String firstName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String zipCode;
    private String city;
    private String country;
    private String birthday;
    private String password;
    


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserManager getUserManager() {
		return userManager;
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("/WEB-INF/jsp/modAccount.jsp");
	}

	@HandlesEvent("loadAccount")
    public Resolution loadAccount() {
		User user = (User)this.getContext().getRequest().getSession().getAttribute("user");
		UserBean ub = null;
		try {
			ub = userManager.getUserBean(user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new StreamingResolution("text",new JSONObject(ub).toString());
    }

	@HandlesEvent("modAccount")
	public Resolution modAccount(){
		try {
			User u = (User)this.getContext().getRequest().getSession().getAttribute("user");
			User user = new User();
			user.setAddress1(address1);
			user.setAddress2(address2);
			user.setCity(city);
			user.setCountry(countryManager.getById(Long.parseLong(country)));
			user.setEmail(email);
			user.setPassword(password);
			user.setZipCode(zipCode);
			userManager.modAccount(u.getId(),user);
		} catch (Exception e) {
			//return new ForwardResolution("/WEB-INF/jsp/error.jsp")
			return new StreamingResolution("text", "{success: false}");
		}
		//return new ForwardResolution("/WEB-INF/jsp/accountCreated.jsp");
		return new StreamingResolution("text", "{success: true}");
		
		
		
//		try {
//			boolean error = false;
//			user.setId(((User) getContext().getRequest().getSession().getAttribute("user")).getId()); 
//	        if (user.getPassword().equals("") || user.getPassword()==null) {
//	        	 getContext().getValidationErrors().add("user.password", new SimpleError("Vous devez renseigner un mot de passe."));
//	            error = true;
//	        }
//			if(error == false){
//				userManager.saveFromUI(user);
//				return new ForwardResolution("/WEB-INF/jsp/accountModified.jsp");
//			}else{
//				return new ForwardResolution("/WEB-INF/jsp/modAccount.jsp");
//			}
//		} catch (Exception e) {
//			return new ForwardResolution("/WEB-INF/jsp/error.jsp");
//		}
		
	}
}
