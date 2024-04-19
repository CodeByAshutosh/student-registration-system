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

}
