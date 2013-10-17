package fr.k2i.adnjoy.stripes.action;

import java.io.FileInputStream;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.manager.FileManager;

@UrlBinding("/Download.htm")
public class DownloadAction extends BaseActionBean {
	
	@SpringBean
	private FileManager fileManager;

	String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@DefaultHandler
	public Resolution view() {
		try {
			File f = fileManager.getByUrl(url);
			StreamingResolution streamingResolution = new StreamingResolution(f.getContentType(), new FileInputStream(new java.io.File(f.getFile())));
			streamingResolution.setLength(new java.io.File(f.getFile()).length());
			return streamingResolution;
		} catch (Exception e) {
			return new JavaScriptResolution(null);
		}
    }

}
