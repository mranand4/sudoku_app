package com.sudoku2.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class DateUtils {
	
	/**
	 * 
	 * @param date is like yyyy-mm-dd HH:mm:ss
	 * @return
	 */
	public static Timestamp getTimestampFromDate(String dateStr) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(dateStr);
			return new Timestamp(date.getTime());
		} catch(Exception e) {
			return null;
		}
	}

}
