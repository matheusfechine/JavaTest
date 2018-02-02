package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.ef.dao.connection.DataBaseConnectionFactory;
import com.ef.dao.enumeration.Duration;
import com.ef.model.Log;
import com.ef.service.parse.FileToEntity;

public class LogDao {

	private FileToEntity fileToEntity;

	public List<Log> findBy(Date startDate, String duration, int threshold) throws Exception {
		Connection c = null;
		List<Log> logs = new ArrayList<Log>();
		try {
			
			StringBuilder sql = new StringBuilder();	
			sql.append("   select count(*), ip_address from log   ");
			sql.append("   where start_date between ? and ?   ");
			sql.append("   group by ip_address having count(*)>   ?   ");
			c = new DataBaseConnectionFactory().getConnection();
			
			PreparedStatement stmt = c.prepareStatement(sql.toString());
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(
						new DateTime(startDate)
						.plusHours(Duration
								.getBy(duration)
								.getDuration())
						.toDate()
						.getTime()
					));
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

	public void insert(List<String> lines) throws Exception {
		initTransformer();
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
			throw new Exception("Database Issue!");
		} finally {
			c.close();
			stmt.close();
		}

	}

	private void initTransformer() {
		if(fileToEntity == null){
			fileToEntity=new FileToEntity();
		}
	}

}
