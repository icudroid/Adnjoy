package fr.k2i.adnjoy.stripes.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.manager.JackPotManager;

@UrlBinding("/Admin/CreateBT.htm")
public class CreateBTActionBean extends BaseActionBean {

    @SpringBean
    private JackPotManager jackPotManager;

	private String  day;
	private int nb;
    
	public JackPotManager getJackPotManager() {
		return jackPotManager;
	}


	public void setJackPotManager(JackPotManager jackPotManager) {
		this.jackPotManager = jackPotManager;
	}


	public int getNb() {
		return nb;
	}


	public void setNb(int nb) {
		this.nb = nb;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}

	@HandlesEvent("generateBT")
	public Resolution generateBT() {
		try {
			Date parse = new SimpleDateFormat("dd/MM/yyyy").parse(day);
			GregorianCalendar genDay = new GregorianCalendar();
			genDay.setTime(parse);
			jackPotManager.generateDayJackPots(genDay,nb,3);
		} catch (Exception e) {
			return new StreamingResolution("text", "{success: false}");
		}
		return new StreamingResolution("text", "{success: true}");
	}
	
	@DefaultHandler
    public Resolution view() {
		return new ForwardResolution("/WEB-INF/jsp/createBT.jsp");
    }




}
