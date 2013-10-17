package fr.k2i.adnjoy.business.user;

public enum Sex {

	MR("Mr"),MME("Mme"),MLLE("Mlle");
	
	private String label;
	
	public String getLabel(){
		return label;
	}
	
	private Sex(String label){
		this.label = label;
	}
	
}
