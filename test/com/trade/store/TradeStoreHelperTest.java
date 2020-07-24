package com.trade.store;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TradeStoreHelperTest {

	@Before
	public void setUp() throws Exception {
		populateTrades();
	}

	@After
	public void tearDown() throws Exception {
		purgeTrades();
	}
	
	List<Trade> tradeslist = new ArrayList<>();

	private void populateTrades() {
		tradeslist.add(new Trade("T1", 1, "CP-1", "B1", Utils.getDate("2021/07/23")));
		tradeslist.add(new Trade("T2", 1, "CP-2", "B1", Utils.getDate("2021/07/23")));
		tradeslist.add(new Trade("T3", 1, "CP-1", "B1", Utils.getDate("2021/07/23")));
		tradeslist.add(new Trade("T4", 1, "CP-3", "B2", Utils.getDate("2021/07/23")));
	}

	private void purgeTrades() {
		// TODO delete records in case input is from db
	}
	
	@Test
	public void testAllTrades() {
		TradeStoreHelper helper = new TradeStoreHelper();
		assertTrue(helper.getAllTrades().size()==0);
		
		for(Trade t : tradeslist) {
			helper.addTrade(t);
		}
		assertTrue(helper.getAllTrades().size()==tradeslist.size());
	}

	@Test
	public void testStoreAddOKTrade() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2020/08/20"));
		helper.addTrade(n);
		Trade t = helper.getTrade("T11", 1);
		assertFalse(t.isExpired());
		assertTrue(t.equals(n));
	}

	@Test(expected = TradeStoreException.class)
	public void testStoreAddBadMaturityDate() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2008/02/20"));
		helper.addTrade(n); // Check for the message
	}

	@Test
	public void testStoreAddandUpdateTrade() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n1 = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2020/08/20"));
		helper.addTrade(n1);
		Trade n2 = new Trade(n1.getId(), 2, n1.getCounterpartyId(), n1.getBookId(), Utils.getDate("2021/09/20"));

		helper.updateTrade(n2);

		List<Trade> t = helper.getTradesById("T11");
		assertTrue(t.size() == 2);
		assertTrue(t.get(0).getVersion() == 2);
		assertTrue(t.get(1).getVersion() == 1);
	}

	@Test
	public void testStoreAddExistingTrade() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n1 = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2020/08/20"));
		boolean flag = false;
		try {
			helper.addTrade(n1);
			helper.addTrade(n1);
		} catch (TradeStoreException e) {
			assertTrue(e.getMessage().contains("Trade already found with Trade id"));
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void testStoreUpdateTradeWithOldVersion() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n1 = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2020/08/20"));
		helper.addTrade(n1);

		Trade n2 = new Trade(n1.getId(), n1.getVersion(), n1.getCounterpartyId(), n1.getBookId(),
				Utils.getDate("2021/09/20"));
		
		boolean flag = false;
		try {
			helper.updateTrade(n2);
		} catch (TradeStoreException e) {
			assertTrue(e.getMessage().contains("Trade version lower is received expected version greater than"));
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void testStoreBadMaturityDt() {
		TradeStoreHelper helper = new TradeStoreHelper();
		Trade n1 = new Trade("T11", 1, "CP-1", "B1", Utils.getDate("2020/07/24")); // old date
		boolean flag = false;
		try {
			helper.addTrade(n1);
		} catch (TradeStoreException e) {
			assertTrue(e.getMessage().contains("Maturity Date value less then today date"));
			flag = true;
		}
		assertTrue(flag);
	}

}
