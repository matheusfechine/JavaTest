package com.ef.service;

import java.util.List;

import com.ef.dao.LogDao;
import com.ef.model.Log;
import com.ef.service.exception.LogException;
import com.ef.service.utils.LogUtils;

public class LogService {

	private LogDao dao;
	private LogUtils utils;
	
	public List<Log> findBy(String startDate, String duration, String threshold) throws Exception {
		initDependencies();
		return dao.findBy(startDate, duration, utils.parseToInteger(threshold));
	}

	public void insert(List<String> lines) throws LogException {
		initDependencies();
		if(!dao.hasAlreadyLoaded()){
			dao.insert(lines);
		}
	}

	private void initDependencies() {
		if(dao == null){
			dao = new LogDao();
		}
		if(utils == null){
			utils = new LogUtils();
		}
	}

	public void insertIntoBlockList(String ipAddress, String comment) throws LogException {
		dao.insertIntoBlockList(ipAddress, comment);
	}
	
}
