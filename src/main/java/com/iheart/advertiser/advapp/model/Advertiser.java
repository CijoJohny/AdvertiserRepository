package com.iheart.advertiser.advapp.model;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Advertiser {
	@ApiModelProperty(example = "10000", required = true, value = "advertiser Id", dataType = "Number")
	private int advertiserId;
	@ApiModelProperty(required = true, value = "IHEART MEDIA", dataType = "String", example = "IHEART MEDIA")
	private String advName;
	@ApiModelProperty(required = true, value = "Cijo Johny", dataType = "String", example = "Cijo Johny")
	private String advContactName;
	@ApiModelProperty(required = false, value = "ACTIVE", dataType = "String", example = "ACTIVE")
	private String status;
	
	private Long advCreditLimit;
	private Date lastModifiedDate;
	private Date createdDate;

}
