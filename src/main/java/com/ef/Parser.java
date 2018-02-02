package com.ef;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.ef.model.Log;
import com.ef.service.FileService;
import com.ef.service.LogService;

public class Parser {

	public static void main(String[] args) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/main/resources/access.log"));
			String line = "";
			List<String> lines = new ArrayList<String>();
			while((line = br.readLine()) != null){
				lines.add(line);
			}
//			new FileService().loadAndPersist(lines);
			
			String startDate = "2017-01-01.13:00:00";
			String duration = "hourly";
			String threshold = "250";
			
			List<Log> ipsFound = new LogService().findBy(startDate, duration, threshold);
			for (Log log : ipsFound) {
				System.out.println("Found:"+ log.getIpAddress());
				
			}
			
		} catch (Exception e) {
		}
		
	}

}
