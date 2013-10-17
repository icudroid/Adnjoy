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
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.manager.UserManager;

@UrlBinding("/ValidateAccount.htm")
public class ValidateAccountActionBean extends BaseActionBean {

    private UserManager userManager;

    @SpringBean
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@DefaultHandler
    public Resolution view() {
		String number = this.getContext().getRequest().getParameter("validateNumber");
		try {
			if(!userManager.validateAccount(number)){
				return new ForwardResolution("/WEB-INF/jsp/error.jsp");
			}
		} catch (Exception e) {
			return new ForwardResolution("/WEB-INF/jsp/error.jsp");
		}
        return new ForwardResolution("/WEB-INF/jsp/home.jsp");
    }


}
