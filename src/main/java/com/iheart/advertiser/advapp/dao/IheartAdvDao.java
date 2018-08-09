package com.iheart.advertiser.advapp.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iheart.advertiser.advapp.model.Advertiser;

@Mapper
public interface IheartAdvDao {

	@Results({ @Result(property = "advertiserId", column = "ADVERTISER_ID"),
			@Result(property = "advName", column = "NAME"),
			@Result(property = "advContactName", column = "CONTACT_NAME"),
			@Result(property = "advCreditLimit", column = "CREDIT_LIMIT"),
			@Result(property = "lastModifiedDate", column = "LAST_MODIFIED_DATE"),
			@Result(property = "createdDate", column = "CREATED_DATE")})

	@Select("SELECT * FROM ADVERTISER WHERE NAME=#{name}")
	Advertiser findAdvByName(String name);

	@Select("SELECT * FROM ADVERTISER")
	Advertiser findAllAdvertisers();

	@Results({ @Result(property = "advertiserId", column = "ADVERTISER_ID"),
			@Result(property = "advName", column = "NAME"),
			@Result(property = "advContactName", column = "CONTACT_NAME"),
			@Result(property = "advCreditLimit", column = "CREDIT_LIMIT"),
			@Result(property = "lastModifiedDate", column = "LAST_MODIFIED_DATE"),
			@Result(property = "createdDate", column = "CREATED_DATE")})
	
	@Select("SELECT * FROM ADVERTISER WHERE ADVERTISER_ID  =#{ADVERTISER_ID}")
	Advertiser findAdvById(int ADVERTISER_ID);

	@Insert("INSERT INTO ADVERTISER(ADVERTISER_ID,NAME, CONTACT_NAME, CREDIT_LIMIT,LAST_MODIFIED_DATE) VALUES(advertiser_seq.NEXTVAL,#{advName}, #{advContactName}, #{advCreditLimit},#{lastModifiedDate})")
	void addAdv(Advertiser advertiser);

	@Delete("DELETE FROM ADVERTISER WHERE ADVERTISER_ID =#{advertiserId}")
	int deleteAdvById(int ADVERTISER_ID);

	@Update("Update ADVERTISER set name=#{advName}, CONTACT_NAME=#{advContactName},CREDIT_LIMIT= #{advCreditLimit},LAST_MODIFIED_DATE=#{lastModifiedDate} where ADVERTISER_ID=#{advertiserId}")
	public int updateAdvById(Advertiser advertiser);

}
