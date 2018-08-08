package com.iheart.advertiser.advapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iheart.advertiser.advapp.dao.IheartAdvDao;
import com.iheart.advertiser.advapp.model.Advertiser;

@Service
public class IheartAdvServiceImpl implements IheartAdvService {
	
	
	@Autowired
	private IheartAdvDao iheartAdvDao;
	
	public Advertiser getAdvertiserInfo(String name) {
		
		Advertiser advertiser = iheartAdvDao.findAdvByName(name);
		
	  return advertiser;
	}

	@Override
	public String addAdvertiser(Advertiser advertiser) {
		
		iheartAdvDao.addAdv(advertiser);
		
		return "success";
	}

	@Override
	public Advertiser getAdvertiserInfo(int advertiserId) {
		Advertiser advertiser = iheartAdvDao.findAdvById(advertiserId);
		return advertiser;
	}
	

}
