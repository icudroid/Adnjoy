package fr.k2i.adnjoy.dwr.bean;

import fr.k2i.adnjoy.business.user.Sex;

public class EnumBean {
	private String id;
	private String label;
	public EnumBean(){}
	public EnumBean(Sex sex) {
		id = sex.name();
		label = sex.getLabel();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
