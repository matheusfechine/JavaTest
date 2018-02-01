package com.ef.service;

import java.util.Date;
import java.util.List;

import com.ef.dao.LogDao;
import com.ef.model.Log;

public class LogService {

	private LogDao dao;
	
	public List<Log> findBy(Date startDate, String duration, int threshold) {
		return dao.findBy(startDate, duration, threshold);
	}

	public void insert(Log log) {
		dao.insert(log);
	}


}
