package com.iheart.advertiser.advapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.advertiser.advapp.constants.IHeartConstants;
import com.iheart.advertiser.advapp.controller.IheartAdvController;
import com.iheart.advertiser.advapp.model.Advertiser;
import com.iheart.advertiser.advapp.service.IheartAdvService;

@RunWith(MockitoJUnitRunner.class)
public class IheartAdvControllerTest {

	@InjectMocks
	private IheartAdvController controller;

	@InjectMocks
	private IHeartConstants iHeartConstants;

	@Mock
	public IheartAdvService iheartAdvService;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void testAddAdvertiser() throws Exception {
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.addAdvertiser(ArgumentMatchers.any(Advertiser.class))).thenReturn(request);

		MvcResult mvcResult = mockMvc.perform(
				post("/api/advertiser").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isCreated()).andReturn();
		

	}

	@Test
	public void testAddAdvertiserError() throws Exception {
		Advertiser requestError = new Advertiser();
		requestError.setAdvName("TestErrorReq");
		requestError.setAdvContactName("Cijo Johny");
		requestError.setAdvCreditLimit(100L);
		requestError.setAdvertiserId(11);
		requestError.setStatus("ACTIVE");

		when(iheartAdvService.addAdvertiser(ArgumentMatchers.any(Advertiser.class))).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(
				post("/api/advertiser").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(requestError)))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	public void testGetAdvertiserById() throws Exception {
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);
		
		when(iheartAdvService.getAdvertiserInfo(ArgumentMatchers.any(Integer.class))).thenReturn(request);

		MvcResult mvcResult = mockMvc.perform(
				get("/api/advertiser/2").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testGetAdvertiserByIdError() throws Exception {
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.getAdvertiserInfo(ArgumentMatchers.any(Integer.class))).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(
				get("/api/advertiser/2").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testGetAdvertiser() throws Exception {
		List<Advertiser> advList = new ArrayList<Advertiser>();
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);
		Advertiser request2 = new Advertiser();
		request2.setAdvName("Cijo");
		request2.setAdvContactName("Cijo Johny");
		request2.setAdvCreditLimit(100L);
		request2.setAdvertiserId(11);
		advList.add(request);
		advList.add(request2);

		when(iheartAdvService.getAllAdvertiser()).thenReturn(advList);

		MvcResult mvcResult = mockMvc.perform(
				get("/api/advertiser").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testGetAdvertiserError() throws Exception {
		List<Advertiser> advList = new ArrayList<Advertiser>();
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);
		Advertiser request2 = new Advertiser();
		request2.setAdvName("Cijo");
		request2.setAdvContactName("Cijo Johny");
		request2.setAdvCreditLimit(100L);
		request2.setAdvertiserId(11);
		advList.add(request);
		advList.add(request2);

		when(iheartAdvService.getAllAdvertiser()).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(
				get("/api/advertiser").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void updateAdvertiserTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.updateAdvertiserById(ArgumentMatchers.any(Advertiser.class))).thenReturn("success");

		MvcResult mvcResult = mockMvc.perform(put("/api/advertiser/1000").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(request))).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void updateAdvertiserTestError() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.updateAdvertiserById(ArgumentMatchers.any(Advertiser.class))).thenReturn("error");

		MvcResult mvcResult = mockMvc.perform(put("/api/advertiser/1000").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(request))).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void deleteAdvertiserTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.deleteAdvertiserById(ArgumentMatchers.any(Integer.class))).thenReturn("success");

		MvcResult mvcResult = mockMvc.perform(delete("/api/advertiser/1000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void deleteAdvertiserTestError() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.deleteAdvertiserById(ArgumentMatchers.any(Integer.class))).thenReturn("error");

		MvcResult mvcResult = mockMvc.perform(delete("/api/advertiser/1000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void doCreditCheckTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.doCreditCheck(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Long.class)))
				.thenReturn(new Boolean(true));

		MvcResult mvcResult = mockMvc.perform(get("/api/advertiser/1000/2000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();
		
	}

	@Test
	public void doCreditCheckTestError() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.doCreditCheck(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Long.class)))
				.thenReturn(new Boolean(false));

		MvcResult mvcResult = mockMvc.perform(get("/api/advertiser/1000/2000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();
		

	}

	@Test
	public void creditFromAccountTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.creditAmountFromAdvertiser(ArgumentMatchers.any(Integer.class),
				ArgumentMatchers.any(Long.class))).thenReturn(new Boolean(true));

		MvcResult mvcResult = mockMvc.perform(post("/api/advertiser/1000/2000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();
		

	}

	@Test
	public void creditFromAccountTestError() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setAdvertiserId(11);

		when(iheartAdvService.creditAmountFromAdvertiser(ArgumentMatchers.any(Integer.class),
				ArgumentMatchers.any(Long.class))).thenReturn(new Boolean(false));

		MvcResult mvcResult = mockMvc.perform(post("/api/advertiser/1000/2000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andExpect(status().isOk()).andReturn();
		
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
