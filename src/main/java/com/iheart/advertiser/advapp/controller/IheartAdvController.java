package com.iheart.advertiser.advapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iheart.advertiser.advapp.model.Advertiser;
import com.iheart.advertiser.advapp.service.IheartAdvService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/api/advertiser")
@Validated

public class IheartAdvController {
	
	@Autowired
	private IheartAdvService iheartAdvService;

	 @ApiOperation(value = "getAdvertiserInfo", notes = "Give the information of a particular advertiser")
	    @ApiResponses(value = {@ApiResponse(code = 200, message = "advertiser details response"),
	            @ApiResponse(code = 400,  message = "bad input params"),
	            @ApiResponse(code = 404, message = "no details found")})
	
	@GetMapping(value = "/{advertiserId}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Advertiser> getAdvertiser(@PathVariable int advertiserId) {
		
		Advertiser resp = iheartAdvService.getAdvertiserInfo(advertiserId);
		return new ResponseEntity<Advertiser>(resp, HttpStatus.CREATED);
	}

	 @GetMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<List<Advertiser>> getAllAdvertiser() {
			
			List<Advertiser> resp = iheartAdvService.getAllAdvertiser();
			return new ResponseEntity<List<Advertiser>>(resp, HttpStatus.CREATED);
		}

	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> addAdvertiser(@RequestBody(required=true) Advertiser advertiser) {
		
		// TODO Validate Request Object
		
		String resp = iheartAdvService.addAdvertiser(advertiser);
	
		return new ResponseEntity<String>(resp, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{advertiserId}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> updateAdvertiser(@PathVariable int advertiserId, @RequestBody(required=true) Advertiser advertiser) {
		advertiser.setAdvertiserId(advertiserId);
		
		String resp = iheartAdvService.updateAdvertiserById(advertiser);
		return new ResponseEntity<String>(resp, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{advertiserId}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteAdvertiser(@PathVariable(required=true) int advertiserId) {
		
		String resp = iheartAdvService.deleteAdvertiserById(advertiserId);
		//Advertiser resp = repository.findAdvByName(advertiser.getAdvName());
		return new ResponseEntity<String>(resp, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{advertiserId}/creditCheck/{creditBalance}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> doCreditCheck(@PathVariable(required=true) int advertiserId,@PathVariable(required=true) long creditBalance) {
		
		// TODO Validate Request Object
		// TODO Validate for Duplicate Data
		
		Boolean resp = iheartAdvService.doCreditCheck(advertiserId,creditBalance);
		//Advertiser resp = repository.findAdvByName(advertiser.getAdvName());
		return new ResponseEntity<Boolean>(resp, HttpStatus.CREATED);
	}
}
