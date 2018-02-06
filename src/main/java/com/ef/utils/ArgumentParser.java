package com.ef.utils;

import java.util.Arrays;
import java.util.Optional;

public class ArgumentParser {

	public String parse(String name, String[] args) {
		return getBy(name, args).map(value -> value.replaceAll("--","").split("=")[1]).orElse(null);
	}

	private Optional<String> getBy(String name, String[] args){
		return Arrays.stream(args).filter(argument -> argument.contains(name)).findFirst();
	}
	
}
