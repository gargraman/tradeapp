package com.trade.store;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TradeStoreExceptionTest {

	@Test
	public void testRuntimeExceptionString() {
		try {
			throw new TradeStoreException("No error found");
			
		}catch(TradeStoreException e) {
			assertTrue(e.getMessage().equals("No error found"));
		}
	}

	@Test
	public void testExceptionString() {
		try {
			
			throw new TradeStoreException("No error found", new RuntimeException("Howdy"));
			
		}catch(TradeStoreException e) {
			assertTrue(e.getMessage().equals("No error found"));
		}
	}

}
