package com.iheart.advertiser.advapp;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.info.InfoEndpoint;

import com.iheart.advertiser.advapp.actuator.CustomizeInfoEndPoint;

@RunWith(MockitoJUnitRunner.class)
public class AdvertiserActuatorTest {
	
	@InjectMocks
	 private CustomizeInfoEndPoint customizeInfoEndPoint;
	
	@Mock
	private InfoEndpoint actuatorInfo;
	
	
	@Test
	public void testInfo() {
		
		WebEndpointResponse<Map> webEndpointResponse = customizeInfoEndPoint.info();
		assertNotNull(webEndpointResponse);
		
	}

}
