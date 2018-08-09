package com.iheart.advertiser.advapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.iheart.advertiser.advapp.constants.IHeartConstants;
import com.iheart.advertiser.advapp.model.Advertiser;
import com.iheart.advertiser.advapp.model.AdvertiserError;
import com.iheart.advertiser.advapp.service.IheartAdvService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/advertiser")
@Validated

public class IheartAdvController {

	@Autowired
	private IheartAdvService iheartAdvService;

	@Value("${iheart.api.timeout}")
	private Long iHeartApiTimeout;

	/**
	 * 
	 * @param advertiserId
	 * @return Advertiser Info based on the advertiserId
	 */

	@ApiOperation(value = "getAdvertiserInfo", notes = "Give the information of a particular advertiser")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Advertiser.class, message = "advertiser details response"),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })

	@GetMapping(value = "/{advertiserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DeferredResult<ResponseEntity<Advertiser>> getAdvertiserById(@PathVariable int advertiserId) {

		DeferredResult<ResponseEntity<Advertiser>> result = new DeferredResult<>(iHeartApiTimeout);
		Advertiser advertiser = iheartAdvService.getAdvertiserInfo(advertiserId);
		if (null != advertiser) {
			result.setResult(new ResponseEntity<>(advertiser, HttpStatus.OK));

		} else {
			handleError(IHeartConstants.NOT_FOUND_STATUS_CODE, IHeartConstants.NOT_FOUND_STATUS_MESSAGE,
					HttpStatus.NOT_FOUND, result);

		}
		return result;

	}

	/**
	 * List of Advertiser Info that are registered with the program
	 * 
	 * @return
	 */
	@ApiOperation(value = "getAdvertiserInfo", notes = "Give the information of a particular advertiser")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Advertiser.class, message = "advertiser details response"),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	DeferredResult<ResponseEntity<List<Advertiser>>> getAllAdvertiser() {

		DeferredResult<ResponseEntity<List<Advertiser>>> result = new DeferredResult<>(iHeartApiTimeout);

		List<Advertiser> resp = iheartAdvService.getAllAdvertiser();
		if (null != resp) {
			result.setResult(new ResponseEntity<>(resp, HttpStatus.OK));

		} else {
			handleError(IHeartConstants.NOT_FOUND_STATUS_CODE, IHeartConstants.NOT_FOUND_STATUS_MESSAGE,
					HttpStatus.NOT_FOUND, result);

		}
		return result;

	}

	/**
	 * 
	 * @param advertiser
	 * @return
	 */

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "addAdvertiser", notes = "Used to add the advertiser to the system")
	@ApiResponses(value = { @ApiResponse(code = 201,response = Advertiser.class, message = "advertiser details response"),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })

	public @ResponseStatus(HttpStatus.CREATED) @ResponseBody DeferredResult<ResponseEntity<Advertiser>> addAdvertiser(
			@RequestBody(required = true) Advertiser advertiser) {
		DeferredResult<ResponseEntity<Advertiser>> result = new DeferredResult<>(iHeartApiTimeout);

		Advertiser resp = iheartAdvService.addAdvertiser(advertiser);
		if (null!= resp) {
			result.setResult(new ResponseEntity<>(resp,HttpStatus.CREATED));
		} else {
			handleError(IHeartConstants.DUPLICATE_STATUS_CODE, IHeartConstants.DUPLICATE_STATUS_MESSAGE,
					HttpStatus.BAD_REQUEST, result);

		}

		return result;
	}

	/**
	 * 
	 * @param advertiserId
	 * @param advertiser
	 * @return
	 */
	@ApiOperation(value = "updateAdvertiser", notes = "Update the adviser information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "advertiser details response"),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })

	@PutMapping(value = "/{advertiserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DeferredResult<ResponseEntity<String>> updateAdvertiser(@PathVariable int advertiserId,
			@RequestBody(required = true) Advertiser advertiser) {

		DeferredResult<ResponseEntity<String>> result = new DeferredResult<>(iHeartApiTimeout);
		advertiser.setAdvertiserId(advertiserId);

		String resp = iheartAdvService.updateAdvertiserById(advertiser);
		if (resp.equals(IHeartConstants.SUCCESS)) {
			result.setResult(new ResponseEntity<>(HttpStatus.OK));
		} else {
			handleError(IHeartConstants.NO_PROFILE_FOUND_CODE, IHeartConstants.NO_PROFILE_FOUND_MESSAGE,
					HttpStatus.NOT_FOUND, result);

		}

		return result;
	}

	/**
	 * 
	 * @param advertiserId
	 * @return
	 */
	@ApiOperation(value = "deleteAdvertiser", notes = "delete the adviser information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })

	@DeleteMapping(value = "/{advertiserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DeferredResult<ResponseEntity<String>> deleteAdvertiser(
			@PathVariable(required = true) int advertiserId) {

		DeferredResult<ResponseEntity<String>> result = new DeferredResult<>(iHeartApiTimeout);

		String resp = iheartAdvService.deleteAdvertiserById(advertiserId);
		if (resp.equals(IHeartConstants.SUCCESS)) {
			result.setResult(new ResponseEntity<>(HttpStatus.OK));
		} else {
			handleError(IHeartConstants.NOT_FOUND_STATUS_CODE, IHeartConstants.NOT_FOUND_STATUS_MESSAGE,
					HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, result);

		}
		return result;
	}

	/*
	 * This method is used to check whether customers have enough balance in the
	 * profile
	 */

	@ApiOperation(value = "doCreditCheck", notes = "Used to get the credit check for the advertisers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "advertiser details response"),
			@ApiResponse(code = 400, response = AdvertiserError.class, message = "bad input params"),
			@ApiResponse(code = 404, response = AdvertiserError.class, message = "no details found") })
	@GetMapping(value = "/{advertiserId}/{creditAmount}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DeferredResult<ResponseEntity<Boolean>> doCreditCheck(
			@PathVariable(required = true) int advertiserId, @PathVariable(required = true) long creditAmount) {
		DeferredResult<ResponseEntity<Boolean>> result = new DeferredResult<>(iHeartApiTimeout);

		boolean resp = iheartAdvService.doCreditCheck(advertiserId, creditAmount);
		result.setResult(new ResponseEntity<>(resp, HttpStatus.OK));

		return result;
	}
	/*
	 * This method is used to credit the amount from customer profile.
	 */

	@ApiOperation(value = "creditFromAccount", notes = "Method used to credit money from advertiser")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 400, message = "bad input params"),
			@ApiResponse(code = 404, message = "no details found"),
			@ApiResponse(code = 406, message = "Not able to authorize the amount") })
	@PostMapping(value = "/{advertiserId}/{creditAmount}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DeferredResult<ResponseEntity<Boolean>> creditFromAccount(
			@PathVariable(required = true) int advertiserId, @PathVariable(required = true) long creditAmount) {
		DeferredResult<ResponseEntity<Boolean>> result = new DeferredResult<>(iHeartApiTimeout);
		Boolean resp = iheartAdvService.creditAmountFromAdvertiser(advertiserId, creditAmount);

		if (resp) {
			result.setResult(new ResponseEntity<>(HttpStatus.OK));
		} else {
			handleError(IHeartConstants.NOT_FOUND_STATUS_CODE, IHeartConstants.MONEY_NOT_DEDUCTED,
					HttpStatus.UNAUTHORIZED, result);

		}

		return result;
	}

	/**
	 * This method is used to generate common error code for all the scenarios.
	 * 
	 * @param errorCode
	 * @param errorMessage
	 * @param status
	 * @param result
	 */

	private void handleError(String errorCode, String errorMessage, HttpStatus status,
			@SuppressWarnings("rawtypes") DeferredResult result) {
		AdvertiserError error = new AdvertiserError();
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		result.setErrorResult(new ResponseEntity<AdvertiserError>(error, status));

	}
}
