package com.safetynet.safetynetalerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MissingEntitiesException extends Exception {

	public MissingEntitiesException(String errorMessage) {
		super(errorMessage);
	}

}
