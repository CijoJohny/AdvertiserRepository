package com.iheart.advertiser.advapp.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Advertiser {
	private int advertiserId;
	private String advName;
	private String advContactName;
	private Long advCreditLimit;
	private Date lastModifiedDate;

}
