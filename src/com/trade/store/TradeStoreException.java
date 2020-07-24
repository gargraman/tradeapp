package com.trade.store;

@SuppressWarnings("serial")
public class TradeStoreException extends RuntimeException {

	public TradeStoreException(String message, Throwable err) {
		super(message, err);
	}

	public TradeStoreException(String message) {
		this(message,null);
	}
}
