package fr.k2i.adnjoy.stripes.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import fr.k2i.adnjoy.manager.FileManager;

@UrlBinding("/Upload.htm")
public class UploadAdAction extends BaseActionBean {
//	private static final String TMP_DIRECTORY = "h:/tmpAd/";
    @SpringBean
    private String directory;
    @SpringBean
    private String tmpDirectory;
	@SpringBean
	private FileManager fileManager;

	private FileBean fileBean;
	
	


	public FileBean getFileBean() {
		return fileBean;
	}




	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}




	@DefaultHandler
	public Resolution view() {
		fr.k2i.adnjoy.business.File file = null;
		try {
			 file = new fr.k2i.adnjoy.business.File(fileBean,directory+tmpDirectory,"tmp");
			fileManager.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StreamingResolution("text", file.getDlUrl());
    }

}
