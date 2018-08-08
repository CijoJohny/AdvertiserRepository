package com.iheart.advertiser.advapp.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import com.iheart.advertiser.advapp.model.Advertiser;

@Mapper
public interface IheartAdvDao {
	
	@Results({
		@Result(property = "advertiserId", column = "ADVERTISER_ID"),
        @Result(property = "advName", column = "NAME"),
        @Result(property = "advContactName", column = "CONTACT_NAME"),
        @Result(property = "advCreditLimit", column = "CREDIT_LIMIT"),
	})
	
	@Select("SELECT * FROM ADVERTISER WHERE NAME=#{name}")
	Advertiser findAdvByName(String name);
	
	
	@Select("SELECT * FROM ADVERTISER")
	Advertiser findAllAdvertisers();
	
	@Results({
		@Result(property = "advertiserId", column = "ADVERTISER_ID"),
        @Result(property = "advName", column = "NAME"),
        @Result(property = "advContactName", column = "CONTACT_NAME"),
        @Result(property = "advCreditLimit", column = "CREDIT_LIMIT"),
	})
	@Select("SELECT * FROM ADVERTISER WHERE ADVERTISER_ID  =#{ADVERTISER_ID}")
	Advertiser findAdvById(int ADVERTISER_ID);
	
	
	@Insert("INSERT INTO ADVERTISER(ADVERTISER_ID,NAME, CONTACT_NAME, CREDIT_LIMIT) VALUES(#{advertiserId},#{advName}, #{advContactName}, #{advCreditLimit})")
	void addAdv(Advertiser advertiser);
	
	@Delete("DELETE FROM ADVERTISER WHERE ADVERTISER_ID =#{advertiserId}")
	void deleteAdvById(int ADVERTISER_ID);
	
	
	@Update("Update ADVERTISER set name=#{name}, CONTACT_NAME=#{advContactName},CREDIT_LIMIT= #{advCreditLimit} where ADVERTISER_ID=#{advertiserId}")
	public int updateAdvById(Advertiser advertiser);
	

}
