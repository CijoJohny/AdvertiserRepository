package com.iheart.advertiser.advapp.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iheart.advertiser.advapp.constants.IHeartConstants;
import com.iheart.advertiser.advapp.model.AdvertiserError;

@ControllerAdvice
public class AdvertiserExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(AdvertiserExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public AdvertiserError handleException(Exception excep) {

		logger.error("Advertiser Exception:", excep);
		AdvertiserError error = new AdvertiserError();
		error.setCode(IHeartConstants.INTERNAL_SERVER_ERROR);
		error.setMessage(IHeartConstants.INTERNAL_SERVER_ERROR_MESSAGE);
		return error;
	}

}
