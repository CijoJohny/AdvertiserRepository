package com.iheart.advertiser.advapp.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class CustomizeInfoEndPoint {

	@Autowired
	private InfoEndpoint actuatorInfo;

	@ReadOperation
	public WebEndpointResponse<Map> info() {

		Map<String, Object> info = this.actuatorInfo.info();
		Map<String, Object> adviserInfo = new HashMap<String, Object>();
		adviserInfo.put("Build", info.get("build"));
		adviserInfo.put("Author", "Cijo Johny");
		adviserInfo.put("About", "This application handles add, update, delete, get operations on Adviser data.");
		return new WebEndpointResponse<>(adviserInfo, 200);

	}

}
