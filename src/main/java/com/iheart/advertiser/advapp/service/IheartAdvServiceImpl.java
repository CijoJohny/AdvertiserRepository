package com.iheart.advertiser.advapp.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iheart.advertiser.advapp.constants.IHeartAdvertiserStatus;
import com.iheart.advertiser.advapp.constants.IHeartConstants;
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

		Advertiser advertiser1 = iheartAdvDao.findAdvByName(advertiser.getAdvName().toUpperCase());

		if (null == advertiser1) {
			advertiser.setAdvName(advertiser.getAdvName().toUpperCase());
			advertiser.setLastModifiedDate(new Date(System.currentTimeMillis()));
			iheartAdvDao.addAdv(advertiser);
			return IHeartConstants.SUCCESS;
		} else {

			return IHeartConstants.ERROR;
		}

	}

	@Override
	public Advertiser getAdvertiserInfo(int advertiserId) {
		Advertiser advertiser = iheartAdvDao.findAdvById(advertiserId);
		return advertiser;
	}

	@Override
	public String deleteAdvertiserById(int advertiserId) {
		int count = iheartAdvDao.deleteAdvById(advertiserId);
		if (count == 1) {
			return IHeartConstants.SUCCESS;
		}
		return IHeartConstants.ERROR;
	}

	@Override
	public String updateAdvertiserById(Advertiser advertiser) {

		Advertiser advertiserResp = iheartAdvDao.findAdvById(advertiser.getAdvertiserId());

		if (null != advertiserResp) {

			advertiser.setAdvContactName(advertiser.getAdvContactName() == null ? advertiserResp.getAdvContactName()
					: advertiser.getAdvContactName());
			advertiser.setAdvName(
					advertiser.getAdvName() == null ? advertiserResp.getAdvName() : advertiser.getAdvName());
			advertiser.setAdvCreditLimit(advertiser.getAdvCreditLimit() == null ? advertiserResp.getAdvCreditLimit()
					: advertiser.getAdvCreditLimit());
			advertiser.setLastModifiedDate(new Date(System.currentTimeMillis()));
			advertiser.setStatus(advertiser.getStatus() == null ?advertiserResp.getStatus()
					: advertiser.getStatus());
			if (IHeartAdvertiserStatus.isValidSatus(advertiser.getStatus())) {
				int count = iheartAdvDao.updateAdvById(advertiser);

				if (count == 1) {
					return IHeartConstants.SUCCESS;
				}
			}
		}
		return IHeartConstants.ERROR;
	}

	@Override
	public Boolean doCreditCheck(int advertiserId, long creditBalance) {
		Advertiser advertiser = iheartAdvDao.findAdvById(advertiserId);
		if (null != advertiser && null != advertiser.getAdvCreditLimit()
				&& advertiser.getAdvCreditLimit() >= creditBalance) {
			return true;
		}
		return false;
	}

	@Override
	public List<Advertiser> getAllAdvertiser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean creditAmountFromAdvertiser(int advertiserId, long creditAmount) {
		Advertiser advertiser = iheartAdvDao.findAdvById(advertiserId);
		if (null != advertiser && null != advertiser.getAdvCreditLimit()
				&& advertiser.getAdvCreditLimit() >= creditAmount) {
			Advertiser adv = new Advertiser();
			adv.setAdvCreditLimit(advertiser.getAdvCreditLimit() - creditAmount);
			adv.setAdvertiserId(advertiserId);
			updateAdvertiserById(adv);
			return true;
		}
		return false;
	}
}
