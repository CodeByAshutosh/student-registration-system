package com.srs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	public static Date convertDateStringToDate_FormateYYYYMMDDSeperatedByHyphen(String dateStr) {
		// Formate : 2016-12-13
		Date dateObj = null;
		try {
			dateObj = new SimpleDateFormat("YYYY-MM-dd").parse(dateStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateObj;
	}
	
	public static Date convertToDateFormat(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// "dd-MMM-yyyy"
		// String dateInString = "7-Jun-2013";
		Date returnDate = null;
		try {
			Date date = formatter.parse(dateInString);
			// System.out.println(date);
			returnDate = date;// formatter.format(date);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDate;
	}

}
