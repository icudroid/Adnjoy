package fr.k2i.adnjoy.service.impl;

import fr.k2i.adnjoy.manager.JackPotManager;
import fr.k2i.adnjoy.service.CreationBTJob;

public class CreationBTJobImpl implements CreationBTJob{

    private JackPotManager jackPotManager;
    
	public void setJackPotManager(JackPotManager jackPotManager) {
		this.jackPotManager = jackPotManager;
	}

	@Override
	public void doIt() throws Exception {
		jackPotManager.generateDayJackPots();
	}
	
}
