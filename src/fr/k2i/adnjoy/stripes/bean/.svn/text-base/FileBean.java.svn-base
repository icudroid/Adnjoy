package fr.k2i.adnjoy.stripes.bean;

import fr.k2i.adnjoy.business.File;

public class FileBean {
	private Long id;
	private String dlUrl;
	private String file;
	private String filename;
	private String contentType;
	private	Long fileSize;
	public FileBean(File dlFile) {
		id = dlFile.getId();
		dlUrl = dlFile.getDlUrl();
		file = dlFile.getFile();
		filename = dlFile.getFilename();
		contentType = dlFile.getContentType();
		fileSize = dlFile.getFileSize();
	}
	public FileBean() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDlUrl() {
		return dlUrl;
	}
	public void setDlUrl(String dlUrl) {
		this.dlUrl = dlUrl;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
