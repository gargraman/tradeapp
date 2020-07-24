package com.trade.store;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TradeStoreHelper {

	TradeStoreData<Trade> mydb;

	public TradeStoreHelper() {
		this.mydb = new TradeStoreData<>(new TradeComparator());
	}
	
	public TradeStoreHelper(Comparator<Trade> c) {
		this.mydb = new TradeStoreData<>(c);
	}

	public void addTrade(Trade trade) throws TradeStoreException {
		validate(trade, false);
		mydb.save(trade);
	}

	public Trade getTrade(String id, int version) throws TradeStoreException  {
		List<Trade> lst = getTradesById(id);
		
		if(lst!=null) {
			Collections.sort(lst, new TradeComparator());
			return lst.get(0);
		}
		return null;
	}

	public List<Trade> getTradesById(String id) {
		return mydb.get(id);
	}

	public List<Trade> getAllTrades() {
		return mydb.getAll();
	}	
	
	public void updateTrade(Trade trade) {
		validate(trade, true);
		mydb.save(trade);
	}

	private void validate(Trade trade, boolean exists) {
		List<Trade> t = mydb.get(trade.getId());
		if(trade.getMaturityDt().compareTo(Calendar.getInstance().getTime())<=0) {
			throw new TradeStoreException("Maturity Date value less then today date");
		}
		
		if(!exists && t.size()>0) { // new trade but trade exists
			throw new TradeStoreException("Trade already found with Trade id :" + trade.getId());
		}
		
		if(exists && t.size()==0) { // old trade but trade not found 
			throw new TradeStoreException("Trade not found with Trade id :" + trade.getId());
		}
		
		if(exists) { // old trade but trade version not properly set
			Collections.sort(t,new TradeComparator());
			if(trade.getVersion() <= t.get(0).getVersion())
			throw new TradeStoreException("Trade version lower is received expected version greater than : " + trade.getVersion());
		}
		
	}

}
