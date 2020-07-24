package com.trade.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TradeComparatorTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompare() {
		
		Trade n1 = new Trade("T1",1,"CP-1", "B1", Utils.getDate("2020/08/20"));
		Trade n2 = new Trade("T2",1,"CP-1", "B1", Utils.getDate("2020/08/20"));
		List<Trade> l = new ArrayList<>();
		l.add(n1);
		l.add(n2);
		Collections.sort(l, new TradeComparator());
		
	}

}
