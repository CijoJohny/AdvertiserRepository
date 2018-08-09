package com.iheart.advertiser.advapp.constants;

public enum IHeartAdvertiserStatus {
	
	ACTIVE(1),
	INACTIVE(2),
	PAUSED(3),
	UNKNOWN(-1);
	
	int code;
	
	IHeartAdvertiserStatus(int code){
		this.code = code;
	}
	
	public int getCode() {
        return code;
    }

	public static boolean isValidSatus(String status) {
		status = status.toUpperCase();
		if (status.equals(ACTIVE.name()) ||status.equals(INACTIVE.name()) ||status.equals(PAUSED.name())) {
			return true;
		}
		return false;
	}
	
	

}
