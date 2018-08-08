package com.iheart.advertiser.advapp;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.MockitoJUnitRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.advertiser.advapp.controller.IheartAdvController;
import com.iheart.advertiser.advapp.model.Advertiser;
import com.iheart.advertiser.advapp.service.IheartAdvService;
import com.iheart.advertiser.advapp.service.IheartAdvServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class IheartAdvControllerTest {
	
	@InjectMocks
 	private IheartAdvController controller;
	
	
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
 		
 	//	doNothing().when(iheartAdvService).addAdvertiser(ArgumentMatchers.any(Advertiser.class));
 		when(iheartAdvService.addAdvertiser(ArgumentMatchers.any(Advertiser.class))).thenReturn("success");
 		
 		MvcResult mvcResult = mockMvc
                .perform(post("/api/advertiser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated()).andReturn();
 		
 		
 		
 	}
 	

 	@Test
 	public void testGetAdvertiserById() throws Exception {
 		Advertiser request = new Advertiser();
 		request.setAdvName("Cijo");
 		request.setAdvContactName("Cijo Johny");
 		request.setAdvCreditLimit(100L);
 		request.setAdvertiserId(11);
 		
 	//	doNothing().when(iheartAdvService).addAdvertiser(ArgumentMatchers.any(Advertiser.class));
 		when(iheartAdvService.getAdvertiserInfo(ArgumentMatchers.any(Integer.class))).thenReturn(request);
 		
 		MvcResult mvcResult = mockMvc
                .perform(get("/api/advertiser/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated()).andReturn();
 		
 		
 		
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
 		
 	//	doNothing().when(iheartAdvService).addAdvertiser(ArgumentMatchers.any(Advertiser.class));
 		when(iheartAdvService.getAllAdvertiser()).thenReturn(advList);
 		
 		MvcResult mvcResult = mockMvc
                .perform(get("/api/advertiser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated()).andReturn();
 		
 		
 		
 	}
 	
 	
 	
 	private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 	

}
