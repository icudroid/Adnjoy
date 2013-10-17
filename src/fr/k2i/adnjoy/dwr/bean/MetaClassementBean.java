package fr.k2i.adnjoy.dwr.bean;

import java.util.List;

public class MetaClassementBean {
	private List<JackPotClassementBean> records;
	private int startIndex;
	private String sort;
	private String dir;
	private int pageSize;
	private int recordsReturned;
	private int totalRecords;
	public List<JackPotClassementBean> getRecords() {
		return records;
	}
	public void setRecords(List<JackPotClassementBean> records) {
		this.records = records;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordsReturned() {
		return recordsReturned;
	}
	public void setRecordsReturned(int recordsReturned) {
		this.recordsReturned = recordsReturned;
	}
	
}
