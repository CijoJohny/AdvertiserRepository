package com.iheart.advertiser.advapp.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iheart.advertiser.advapp.model.AdvertiserError;
	

@ControllerAdvice
public class AdvertiserExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AdvertiserExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public AdvertiserError handleException(Exception excep) {
		// TODO we need to handle for different exceptions instead of generic Exception 
		logger.error("Adviser Exception:", excep);
		AdvertiserError error = new AdvertiserError();
		error.setCode("INTERNAL_SERVER_ERROR");
		error.setMessage("Please try again later");
		return error;
	}

}
