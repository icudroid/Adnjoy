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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.util.WebUtils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.business.user.Right;
import fr.k2i.adnjoy.business.user.Sex;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.BrandBean;
import fr.k2i.adnjoy.dwr.bean.CountryBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.CountryManager;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.stripes.WebUtil;
import fr.k2i.adnjoy.stripes.action.BaseActionBean;
import fr.k2i.adnjoy.stripes.bean.ErrorBean;
import fr.k2i.adnjoy.stripes.bean.SexBean;

@UrlBinding("/Android/CreateAcount.htm")
public class CreateAccountActionBean extends BaseActionBean {

	@SpringBean
    private UserManager userManager;
	@SpringBean
    private CountryManager countryManager;
    
    
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
		return new ForwardResolution("/WEB-INF/jsp/createAccount.jsp");
    }

	
	@HandlesEvent("getSexes")
	public Resolution getSexes(){
		ExtResponseBean res = new ExtResponseBean();
		List<SexBean> sexes = new ArrayList<SexBean>();
		Sex[] values = Sex.values();
		for (Sex sex : values) {
			sexes.add(new SexBean(sex));
		}
		res.setResults(sexes.size());
		res.setRows(sexes);
		res.setSuccess(true);
		return new StreamingResolution("text",new JSONObject(res).toString());
	}

	@HandlesEvent("getCountries")
	public Resolution getCountries(){
		ExtResponseBean res = new ExtResponseBean();
		List<CountryBean> countriesBean = null;
		try {
			countriesBean = countryManager.getCountriesBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setResults(countriesBean.size());
		res.setRows(countriesBean);
		res.setSuccess(true);
		return new StreamingResolution("text",new JSONObject(res).toString());
	}

	@HandlesEvent("createAccount")
	public Resolution createAccount(){
		ExtResponseBean res = new ExtResponseBean();
		List<ErrorBean> errs = new ArrayList<ErrorBean>();
		Date birthdayDate = null;
		
		if(address1==null){
			ErrorBean err = new ErrorBean();
			err.setField("address1");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}

		if(birthday==null){
			ErrorBean err = new ErrorBean();
			err.setField("birthday");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}else{
			//test 18 ans
			Calendar now = new GregorianCalendar();
			Calendar cal = new GregorianCalendar(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
			cal.add(Calendar.YEAR, -18);
			
			try {
				birthdayDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
				if(birthdayDate.after(cal.getTime())){
					ErrorBean err = new ErrorBean();
					err.setField("birthday");
					err.setMessage("Vous devez être majeur");
					errs.add(err);
				}
				
			} catch (ParseException e) {
				ErrorBean err = new ErrorBean();
				err.setField("birthday");
				err.setMessage("Date de naissance incorrecte");
				errs.add(err);
			}
		}
		
		if(city==null){
			ErrorBean err = new ErrorBean();
			err.setField("city");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}
		
		if(email==null){
			ErrorBean err = new ErrorBean();
			err.setField("email");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}else{
			try {
				if(!userManager.checkUniqueEmail(email)){
					ErrorBean err = new ErrorBean();
					err.setField("email");
					err.setMessage("L'email existe déjà");
					errs.add(err);
				}else if(!WebUtil.isValidEmailAddress(email)){
					ErrorBean err = new ErrorBean();
					err.setField("email");
					err.setMessage("Email non valide");
					errs.add(err);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(firstName==null){
			ErrorBean err = new ErrorBean();
			err.setField("firstName");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}

		if(lastName==null){
			ErrorBean err = new ErrorBean();
			err.setField("lastName");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}
		
		if(password==null){
			ErrorBean err = new ErrorBean();
			err.setField("password");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}else if(password.length()<6){
			ErrorBean err = new ErrorBean();
			err.setField("password");
			err.setMessage("Le mot de passe doit contenir 6 caractères minimum");
			errs.add(err);
		}
		
		if(pseudo==null){
			ErrorBean err = new ErrorBean();
			err.setField("pseudo");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}else{
			try {
				if(!userManager.checkUniquePseudo(pseudo)){
					ErrorBean err = new ErrorBean();
					err.setField("pseudo");
					err.setMessage("Le pseudo '"+pseudo+"' existe déjà");
					errs.add(err);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(sex==null){
			ErrorBean err = new ErrorBean();
			err.setField("sex");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}
		
		if(zipCode==null){
			ErrorBean err = new ErrorBean();
			err.setField("zipCode");
			err.setMessage("Champ obligatoire");
			errs.add(err);
		}
		
		if(errs.size()==0){
			try {
				User user = new User();
				user.setAddress1(address1);
				user.setAddress2(address2);
				user.setBirthday(birthdayDate);
				user.setCity(city);
				user.setCountry(countryManager.getById(Long.parseLong(country)));
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPassword(password);
				user.setPseudo(pseudo);
				user.setRight(Right.User);
				user.setSex(Sex.valueOf(sex));
				user.setZipCode(zipCode);
				userManager.createAccount(user);
				res.setSuccess(true);
			} catch (Exception e) {
				//return new ForwardResolution("/WEB-INF/jsp/error.jsp")
				return new StreamingResolution("text", "{success: false}");
			}
		}else{
			res.setSuccess(false);
		}
		res.setResults(errs.size());
		res.setRows(errs);
		return new StreamingResolution("text",new JSONObject(res).toString());
	}
}
