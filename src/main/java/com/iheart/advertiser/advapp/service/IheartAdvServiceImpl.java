package com.iheart.advertiser.advapp.service;

import java.util.List;

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
	//	Advertiser advertiser = iheartAdvDao.findAdvByName("cijoComplanyq");
		return advertiser;
	}

	@Override
	public String deleteAdvertiserById(int advertiserId) {
		iheartAdvDao.deleteAdvById(advertiserId);
		return null;
	}

	@Override
	public String updateAdvertiserById(Advertiser advertiser) {
		iheartAdvDao.updateAdvById(advertiser);
		return null;
	}

	@Override
	public Boolean doCreditCheck(int advertiserId, long creditBalance) {
		Advertiser advertiser = iheartAdvDao.findAdvById(advertiserId);
		if (null!= advertiser && null!=advertiser.getAdvCreditLimit() && advertiser.getAdvCreditLimit() > creditBalance) {
			return true;
		}
		return null;
	}

	@Override
	public List<Advertiser> getAllAdvertiser() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
