package fr.k2i.adnjoy.stripes.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.dwr.bean.WonObjectBean;
import fr.k2i.adnjoy.manager.UserManager;


@UrlBinding("/getWonObjects.htm")
public class GetWonObjects extends BaseActionBean {
	private int start;
	private int limit;
	
	@SpringBean
	private UserManager userManager;
	
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
	
	
	@DefaultHandler
    public Resolution view() {
		ExtResponseBean res = null;
		try {
			User user = (User)this.getContext().getRequest().getSession().getAttribute("user");
			List<WonObjectBean> list = userManager.getWonObjects(user.getId(),start,limit);
			res = new ExtResponseBean();
			res.setResults(userManager.getWonObjects(user.getId(),0,0).size());
			res.setRows(list);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
}
