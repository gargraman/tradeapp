package com.trade.store;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static final String YYYY_MM_DD = "yyyy/MM/dd";

	public static Date getDate(String dateInString, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {

			return formatter.parse(dateInString);
			
		} catch (ParseException e) {
			throw new TradeStoreException("Invalid date",e);
		}
	}

	public static Date getDate(String string) {
		return getDate(string, YYYY_MM_DD);
	}
	
	public static String fromDate(Date dt) {
		Format formatter = new SimpleDateFormat(YYYY_MM_DD);
		return formatter.format(dt);
	}

}
