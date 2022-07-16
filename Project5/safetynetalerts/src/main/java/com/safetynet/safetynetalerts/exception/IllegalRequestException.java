package com.safetynet.safetynetalerts.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
//TODO : trouver comment afficher reason
@ResponseStatus(code = HttpStatus.CONFLICT)
public class IllegalRequestException extends Exception {

	public IllegalRequestException(String errorMessage) {
		super(errorMessage);
	}
	
}
