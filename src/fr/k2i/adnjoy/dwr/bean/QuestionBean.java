package fr.k2i.adnjoy.dwr.bean;

import java.util.List;

public class QuestionBean {
	private String url;
	private List<ResponseBean> responses;
	private long duration;
	private long pauseBefore; 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ResponseBean> getResponses() {
		return responses;
	}
	public void setResponses(List<ResponseBean> responses) {
		this.responses = responses;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getPauseBefore() {
		return pauseBefore;
	}
	public void setPauseBefore(long pauseBefore) {
		this.pauseBefore = pauseBefore;
	}
	
}
