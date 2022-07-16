package com.safetynet.safetynetalerts.model;

import com.safetynet.safetynetalerts.exception.MissingEntitiesException;

public interface EntityLinker {
	
	public void linkEntities() throws MissingEntitiesException;

}
