package com.safetynet.safetynetalerts.interfaces;

import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;

public interface EntityLinker {
	
	public void linkEntities() throws MissingEntitiesException;

}
