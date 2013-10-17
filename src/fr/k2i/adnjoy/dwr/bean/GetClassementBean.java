package fr.k2i.adnjoy.dwr.bean;

public class GetClassementBean {
	private String sort;
	private String dir;
	private int startIndex;
	private int results;
	private long idJackPot;
	public GetClassementBean(Long idJackPot, String sort, String dir,
			Integer startIndex, Integer results) {
		this.sort = sort;
		this.dir = dir;
		this.startIndex = startIndex;
		this.results = results;
		this.idJackPot = idJackPot;
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
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public long getIdJackPot() {
		return idJackPot;
	}
	public void setIdJackPot(long idJackPot) {
		this.idJackPot = idJackPot;
	}
}
