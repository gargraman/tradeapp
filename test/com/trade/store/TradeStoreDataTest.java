package com.trade.store;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TradeStoreDataTest {

	
	
	@Test
	public void testGetAllTrades() {
		TradeStoreData<Trade> tdata = new TradeStoreData<>(new TradeComparator());
		List<Trade> trades = tdata.getAll();
		assertTrue((trades.size()==0));
	}
	
	@Test
	public void testSaveTrade() {
		TradeStoreData<Trade> tdata = new TradeStoreData<>(new TradeComparator());
		Trade t = new Trade("T1", 1, "CP-1", "B1", Utils.getDate("2021/07/23"));
		tdata.save(t);
		assertTrue(( tdata.get("T1").size()==1));
	}
	
	@Test(expected=TradeStoreException.class)
	public void testSaveBadTrade() {
		TradeStoreData<Trade> tdata = new TradeStoreData<>(new TradeComparator());
		tdata.save(null);
		assertTrue(( tdata.getAll().size()==1));
	}


}
