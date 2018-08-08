package com.iheart.advertiser.advapp.service;

import com.iheart.advertiser.advapp.model.Advertiser;

public interface IheartAdvService {
	
	public Advertiser getAdvertiserInfo(String name);
	
	public Advertiser getAdvertiserInfo(int advertiserId);
	
	public String addAdvertiser(Advertiser advertiser);

}
