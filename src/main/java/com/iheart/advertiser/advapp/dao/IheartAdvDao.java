package com.iheart.advertiser.advapp.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.iheart.advertiser.advapp.model.Advertiser;

@Mapper
public interface IheartAdvDao {
	
	@Select("SELECT * FROM ADVERTISER WHERE NAME=#{name}")
	Advertiser findAdvByName(String name);
	
	@Select("SELECT * FROM ADVERTISER WHERE ADVERTISER_ID  =#{ADVERTISER_ID}")
	Advertiser findAdvById(int ADVERTISER_ID);
	
	@Insert("INSERT INTO ADVERTISER(ADVERTISER_ID,NAME, CONTACT_NAME, CREDIT_LIMIT) VALUES(#{advertiserId},#{advName}, #{advContactName}, #{advCreditLimit})")
	void addAdv(Advertiser advertiser);
	
	// TODO Add methods for update and delete operations

}
