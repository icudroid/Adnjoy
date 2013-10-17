package fr.k2i.adnjoy.business;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sourceforge.stripes.action.FileBean;
@Entity
@Table (name="TBL_FILE")
public class File extends BusinessObject {
	
	private static final long serialVersionUID = 3955627943561661644L;
//	private static final String DIRECTORY = "i://images//";
	private String dlUrl;
//	private Blob file;
	private String file;
	private String filename;
	private String contentType;
	private	Long fileSize;
	
	
	public File(FileBean logo,String directory,String type) throws IOException {
		contentType = logo.getContentType();
		filename = logo.getFileName();
		fileSize = logo.getSize();
		dlUrl = type+"/"+Long.toHexString(new Date().getTime())+"/"+filename;
		file  = directory+Long.toHexString(new Date().getTime())+filename;
			logo.save(new java.io.File(file));
//			setFile( Hibernate.createBlob( logo.getInputStream() ) );
	}


	@Column(unique=true,nullable=false)
	public String getDlUrl() {
		return dlUrl;
	}



	public void setDlUrl(String dlUrl) {
		this.dlUrl = dlUrl;
	}


//	@Lob
//	public Blob getFile() {
//		return file;
//	}
//
//
//
//	public void setFile(Blob file) {
//		this.file = file;
//	}


	@Column(unique=true,nullable=false)
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
	
//	public InputStream getDlFiletream()throws SQLException{
//	if (getFile() == null)
//		return null;
//		return getFile().getBinaryStream();
//	}
//	
//	
//	public void setDlFiletream( InputStream sourceStream )throws IOException{
//		setFile( Hibernate.createBlob( sourceStream ) );
//	}


	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public File() {
		super();
	}

}
