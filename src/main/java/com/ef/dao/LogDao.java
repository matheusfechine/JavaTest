package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ef.dao.connection.DataBaseConnectionFactory;
import com.ef.model.Log;
import com.ef.service.exception.LogException;
import com.ef.service.parse.FileToEntity;
import com.ef.service.parse.exception.FileToEntityException;
import com.ef.service.utils.LogUtils;

public class LogDao {

	private FileToEntity fileToEntity;
	private LogUtils utils;
	
	public List<Log> findBy(String startDate, String duration, int threshold) throws Exception {
		initDependencies();
		Connection c = null;
		List<Log> logs = new ArrayList<Log>();
		try {
			
			StringBuilder sql = new StringBuilder();	
			sql.append("   select count(*), ip_address from log   ");
			sql.append("   where start_date between ? and ?   ");
			sql.append("   group by ip_address having count(*)>   ?   ");
			c = new DataBaseConnectionFactory().getConnection();
			
			PreparedStatement stmt = c.prepareStatement(sql.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(utils.parseStartDateBy(startDate, duration).getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(utils.parseEndDateBy(startDate, duration).getTime()));
			stmt.setInt(3, threshold);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Log log = new Log();
				log.setIpAddress(rs.getString("ip_address"));
				logs.add(log);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			throw new Exception("Connection Issue!");
		} finally {
			c.close();
		}
		return logs;
	}

	public void insert(List<String> lines) throws LogException {
		initDependencies();
		Connection c = null;
		PreparedStatement stmt =null;
		try {
			c = new DataBaseConnectionFactory().getConnection();
			
			c.setAutoCommit(false);
			String sql = "insert into log(start_date, ip_address, http_method, http_status, description) values(?,?,?,?,?)";
			stmt = c.prepareStatement(sql);
			for (String line : lines) {
				Log log = fileToEntity.transform(line);
				stmt.setTimestamp(1, new java.sql.Timestamp(log.getStartDate().getTime()));
				stmt.setString(2, log.getIpAddress());
				stmt.setString(3, log.getHttpMethod());
				stmt.setInt(4, log.getHttpStatus());
				stmt.setString(5, log.getDescription());
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			throw new LogException("Unable to insert Log: "+e.getMessage());
		}catch(FileToEntityException e) {
			throw new LogException("Unable to insert Log: "+e.getMessage());
		}finally {
			try {
				c.close();
				stmt.close();
			} catch (SQLException e) {
				throw new LogException("Unable to close Connection: "+e.getMessage());
			}
		}

	}

	public boolean hasAlreadyLoaded() throws LogException{
		initDependencies();
		Connection c = null;
		PreparedStatement stmt =null;
		try {
			StringBuilder sql = new StringBuilder();	
			sql.append("   select count(*) from log   ");
			c = new DataBaseConnectionFactory().getConnection();
			stmt = c.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return false;
			}
			rs.close();
			stmt.close();
			c.close();
			return true;
		} catch (SQLException e) {
			throw new LogException("Unable to insert Log: "+e.getMessage());
		}finally {
			try {
				c.close();
				stmt.close();
			} catch (SQLException e) {
				throw new LogException("Unable to close Connection: "+e.getMessage());
			}
		}
	}

	public void insertIntoBlockList(String ipAddress, String comment) throws LogException {
		initDependencies();
		Connection c = null;
		PreparedStatement stmt =null;
		try {
			c = new DataBaseConnectionFactory().getConnection();
			
			c.setAutoCommit(false);
			String sql = "insert into blacklist(ipaddress, comment, date) values(?,?,?)";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, ipAddress);
			stmt.setString(2, comment);
			stmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			throw new LogException("Unable to insert into Black List: "+e.getMessage());
		}finally {
			try {
				c.close();
				stmt.close();
			} catch (SQLException e) {
				throw new LogException("Unable to close Connection: "+e.getMessage());
			}
		}
	}
	
	private void initDependencies() {
		if(fileToEntity == null){
			fileToEntity=new FileToEntity();
		}
		if(utils == null){
			utils = new LogUtils();
		}
	}

}
