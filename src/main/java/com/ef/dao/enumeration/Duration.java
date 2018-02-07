package com.ef.dao.enumeration;

import java.util.Arrays;
import java.util.Optional;

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
		return get(description, Duration.values()).orElse(null);
	}
	
	private static Optional<Duration> get(String description, Duration[] values){
		return Arrays.stream(values).filter(argument -> argument.getDescription().equals(description)).findFirst();
	}

}
