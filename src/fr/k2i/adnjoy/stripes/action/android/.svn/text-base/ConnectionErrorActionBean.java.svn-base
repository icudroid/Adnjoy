package fr.k2i.adnjoy.stripes.action.android;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import fr.k2i.adnjoy.stripes.action.BaseActionBean;

@UrlBinding("/Android/LoginErr.htm")
public class ConnectionErrorActionBean extends BaseActionBean {
	@DefaultHandler
    public Resolution view() {
		return new StreamingResolution("text","{failed:1}");
	}
}
