package com.iheart.advertiser.advapp.service;

import java.util.List;

import com.iheart.advertiser.advapp.model.Advertiser;

public interface IheartAdvService {

	public Advertiser getAdvertiserInfo(String name);

	public Advertiser getAdvertiserInfo(int advertiserId);

	public Advertiser addAdvertiser(Advertiser advertiser);

	public String deleteAdvertiserById(int advertiserId);

	public String updateAdvertiserById(Advertiser advertiser);

	public Boolean doCreditCheck(int advertiserId, long creditBalance);

	public List<Advertiser> getAllAdvertiser();

	public Boolean creditAmountFromAdvertiser(int advertiserId, long creditAmount);

}
