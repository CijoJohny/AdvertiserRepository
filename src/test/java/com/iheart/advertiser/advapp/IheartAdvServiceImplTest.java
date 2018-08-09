package com.iheart.advertiser.advapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.iheart.advertiser.advapp.actuator.CustomizeInfoEndPoint;
import com.iheart.advertiser.advapp.constants.IHeartAdvertiserStatus;
import com.iheart.advertiser.advapp.dao.IheartAdvDao;
import com.iheart.advertiser.advapp.model.Advertiser;
import com.iheart.advertiser.advapp.model.AdvertiserError;
import com.iheart.advertiser.advapp.service.IheartAdvServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class IheartAdvServiceImplTest {

	@InjectMocks
	private IheartAdvServiceImpl iheartAdvServiceImpl;
	@InjectMocks
	private CustomizeInfoEndPoint customizeInfoEndPoint;

	@Mock
	private IheartAdvDao iheartAdvDao;

	@Test
	public void getAdvertiserInfoErrorTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		request.setStatus("ACTIVE");
		when(iheartAdvDao.findAdvByName(ArgumentMatchers.any(String.class))).thenReturn(request);
		Advertiser advertiser = iheartAdvServiceImpl.getAdvertiserInfo("cijo");
		assertNotNull(advertiser);
	}

	@Test
	public void addAdvertiserErrorTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("CijoJohny");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		when(iheartAdvDao.findAdvByName(ArgumentMatchers.any(String.class))).thenReturn(request);

		iheartAdvServiceImpl.addAdvertiser(request);

	}

	@Test
	public void updateAdvertiserByIdTestSuccess() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("TestSuccessRunner");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100000L);
		request.setStatus("Active");
		request.setAdvertiserId(10010);
		when(iheartAdvDao.findAdvById(ArgumentMatchers.any(Integer.class))).thenReturn(request);
		when(iheartAdvDao.updateAdvById(ArgumentMatchers.any(Advertiser.class))).thenReturn(1);
		
		iheartAdvServiceImpl.updateAdvertiserById(request);

	}

	@Test
	public void updateAdvertiserByIdTestError() throws Exception {

		Advertiser request1 = new Advertiser();
		request1.setAdvName("UpdateProfile");
		request1.setAdvContactName("Cijo Johny");
		request1.setAdvCreditLimit(100000L);
		request1.setStatus("Active");
		iheartAdvServiceImpl.updateAdvertiserById(request1);

	}

	@Test
	public void creditAmountFromAdvertiserTestSuccess() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100000L);
		request.setStatus("Active");
		when(iheartAdvDao.findAdvById(ArgumentMatchers.any(Integer.class))).thenReturn(request);
		iheartAdvServiceImpl.creditAmountFromAdvertiser(10, 1);

	}

	@Test
	public void doCreditCheckTest() throws Exception {

		iheartAdvServiceImpl.doCreditCheck(10, 1000);

	}

	@Test
	public void doCreditCheckTestError() throws Exception {
		Advertiser request = new Advertiser();
		request.setAdvName("CreditCheck");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100000L);
		when(iheartAdvDao.findAdvById(ArgumentMatchers.any(Integer.class))).thenReturn(request);
		iheartAdvServiceImpl.doCreditCheck(10, 1);

	}

	@Test
	public void getAdvertiserInfoTest1() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);

		Advertiser advertiser = iheartAdvServiceImpl.getAdvertiserInfo("cijo");
		assertNull(advertiser);

	}

	@Test
	public void addAdvertiserTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);

		iheartAdvServiceImpl.addAdvertiser(request);

	}

	@Test
	public void creditAmountFromAdvertiserTest() throws Exception {

		iheartAdvServiceImpl.creditAmountFromAdvertiser(10, 10000);

	}

	@Test
	public void getAllAdvertiserTest() throws Exception {

		iheartAdvServiceImpl.getAllAdvertiser();

	}

	@Test
	public void deleteAdvertiserByIdTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		when(iheartAdvDao.deleteAdvById(ArgumentMatchers.any(Integer.class))).thenReturn(1);
		iheartAdvServiceImpl.deleteAdvertiserById(1);

	}

	@Test
	public void deleteAdvertiserByIdTestError() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("DeleteEmpty");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		when(iheartAdvDao.deleteAdvById(ArgumentMatchers.any(Integer.class))).thenReturn(0);
		iheartAdvServiceImpl.deleteAdvertiserById(1);

	}

	@Test
	public void getAdvertiserInfoTest() throws Exception {

		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100L);
		iheartAdvServiceImpl.getAdvertiserInfo(10);

	}

	@Test
	public void getAdvertiserDomainTest() throws Exception {
		
		Advertiser request = new Advertiser();
		request.setAdvName("Cijo");
		request.setAdvContactName("Cijo Johny");
		request.setAdvCreditLimit(100000L);
		request.setStatus("ACTIVE");
		request.setCreatedDate(new Date(System.currentTimeMillis()));
		request.setLastModifiedDate(new Date(System.currentTimeMillis()));
		Advertiser request2 = new Advertiser();
		request2.setAdvName("Test2");
		request2.setAdvContactName("Cijo Johny");
		request2.setAdvCreditLimit(100000L);
		request2.setStatus("ACTIVE");
		request2.setCreatedDate(new Date(System.currentTimeMillis()));
		request2.setLastModifiedDate(new Date(System.currentTimeMillis()));
		request.equals(request2);
		Advertiser request3 =request;
		request3.equals(request);
		assertEquals(request, request);
		assertTrue(request.hashCode() == request.hashCode());
		assertEquals(new Advertiser(), new Advertiser());
		assertEquals(new AdvertiserError(), new AdvertiserError());
		assertTrue(new Advertiser().hashCode() == new Advertiser().hashCode());
		assertTrue(new AdvertiserError().hashCode() == new AdvertiserError().hashCode());
		assertFalse(new AdvertiserError().toString().contains("@"));
		assertFalse(new Advertiser().toString().contains("@"));
		

	}
	
	@Test
	public  void doContantValidation() {
		IHeartAdvertiserStatus.isValidSatus("INACTIVEUSER");
		IHeartAdvertiserStatus.ACTIVE.getCode();
		IHeartAdvertiserStatus.values();
		IHeartAdvertiserStatus.valueOf("ACTIVE");
	}

	@Test
	public void getAdvertiserErrorDomainTest() throws Exception {

		AdvertiserError request = new AdvertiserError();
		request.setCode("3445");
		request.setMessage("Test");
		AdvertiserError request2 = new AdvertiserError();
		request2.setCode("3445");
		request2.setMessage("Test");
		assertEquals(request, request2);
		assertTrue(request.hashCode() == request2.hashCode());

	}

}
