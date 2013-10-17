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
import fr.k2i.adnjoy.dwr.bean.ChanelBean;
import fr.k2i.adnjoy.dwr.bean.CountryBean;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.manager.ChanelManager;
import fr.k2i.adnjoy.manager.CountryManager;
import fr.k2i.adnjoy.manager.FileManager;

@UrlBinding("/Admin/AddChanel.htm")
public class AddChanelActionBean extends BaseActionBean {

    @SpringBean
    private CountryManager countryManager;
    
    @SpringBean
    private ChanelManager chanelManager;
    
    @SpringBean
    private FileManager fileManager;
    
    @SpringBean
    private String directory;
    @SpringBean
    private String imageDirectory;
	
    private Long id;
    
    private String filterChanel;
    
    private Long logoId;
    private Long countryId;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String fax;
    private String login;
    
    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilterChanel() {
		return filterChanel;
	}

	public void setFilterChanel(String filterChanel) {
		this.filterChanel = filterChanel;
	}

	private FileBean fileBean;
    
    public FileBean getFileBean() {
		return fileBean;
	}

	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}
    
	@DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/createChanel.jsp");
    }

	
	
	@HandlesEvent("getCountries")
	public Resolution getCountries(){
		
		ExtResponseBean res = null;
		try {
			List<CountryBean> allJsonObj = countryManager.getCountriesBean();
			
			Collections.sort(allJsonObj, new Comparator<CountryBean>() {

				@Override
				public int compare(CountryBean o1, CountryBean o2) {
					return o1.getCountry().compareTo(o2.getCountry());
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
	
	
	@HandlesEvent("getChanelById")
	public Resolution getChanelById(){
		
		ChanelBean c = null;
		try {
			c = chanelManager.getBeanById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new StreamingResolution("text",new JSONObject(c).toString());
	}
	
	@HandlesEvent("getChanels")
	public Resolution getChanels(){
		
		ExtResponseBean res = null;
		try {
			List<ChanelBean> allJsonObj = chanelManager.getAllBean(filterChanel);
			
			Collections.sort(allJsonObj, new Comparator<ChanelBean>() {

				@Override
				public int compare(ChanelBean o1, ChanelBean o2) {
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
	
	@HandlesEvent("saveChanel")
	public Resolution saveChanel(){
		ChanelBean c = new ChanelBean();
		try {
			c.setId(id);
			if(countryId !=null)
				c.setCountry(countryManager.getCountryBeanById(countryId));
			c.setEmail(email);
			c.setFax(fax);
			c.setLogin(login);
			c.setName(name);
			c.setPassword(password);
			c.setPhone(phone);
			
			if(fileBean == null)
				c.setLogoId(logoId);
			else{
				File file = new File(fileBean, directory+imageDirectory,"chanel");
				fileManager.save(file);
				c.setLogoId(file.getId());
			}
			c.setName(name);
		
			chanelManager.saveBean(c);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}

	@HandlesEvent("deleteChanel")
		public Resolution deleteChanel() {
		try {
			chanelManager.deletebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new StreamingResolution("text", "{success: false}");
		}
//		return getStockObjectsByBrand();
		return new StreamingResolution("text", "{success: true}");
    }
}
