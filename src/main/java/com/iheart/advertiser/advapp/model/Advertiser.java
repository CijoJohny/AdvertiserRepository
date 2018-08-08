package com.iheart.advertiser.advapp.model;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Advertiser {
	
	
	private int advertiserId;
	
	 @ApiModelProperty(required = true, value = "IHEART MEDIA", dataType = "String",example ="IHEART MEDIA")
	private String advName;
	 @ApiModelProperty(required = true, value = "Cijo Johny", dataType = "String",example ="Cijo Johny")
	private String advContactName;
	// @ApiModelProperty(required = true, value = 128000, dataType = Long,example =12500.00)
	private Long advCreditLimit;
	private Date lastModifiedDate;

}
