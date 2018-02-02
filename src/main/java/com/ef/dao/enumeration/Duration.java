package com.ef.dao.enumeration;

public enum Duration {

	HOURLY(1, "hourly"), DAILY(24, "daily");

	private Integer duration;
	private String description;

	private Duration(Integer duration, String description){
		this.duration = duration;
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}
	
	public static Duration getBy(String description){
		for (Duration duration : Duration.values()) {
			if(description.equals(duration.getDescription())){
				return duration;
			}
		}
		return null;
	}

}
