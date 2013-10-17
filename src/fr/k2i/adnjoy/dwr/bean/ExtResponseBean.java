package fr.k2i.adnjoy.dwr.bean;

import java.util.List;

public class ExtResponseBean {
	private long version;
	private int results;
	private boolean success;
	private List<?>rows;
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
}
