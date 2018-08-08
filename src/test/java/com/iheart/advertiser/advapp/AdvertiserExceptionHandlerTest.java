package com.iheart.advertiser.advapp;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.iheart.advertiser.advapp.model.AdvertiserError;
import com.iheart.advertiser.advapp.validation.AdvertiserExceptionHandler;

@RunWith(MockitoJUnitRunner.class)
public class AdvertiserExceptionHandlerTest {
	
	
	@InjectMocks
 	private AdvertiserExceptionHandler advertiserExceptionHandler;
	
	@Test
	public void testHandleException() {
		AdvertiserError advertiserError	= advertiserExceptionHandler.handleException(new Exception("jUnitTestError"));
		
		assertEquals("INTERNAL_SERVER_ERROR", advertiserError.getCode());
		
	}

}
