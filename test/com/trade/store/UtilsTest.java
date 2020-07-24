package com.trade.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testDate() {
		Date t = null;
		t = Utils.getDate("2020/05/15");
		Calendar myCalendar = new GregorianCalendar(2020, GregorianCalendar.MAY, 15);
		Date myDate = myCalendar.getTime();
		assertEquals(t,myDate);
	}

	@Test
	public void testDateWithPattern() {
		Date t = null;
		t = Utils.getDate("2020-05-15","yyyy-MM-dd");
		Calendar myCalendar = new GregorianCalendar(2020, GregorianCalendar.MAY, 15);
		Date myDate = myCalendar.getTime();
		assertEquals(t,myDate);
	}
	
	@Test(expected=TradeStoreException.class)
	public void testBadDate() throws ParseException {
		Utils.getDate("2020-15-15");
		fail("TradeStoreException expected");
	}
	
	@Test
	public void testFromDate() {
		Calendar myCalendar = new GregorianCalendar(2020, GregorianCalendar.MAY, 15);
		Date dt =myCalendar.getTime();
		String t = Utils.fromDate(dt);
		assertEquals(t,"2020/05/15");
	}
	
}
